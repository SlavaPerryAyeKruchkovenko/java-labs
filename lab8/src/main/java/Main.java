import messenger.Messenger;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<SocketChannel> clients = new ArrayList<>();
        boolean haveConnect = false;
        Runnable task = () -> {
            System.out.println("connected: " + getClientsCount(clients));
        };
        new Messenger().execute(5000, task);

        try (ServerSocketChannel socket = ServerSocketChannel.open()) {
            Selector selector = Selector.open();
            socket.bind(new InetSocketAddress(1111));
            socket.configureBlocking(false);
            socket.register(selector, SelectionKey.OP_ACCEPT);
            haveConnect = true;
            while (haveConnect) {
                int users = selector.select();
                if (users != 0 || selector.selectedKeys().size() != 0) {
                    for (SelectionKey key : selector.selectedKeys()) {
                        SocketChannel channel = socket.accept();
                        if (key.channel().equals(socket)) {
                            if (channel != null) {
                                clients.add(channel);
                                channel.configureBlocking(false);
                                channel.write(ByteBuffer.wrap("input>".getBytes(StandardCharsets.UTF_16LE)));
                                channel.register(selector, SelectionKey.OP_READ);
                            }
                        } else {
                            while (true) {
                                channel = (SocketChannel) key.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(100);
                                StringBuilder builder = new StringBuilder();
                                do {
                                    if (channel.read(buffer) > 0) {
                                        buffer.flip();
                                        String text = new String(buffer.array(), buffer.position(), buffer.remaining());

                                        if (haveEnd(buffer.array())) {
                                            String[] arr = text.split("\r");
                                            if (arr.length > 0)
                                                builder.append(arr[0]);
                                            break;
                                        }
                                        else if (text.length() > 0 && !text.equals("\n")) {
                                            builder.append(text);
                                        }

                                    }

                                }
                                while (true);
                                String text = builder.toString();

                                if (text.toString().toLowerCase(Locale.ROOT).equals("exit")) {

                                    channel.close();
                                    clients.remove(channel);
                                    break;
                                }
                                text = runTask(text) + ("input> ");
                                byte[] res = text.getBytes(StandardCharsets.UTF_8);
                                buffer = buffer.wrap(res);
                                channel.write(buffer);
                                buffer.clear();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static boolean haveEnd(byte[] arr) {
        for (byte el : arr) {
            if (el == '\r')
                return true;
        }
        return false;
    }

    private static int getClientsCount(List<SocketChannel> clients) {
        int result = 0;
        for (SocketChannel channel : clients)
            if (channel.isConnected() && channel.isOpen())
                result++;
        return result;
    }

    private static String runTask(String text) {
        if(!text.isEmpty()){
            String result;
            String[] arr = Arrays.stream(text.split(" "))
                    .map(String::trim)
                    .filter(x-> !x.equals(""))
                    .toArray(String[]::new);
            String dir = System.getProperty("user.dir");
            ProcessBuilder builder = new ProcessBuilder()
                    .command(arr)
                    .directory(new File(dir))
                    .redirectOutput(ProcessBuilder.Redirect.PIPE);

            try {
                Process process = builder.start();
                if (!process.waitFor(10, TimeUnit.SECONDS)) {
                    process.destroy();
                    return "timeout" + "\r\n";
                }

                BufferedReader inReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader erReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                result = inReader.lines().collect(Collectors.joining("\r\n"));
                String error= erReader.lines().collect(Collectors.joining("\r\n"));
                result = error.isEmpty() ? result : error;
            } catch (IOException e) {
                result = "unknown command";
            } catch (Exception e) {
                result = "error";
            }
            return result + "\r\n";
        }
        return null;
    }

}
