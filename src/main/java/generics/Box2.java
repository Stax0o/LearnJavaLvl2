package generics;

import java.util.List;

public class Box2 {
    public static  <T> void transfer(List<? extends T> src, List<? super T> dest) {
        dest.addAll(src);
        src.clear();
    }
}
