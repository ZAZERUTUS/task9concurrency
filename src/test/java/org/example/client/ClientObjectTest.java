package org.example.client;

import org.example.server.ServerObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;


class ClientObjectTest {

    private ServerObject serverMock;

    @BeforeEach
    void setUp() {
        serverMock = Mockito.mock(ServerObject.class);
    }

    @Test
    public void sendRequestsAndVerifyListAfterRequests() throws InterruptedException, ExecutionException {
        //Given
        List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5);
        ClientObject client = new ClientObject(dataList, serverMock);

        Mockito.when(serverMock.processRequest(Mockito.anyInt()))
                .thenReturn(15);

        //When
        client.sendRequests();

        //Then
        Assertions.assertEquals(0, client.getLenRequests());
    }

    @Test
    public void sendRequestsWithVerifyCount() throws InterruptedException, ExecutionException {
        //Given
        List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5);
        ClientObject client = new ClientObject(dataList, serverMock);

        Mockito.when(serverMock.processRequest(Mockito.anyInt()))
                .thenReturn(15);

        //When
        client.sendRequests();

        //Then
        Assertions.assertEquals(BigDecimal.valueOf(15), client.getAccumulator());
    }

    @Test
    public void sendRequestsForAllData() throws InterruptedException, ExecutionException {
        //Given
        List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5);
        ClientObject client = new ClientObject(dataList, serverMock);

        Mockito.when(serverMock.processRequest(Mockito.anyInt()))
                .thenReturn(15);

        //When
        client.sendRequests();

        //Then
        for (int value : dataList) {
            Mockito.verify(serverMock).processRequest(value);
        }
    }

    @Test
    public void getLenRequests() {
        List<Integer> dataList = Arrays.asList(1, 2, 3, 4, 5);
        ClientObject client = new ClientObject(dataList, serverMock);

        Assertions.assertEquals(5, client.getLenRequests());
    }
}