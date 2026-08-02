package net.moddedmod16.powerum.client.cpu;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadCreator {

    private static final int MAX_THREADS = 6;
    private static final ConcurrentLinkedQueue<Runnable> TASK_QUEUE = new ConcurrentLinkedQueue<>();
    private static final AtomicInteger ACTIVE_THREADS = new AtomicInteger();

    public static void submintTask(Runnable assetTask){
        if (assetTask == null) return;
        TASK_QUEUE.add(assetTask);
        if (ACTIVE_THREADS.get() < MAX_THREADS && !TASK_QUEUE.isEmpty()) {
            spawnThreads();
        }
    }
    public static synchronized void spawnThreads(){
        if (ACTIVE_THREADS.get() < MAX_THREADS){
            int currentid = ACTIVE_THREADS.incrementAndGet();
            Thread thread = new Thread(() -> {
                while (true) {
                    Runnable task = TASK_QUEUE.poll();
                    if (task != null) {
                        try {
                            task.run();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
                ACTIVE_THREADS.decrementAndGet();
            }, "powerum-asset-thread-" + currentid);
            thread.setDaemon(true);
            thread.start();
        }
    }
}
