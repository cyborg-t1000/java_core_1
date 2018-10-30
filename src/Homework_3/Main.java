package Homework_3;

import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String[] poem = {"Ехал", "Грека", "через", "реку", "Видит", "Грека", "в", "реке", "рак", "Сунул", "в", "реку", "руку", "Грека", "Рак", "за", "руку", "Греку", "цап"};
        System.out.println("Уникальные слова: " + Arrays.toString(uniq(poem)));
        System.out.println("Посчёт слов: " + wordCount(poem));

        PhoneBook pb = new PhoneBook();
        pb.add("Иванов", "123-456-7890");
        pb.add("Иванов", "444-456-7890");
        pb.add("Петров", "777-777-7777");
        pb.add("Сидоров", "333-337-3333");
        pb.show("Иванов");
        pb.show("Петров");

        checkPass("we");
        checkPass("1Aa12345");
    }

    private static String[] uniq(String[] input) {
        HashSet<String> hs = new HashSet<>();
        Collections.addAll(hs, input);
        return hs.toArray(new String[0]);
    }

    private static Map<String, Integer> wordCount (String[] input) {
        Map<String, Integer> hm = new HashMap<>();
        for (String i: input) {
            Integer cnt = hm.get(i);
            hm.put(i, cnt  == null ? 1 : ++cnt);
        }
        return hm;
    }

    private static void checkPass(String pass) {
        System.out.println("Пароль " + pass + " проходит? " + Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", pass));
    }
}
