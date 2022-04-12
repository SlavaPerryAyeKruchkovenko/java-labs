import messenger.Messenger;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<SocketChannel> clients = new ArrayList<>();

        Runnable task = () -> {
            System.out.println("connected: " + getClientsCount(clients));
        };
        new Messenger().execute(5000, task);
    }

    private static int getClientsCount(List<SocketChannel> clients) {
        int result = 0;
        for (SocketChannel channel : clients)
            if (channel.isConnected() && channel.isOpen())
                result++;
        return result;
    }


}
