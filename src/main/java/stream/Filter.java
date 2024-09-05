package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Filter {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt(100));
        }

        List<Integer> listFiltered;
        listFiltered = filter(list, a -> a % 3 == 0 || a % 5 == 0);

        for (Integer i : listFiltered) {
            System.out.println(i);
        }
    }

    private static List<Integer> filter(List<Integer> list, Predicate predicate) {
        List<Integer> result = new ArrayList<>();
        for (int i : list) {
            if (predicate.test(i)) {
                result.add(i);
            }
        }
        return result;
    }
}
