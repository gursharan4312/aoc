import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    var list = Arrays.stream(Files.readString(Paths.get("input.txt")).trim().split("\\s+")).mapToLong(Long::parseLong)
        .toArray();
    long sum = 0;
    var map = new HashMap<String, Long>();
    for (long stone : list) {
      sum += solve(stone, 75, map);
    }
    System.out.println(sum);
  }

  public static long solve(long stone, int t, HashMap<String, Long> map) {
    String key = stone + " " + t;
    if (map.containsKey(key))
      return map.get(key);
    int length = (stone == 0) ? 1 : (int) Math.log10(stone) + 1;
    long divisor = (long) Math.pow(10, length / 2);
    long res = 0;
    if (t == 0) {
      res = 1;
    } else if (stone == 0) {
      res = solve(1, t - 1, map);
    } else if (length % 2 == 0) {
      res = solve(stone / divisor, t - 1, map) + solve(stone % divisor, t - 1, map);
    } else {
      res = solve(stone * 2024, t - 1, map);
    }
    map.put(key, res);
    return res;
  }
}
