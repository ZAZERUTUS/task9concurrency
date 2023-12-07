package org.example;

import org.example.client.ClientObject;
import org.example.server.ServerObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

class IntegrationTest {

    @Test
    public void integrationClientServerVerifyValue() throws ExecutionException, InterruptedException {
        //Given
        List<Integer> dataList = getTestList();
        ServerObject server = new ServerObject();
        ClientObject client = new ClientObject(dataList, server);
        BigDecimal expected = BigDecimal.valueOf(5050);

        //When
        client.sendRequests();
        BigDecimal actual = client.getAccumulator();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void integrationClientServerVerifyLen() throws ExecutionException, InterruptedException {
        //Given
        List<Integer> dataList = getTestList();
        ServerObject server = new ServerObject();
        ClientObject client = new ClientObject(dataList, server);
        int expected = dataList.size();

        //When
        client.sendRequests();
        int actual = server.getSharedResource().size();

        //Then
        Assertions.assertEquals(expected, actual);
    }

    private List<Integer> getTestList() {
        int n = 100;
        List<Integer> dataList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            dataList.add(i);
        }
        return dataList;
    }
}
