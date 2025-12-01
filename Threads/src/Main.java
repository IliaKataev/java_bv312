import java.awt.image.AffineTransformOp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //public static int counter = 0;
    //public static Object locker = new Object();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите путь к файлу с числами: ");
        String path = sc.nextLine();

        System.out.println("Введите слово: ");
        String word = sc.nextLine();


        AtomicInteger count = new AtomicInteger(0);

        Thread searchThread = new Thread(new SearchTask(path, word, count));
        searchThread.start();

        try{
            searchThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(count.get() > 0){
            System.out.println("Количество вхождений:" + count.get());
        }else{
            System.out.println("Слово не найдено");
        }

    }

    public static void BasicThreads(){
        //        MyThread t1 = new MyThread();
//        t1.setDaemon(true);
//        t1.setPriority(Thread.MAX_PRIORITY);
//        t1.start();
//        int laps = 10000;
//        while(laps > 0){
//            System.out.println(".");
//            laps--;
//        }

        int v = (new Random()).nextInt(10);
        Thread3 t2 = new Thread3(v);
        Thread t22 = new Thread(t2);
        t22.setDaemon(true);
        v = (new Random()).nextInt(10);
        Thread3 t3 = new Thread3(v);
        Thread t33 = new Thread(t3);
        t33.setDaemon(true);
        System.out.println(t22.getName());
        t22.start();
        t33.start();
        t22.interrupt();

//        while(t2.isAlive() || t3.isAlive()){
//          Так делать не стоит
//        }

//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException _){}

        try{
            t22.join(100);
            t33.join(5000);
        } catch(InterruptedException _){}

    }

    public static void Void(){
        int limit= 10000;
        SyncResource syncResource = new SyncResource();

        Thread t1 = new SyncThread(syncResource);
        Thread t2 = new SyncThread(syncResource);

        t1.start();
        t2.start();
//        IncThread incThread = new IncThread(limit);
//        DecThread decThread = new DecThread(limit);
//
//        incThread.start();
//        decThread.start();
//
        try{
            t1.join();
            t2.join();
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
        System.out.println("counter="+syncResource.getCounter());
    }

    //Задание 3
    public static List<Integer> Zadanie3(String path){
        try{
            return Files.lines(Paths.get(path))
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(Integer::parseInt)
                    .toList();
        }catch(IOException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public  static void TestZadanie3(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите путь к файлу с числами: ");
        String path = sc.nextLine();

        List<Integer> numbers = Zadanie3(path);

        AtomicInteger evenCount = new AtomicInteger();
        AtomicInteger oddCount = new AtomicInteger();

        Thread evenThread = new Thread(new EvenWriter(numbers, "even_numbers.txt", evenCount));
        Thread oddThread = new Thread(new OddWriter(numbers, "odd_numbers.txt", oddCount));

        evenThread.start();
        oddThread.start();

        try{
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Even numbers: " +evenCount.get());
        System.out.println("Odd numbers: " +oddCount.get());
    }


}


/*
Задание 3

Пользователь с клавиатуры вводит путь к файлу, содержащему набор чисел. После чего запускаются
два потока. Первый поток создает новый файл, в который запишет только четные элементы
массива. Второй поток создает новый файл, в который запишет только нечетные элементы массива.
Количество четных и нечетных элементов возвращается в метод main

 */

/*
Задание 4
Пользователь с клавиатуры вводит путь к файлу и слово для поиска.
После чего запускается поток.
Он ищет это слово в файле.
Результат поиска возвращается в main.
*/