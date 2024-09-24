package br.edu.ufcg.ccc.taks;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    private final String task;
    private final UUID clientId;

    public Task(String task, UUID clientId){
        this.task = task;
        this.clientId = clientId;
    }
    @Override
    public String call() throws Exception {
        Random random = new Random();
        Thread.sleep(random.nextLong(100, 2000));
        String response = "Executando tarefa de" + clientId.toString() + " - " + task;
        System.out.println(response);
        return response;
    }
}
