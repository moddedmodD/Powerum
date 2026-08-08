package net.moddedmod16.powerum.client;

import java.util.concurrent.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger("Powerum-ThreadPool");
    private static final ExecutorService WORKER_POOL = new ThreadPoolExecutor(
            2,
            6,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            runnable -> {
                Thread thread = new Thread(runnable, "powerum-worker-thread");
                thread.setDaemon(true);
                return thread;
            }
    );
    public static void submit(Runnable runnable){
        if (runnable != null){
            WORKER_POOL.submit(() -> {
                try {
                    runnable.run();
                } catch (Exception e){
                    LOGGER.error("Action was interrupted ", e);
                }
            });
        }
    }
}
