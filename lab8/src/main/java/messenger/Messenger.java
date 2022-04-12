package messenger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Messenger {
    public void execute(int delay, Runnable task) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(task, 0, delay, TimeUnit.MILLISECONDS);
    }
}
