import java.util.Enumeration;

public class Pair <T1 extends Enumeration<Integer>, T2>{
    T1 v1;
    T2 v2;

    Pair(T1 v1, T2 v2){
        this.v1 = v1;
        this.v2 = v2;
    }
}
