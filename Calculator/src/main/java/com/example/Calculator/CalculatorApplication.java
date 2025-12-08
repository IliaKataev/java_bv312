package com.example.Calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.DefaultLifecycleProcessor;

import java.util.Scanner;

@SpringBootApplication
public class CalculatorApplication {

    private  final Calculator calculator;
    private final LoggerService  service;


    public CalculatorApplication(Calculator calculator, LoggerService service) {
        this.calculator = calculator;
        this.service = service;

    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: <число 1> <операция> <число 2>. Чтобы выйти, напишите exit ");

        while (true){
            System.out.print("> ");
            String line = scanner.nextLine();
            if(line.equalsIgnoreCase("exit")) break;
            try{
                calculate(line);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    void calculate(String line) {
        String[] parts = line.split(" ");
        if(parts.length != 3){
            System.out.println("Некорректный ввод");
            return;
        }

        double a = Double.parseDouble(parts[0]);
        double b = Double.parseDouble(parts[2]);
        String op = parts[1];
        double result;

        switch(op){
            case "+": result = calculator.add(a,b); break;
            case "-": result = calculator.subtract(a,b); break;
            case "*": result = calculator.multiply(a,b); break;
            case "/": result = calculator.divide(a,b); break;
            default:
                System.out.println("Неизвестная операция");
                return;
        }

        System.out.println(result);
        service.log(line + " = " + result);
    }


    public static void main(String[] args) {
        Calculator calculator1 = new Calculator();
        LoggerService logger = new LoggerService();
        new CalculatorApplication(calculator1, logger).run();

    }

}

//Calculator.java
/*

double add - сложение
double subtract - вычитание
double multiply - умножение
double divide - деление (обработка деления на 0)

возвращать нужно результат операции
записывать в консоль в таком формате: A ? B

5 + 3
8.0

10/0
Невозможно поделить на 0
 */


//LoggerService.java
/*
void log(String message) - не должно происходить действительной записи в лог. Это заглушка для мока
 */

//Требования к тестам
// JUnit 5, LoggerService должен мокаться через Мокито и проверять, что log вызвается с правильной строкой
// verity(loggerMock. times(1)).log("5 + 3 = 8.0")