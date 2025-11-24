import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String fileName = "Monday.txt";
        String fullName = "";
        String dirName = System.getProperty("user.dir");
        fullName = dirName + File.separator + fileName;
        System.out.println(fullName);
        File file = new File(fullName);
        if(!file.exists()){
            try{
                if(file.createNewFile()){
                    System.out.println("file created!");
                } else
                    System.out.println("Something went wrong!");
            } catch (IOException e){
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }
        }else{
            System.out.println("File already exists!");
        }

        String dirname = dirName + "/cool/nice/dir";
        File d = new File(dirname);
        d.mkdirs();


    }

    public static void fishing(){
        List<Fish> fishes = new ArrayList<>();
        fishes.add(new Fish("Язь", 2.8, 33, 500.0));
        fishes.add(new Fish("Щука", 35, 100, 12250.0));
        fishes.add(new Fish("Бонито", 5.5, 75, 962.0));
        fishes.add(new Fish("Окунь", 3.1, 50, 2131.0));

        System.out.println("Before processing this arraylist");
        for (Fish f: fishes){
            System.out.println(f);
        }
        //предикат
        System.out.println("Рыбы длиннее 50 см (не включительно)");
        getByPredicate(fishes, (f) -> f.getLength() > 50);
        System.out.println("Рыбы тяжелее 3 кг (не включительно)");
        getByPredicate(fishes, (f) -> f.getWeight() > 3.0);

        //компаратор
        fishes.sort(new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return (int) o1.getWeight() * 100 - (int) o2.getWeight() * 100;
            }
        });

        fishes.sort((o1, o2) -> (int) o1.getLength() * 100 - (int) o2.getLength() * 100);

        System.out.println("После сортировки");
        for (Fish f : fishes){
            System.out.println(f);
        }

        fishes.forEach(fish -> System.out.println(fish));
        fishes.stream()
                .filter(f -> f.getPrice() > 1000.0)
                .forEach(f -> f.setPrice( f.getPrice() * 0.9));

        System.out.println("После уменьшения цены на 10% для рыб дороже 1000.0 руб");
        for (Fish f : fishes){
            System.out.println(f);
        }

        System.out.println("Агрегационные операции");
        int number = (int) fishes.stream()
                .filter(f -> f.getPrice() > 1000.0)
                .count();

        double cost = fishes.stream()
                .filter(f->f.getPrice()>1000.0)
                .mapToDouble(Fish::getPrice)
                .sum();

        System.out.println("number: " + number+"; Общая цена: "+cost);
    }

    public static void MapCollections(){
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Leo", 5);
        ages.put("Ilia", 23);

        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "A");
        map.put(2, "B");

        Map<Integer, String> tree = new TreeMap<>();
        tree.put(3, "Three");
        tree.put(1, "One");

        System.out.println(ages);

        WeakHashMap<Object, String> map1 = new WeakHashMap<>();
        Object key = new Object();
        key = null;
        System.gc();

    }

    public static void showGroup(Group group){
        System.out.println(group.bestAlbum());
    }

    public static void syncCollections(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        ConcurrentHashMap<String, Integer> concHashMap = new ConcurrentHashMap<>();
        concHashMap.put("A", 1);
    }

    public static void Labmda(){
        Function<Integer, Integer> square = x -> x*x;
        System.out.println(square.apply(5));
    }

    public static void getByPredicate(List<Fish> fishes, Predicate<Fish> p){
        for (Fish f : fishes){
            if(p.test(f)){
                System.out.println(f);
            }
        }
    }
}

