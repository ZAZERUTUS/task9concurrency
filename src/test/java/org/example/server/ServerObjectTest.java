package org.example.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerObjectTest {

    @Test
    public void processRequestVerifyValueInResource() throws InterruptedException {
        //Given
        ServerObject server = new ServerObject();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(5);

        //When
        for (int i = 1; i <= 5; i++) {
            int value = i;
            executorService.submit(() -> {
                server.processRequest(value);
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();

        //Then
        for (int i = 1; i <= 5; i++) {
            Assertions.assertEquals(i, server.getSharedResource().get(i - 1));
        }
    }

    @Test
    public void processRequestVerifyLenResource() throws InterruptedException {
        //Given
        ServerObject server = new ServerObject();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(5);

        //When
        for (int i = 1; i <= 5; i++) {
            int value = i;
            executorService.submit(() -> {
                server.processRequest(value);
                latch.countDown();
            });
        }
        latch.await();
        executorService.shutdown();

        //Then
        Assertions.assertEquals(5, server.getSharedResource().size());
    }
}
