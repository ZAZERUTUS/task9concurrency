package org.example.server;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class ServerObject {

    private final List<Integer> sharedResource = Collections.synchronizedList(new ArrayList<>());
    private final Lock lock = new ReentrantLock();

    public int processRequest(int value) {
        lock.lock();
        try {
            int processingTime = new Random().nextInt(901) + 100; // Диапазон от 100 до 1000 мс
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            sharedResource.add(value);
            System.out.println("In resource added - " + value + " count - " + sharedResource.size());
        } finally {
            lock.unlock();
        }
        return sharedResource.size();
    }
}
