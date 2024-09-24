package br.edu.ufcg.ccc;

import br.edu.ufcg.ccc.taks.Task;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<Integer, Task> concurrentHashMap = new ConcurrentHashMap<>();
        BlockingDeque<Task> taskBlockingDeque = new LinkedBlockingDeque<>();
        AtomicInteger threadsCount = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 30; i++) {
            Task task = new Task("Manel lindo!", UUID.randomUUID());
            taskBlockingDeque.put(task);
        }

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            for (int i = 0; i < 10; i++) {
                Task task = taskBlockingDeque.removeFirst();
                executorService.submit(task);
                concurrentHashMap.put(threadsCount.incrementAndGet(), task);
            }
        }, 0,30,  TimeUnit.SECONDS);
    }
}