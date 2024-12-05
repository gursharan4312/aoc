import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    var map = new HashMap<Integer, Set<Integer>>();
    Files.lines(Paths.get("input.txt"))
        .map(line -> line.split("\\|"))
        .map(line -> Arrays.stream(line).mapToInt(Integer::parseInt)
            .toArray())
        .forEach(array -> {
          int f = array[0];
          int s = array[1];
          if (map.containsKey(array[0])) {
            map.get(f).add(s);
          } else {
            Set<Integer> set = new HashSet<Integer>();
            set.add(s);
            map.put(f, set);
          }
        });
    var lines = Files.lines(Paths.get("input2.txt"))
        .map(line -> line.split(","))
        .map(line -> Arrays.stream(line).mapToInt(Integer::parseInt)
            .toArray())
        .toArray(int[][]::new);
    int sum = 0;
    int sum2 = 0;
    for (int[] line : lines) {
      boolean valid = true;
      for (int i = line.length - 1; i >= 0; i--) {
        for (int j = i - 1; j >= 0; j--) {
          if (map.containsKey(line[i])) {
            if (map.get(line[i]).contains(line[j])) {
              valid = false;
              int curr = line[i];
              int check = line[j];
              line[j] = curr;
              line[i] = check;
              j++;
            }
          }
        }
      }
      if (valid) {
        int mid = line.length / 2;
        sum += line[mid];
      } else {
        int mid = line.length / 2;
        sum2 += line[mid];
      }
    }
    System.out.println(sum);
    System.out.println(sum2);
  }
}
