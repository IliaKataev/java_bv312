import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //printQuote();
        //choose();
        //chooseSort();
        //StudentExample();

        Shape[]s = new Shape[3];
        s[0] = new Circle(10,10,5,"black");
        s[1] = new Rectangle(10,10,50,50,"yellow");
        s[2] = new Circle(10,10,5,"green");

        for (Shape shape : s) {
            shape.draw();
        }
    }

    public static void StudentExample(){
        Student student = new Student("Иванов Иван Иванович", 21);
        System.out.println(student.toString());

        Aspirant aspirant = new Aspirant("Петров Петр Петрович", 28, "Исследование реляционных баз данных");
        System.out.println(aspirant.toString());

        Student student1 = new Aspirant("Сидоров Глеб Михайлович", 30, "Разработка БД для госуслуг");
        System.out.println(student1.toString());

        System.out.println(student);
    }
    //задание 1
    public static void printQuote(){
        System.out.println("\"Your time is limited,\n \tso don’t waste it\n  \t\tliving someone else’s life\"\n \t\t\tSteve Jobs");
    }

    //задание 6
    public static void choose(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество метров");
        double meters = scanner.nextDouble();

        System.out.println("Во что перевести?");
        System.out.println("1. В мили");
        System.out.println("2. В дюймы");
        System.out.println("3. В ярды");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();

        convert(meters, choice);

    }

    public static void convert(double meters, int choice){
        switch (choice){
            case 1:
                double miles = meters * 0.000621371;
                System.out.println("В милях:" + miles);
                break;
            case 2:
                double inches = meters * 39.37 ;
                System.out.println("В дюймах:" + inches);
                break;
            case 3:
                double yards = meters * 1.09361;
                System.out.println("В милях:" + yards);
                break;
            default:
                System.out.println("неверный ввод");
        }
    }

    //задание 12
    public static void chooseSort(){
        Scanner scanner = new Scanner(System.in);

        int[] array = {5, 0, 4, 1, 5, 8, 2, 9, 157};

        System.out.println("Исходный массив: "+Arrays.toString(array));

        System.out.println("Тип сортировки?");
        System.out.println("1. Пузырьком");
        System.out.println("2. Выбором");
        System.out.println("3. Вставкой");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();

        System.out.println("Порядок сортировки?");
        System.out.println("1. По возрастанию");
        System.out.println("2. По убыванию");
        int order = scanner.nextInt();

        boolean asc = (order == 1);
        int[] arrToSort = Arrays.copyOf(array, array.length);

        long start = System.nanoTime();

        switch (choice){
            case 1:
                bubbleSort(arrToSort, asc);
                System.out.println("Выполняется пузырьковая");
                break;
            case 2:
                selectionSort(arrToSort, asc);
                System.out.println("Выполняется выбором");
                break;
            case 3:
                insertionSort(arrToSort, asc);
                System.out.println("Выполняется вставками");
                break;
            default:
                System.out.println("неверный ввод");
        }

        long end = System.nanoTime();
        long duration = end - start;

        System.out.println("Результаты: " + Arrays.toString(arrToSort));
        System.out.println("Время сортировки: " + duration + " нс");

    }

    public static void bubbleSort(int[] arr, boolean asc){
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = 0; j < arr.length - i - 1; j++){
                if( (asc && arr[j] > arr[j+1]) || (!asc && arr[j] < arr[j+1]) ){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[]arr, boolean asc){
        for(int i = 0; i < arr.length - 1; i++){
            int index = i;

            for (int j = i + 1; j < arr.length; j++){
                if( (asc && arr[j] < arr[index]) || (!asc && arr[j] > arr[index]) ){
                    index = j;
                }
            }

            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[]arr, boolean asc){
        for (int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;

            while(j >= 0 && ((asc && arr[j] > key) || (!asc && arr[j] < key))){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
}

/*
  Задание 1
  Выведите на экран надпись Your time is limited, so don’t waste it living someone else’s life Steve Jobs на разных строках
  “Your time is limited,
  so don’t waste it
  living someone else’s life”
  Steve Jobs
 */


/*

Задание 6
Пользователь вводит с клавиатуры количество метров. В зависимости от выбора пользователя программа переводит
метры в мили, дюймы или ярды

1609,34 метров в миле
1 метр = 39,37 дюймов
1 метр = 1,09361 ярда

 */

/*

Задание 12

Напишите метод, сортирующий массив по убыванию или возрастанию в зависимости от выбора пользователя.
Пользователь может выбрать способ сортировки из трех предложенных. Также после каждого выполнения показать
скорость сортировки данных

Сортировка пузырьком - Время сортировки: 90800 нс
Сортировка выбором - Время сортировки: 86400 нс
Сортировка вставками - Время сортировки: 90000 нс

5281

1. 2, 2581
2. 8
3. 1 1258

 */