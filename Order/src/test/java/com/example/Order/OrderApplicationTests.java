package com.example.Order;

import com.example.Order.repo.OrderRepository;
import com.example.Order.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrderApplicationTests {

    private OrderRepository repoMock;
    private OrderService service;

    @BeforeEach
    public void setUp(){
        repoMock = mock(OrderRepository.class);
        service = new OrderService(repoMock);
    }

    @Test
    public void testPlaceOrderCallsRepo(){
        boolean result = service.placeOrder("book", 2);

        assertTrue(result);

        verify(repoMock, times(1)).save(anyString(), anyInt());
    }

    @Test
    public void testPlaceOrderInvalidQuantity(){
        boolean result = service.placeOrder("book", -1);

        assertFalse(result);

        verify(repoMock, never()).save(anyString(), anyInt());
    }
}
