package org.example;

import org.example.client.ClientObject;
import org.example.server.ServerObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        int n = 100;
        List<Integer> dataList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            dataList.add(i);
        }

        ServerObject server = new ServerObject();
        ClientObject client = new ClientObject(dataList, server);

        try {
            client.sendRequests();
//            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-2");
//
//            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-3");
//            int actualAccumulator = server.getSharedResource().size();
//            System.out.println("-=-=-=-=-=-=-=-=-=-=-=- " + client.getAccumulator());
//
//            if (actualAccumulator == 100 &&
//                    0 == client.getAccumulator().compareTo(BigDecimal.valueOf(5050))) {
//                System.out.println("Test passed successfully!");
//            } else {
//                System.out.println("Test failed!");
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}