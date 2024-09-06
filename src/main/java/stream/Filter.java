package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Filter {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            list.add(random.nextInt(100, 200));
        }

        List<String> list2 = list.stream()
                .filter(n -> n % 2 == 0 && n % 5 == 0)
                .map(Math::sqrt)
                .map(scrt -> "Sqrt: " + scrt)
                .collect(Collectors.toList());

        for (String s : list2) {
            System.out.println(s);
        }
        System.out.println(list2.size());

        list.stream()
                .sorted()
                .limit(5)
                .forEach(System.out::println);
    }
}
