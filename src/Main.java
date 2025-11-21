import java.util.*;

public class Main{
    public static void main(String[] args){

        int i = -1;
        i = i++;
        System.out.println(i);

        if (true){
            i++;
        } else{
            i--;
        }

        switch (i){
            case 3:
                System.out.println(3);
                break;
            default:
                System.out.println("default");
        }



        for (;i < 10;){
            System.out.println(i);
        }

        int[] numbers = new int[]{3,2,1};
        for (int number : numbers){
            System.out.println("number");
        }

        List<Integer> lists = new ArrayList<Integer>();
        lists.add(1);



    }
    static double max(double x, double y, double z){
        double maximum = x>y?x:y;
        maximum=z>maximum?z:maximum;
        return maximum;
    }

}


/**
 *
 *
 *
 */

// JVM - виртуальная машина, спецификация ВМ
// Компилятор - groovy, scala
// байт-код - 205 инструкций + 50 доп.
// Сборщик мусора


// ЖЦ: код.java -> компилятор -> байт-код(код.class) -> ВМ -> ОС -> Аппаратная часть