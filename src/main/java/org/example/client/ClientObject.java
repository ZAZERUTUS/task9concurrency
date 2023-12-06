package org.example.client;

import lombok.Getter;
import org.example.server.ServerObject;
import org.example.task.RequestTaskObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class ClientObject {
    private final List<Integer> dataList;
    private BigDecimal accumulator;
    private final ServerObject server;
    private final ExecutorService executorService;

    public ClientObject(List<Integer> dataList, ServerObject server) {
        this.dataList = new ArrayList<>(dataList);
        this.server = server;
        this.executorService = Executors.newFixedThreadPool(dataList.size());
        this.accumulator = BigDecimal.ZERO;
    }

    public void sendRequests() throws InterruptedException, ExecutionException {
        CountDownLatch latch = new CountDownLatch(dataList.size());

        int len = dataList.size();
        for (int i = 1; i <= len; i++) {
            int index = new Random().nextInt(dataList.size());
            int value = dataList.remove(index);

            RequestTaskObject task = new RequestTaskObject(value, server, latch);
            BigDecimal localVal = executorService.submit(task).get();
            accumulator = accumulator.add(localVal);
        }

        executorService.shutdown();
    }

    public int getLenRequests() {
        return dataList.size();
    }
}
