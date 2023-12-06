package org.example.task;

import org.example.server.ServerObject;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class RequestTaskObject implements Callable<BigDecimal> {

    private final int value;
    private final ServerObject server;
    private final CountDownLatch latch;

    public RequestTaskObject(int value, ServerObject server, CountDownLatch latch) {
        this.value = value;
        this.server = server;
        this.latch = latch;
    }

    @Override
    public BigDecimal call() {
        try {
            server.processRequest(value);
        } finally {
            latch.countDown();
            System.out.println("Latch - " + latch.getCount());
        }
        return new BigDecimal(value);
    }
}
