package com.example.Calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
class CalculatorApplicationTests {
    private LoggerService service;
    private CalculatorApplication app;
    @BeforeEach
    void setUp(){
        service = mock(LoggerService.class);
        app = new CalculatorApplication(new Calculator(), service);
    }
    @Test
    void testCalculateAdd(){
        app.calculate("5 + 3");
        verify(service, times(1)).log("5 + 3 = 8.0");
    }

}
