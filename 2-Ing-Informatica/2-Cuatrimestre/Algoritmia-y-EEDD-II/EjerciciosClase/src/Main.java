import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        HashMap<Character, Integer> map;
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            map = new HashMap<>();
            String input = sc.nextLine();
            String [] s = input.split(" ");
            for (Character c : s[0].toCharArray()) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }

            for (Character c : s[1].toCharArray()) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);
                    if (map.get(c) == 0) {
                        map.remove(c);
                    }
                }
            }

            if (map.isEmpty()) {
                System.out.println("GANA");
            } else {
                System.out.println("PIERDE");
            }
        }
    }
}