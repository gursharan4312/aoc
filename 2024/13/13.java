import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {
  static HashMap<String, Long> map = new HashMap<String, Long>();

  public static void main(String[] args) throws IOException {
    long sum = 0L;
    List<int[]> processedLines = Files.lines(Paths.get("input.txt"))
        .filter(line -> !line.trim().isBlank())
        .map(line -> line.split("\\s+"))
        .map(line -> Arrays.stream(line)
            .filter(word -> (word.startsWith("X") || word.startsWith("Y")))
            .map(word -> word.replaceAll("\\D", ""))
            .mapToInt(Integer::parseInt).toArray())
        .collect(Collectors.toList());

    List<List<int[]>> groupedLines = IntStream.range(0, processedLines.size())
        .boxed()
        .collect(Collectors.groupingBy(index -> index / 3))
        .values()
        .stream()
        .map(group -> group.stream()
            .map(processedLines::get)
            .collect(Collectors.toList()))
        .collect(Collectors.toList());

    for (List<int[]> group : groupedLines) {
      sum += solve(group.get(0), group.get(1), group.get(2)[0], group.get(2)[1], 0);
      map.clear();
    }
    System.out.println(sum);
    sum = 0;
    for (List<int[]> group : groupedLines) {
      sum += solve2(group.get(0), group.get(1), 10000000000000L + group.get(2)[0], 10000000000000L + group.get(2)[1]);
      map.clear();
    }
    System.out.println(sum);
  }

  public static long solve(int[] a, int[] b, long targetX, long targetY, long cost) {
    String key = targetX + " " + targetY;
    if (targetX < 0 || targetY < 0)
      return 0;
    if (targetY == 0 && targetX == 0)
      return cost;
    if (map.containsKey(key))
      return map.get(key);

    long cost1 = solve(a, b, targetX - a[0], targetY - a[1], cost + 3L);
    long cost2 = solve(a, b, targetX - b[0], targetY - b[1], cost + 1L);

    long res = 0;
    if (cost1 == 0 && cost2 == 0)
      res = 0;
    else if (cost1 == 0 && cost2 != 0)
      res = cost2;
    else if (cost1 != 0 && cost2 == 0)
      res = cost1;
    else
      res = Math.min(cost1, cost2);

    map.put(key, res);
    return res;
  }

  public static long solve2(int[] a, int[] b, long c1, long c2) {
    int a1 = a[0], a2 = a[1], b1 = b[0], b2 = b[1];

    double determinant = a1 * b2 - a2 * b1;
    if (determinant == 0) {
      return 0;
    } else {
      double x = (1 * b2 - c2 * b1) / determinant;
      double y = (a1 * c2 - a2 * c1) / determinant;
      if (x == Math.floor(x) && y == Math.floor(y)) {
        if (Math.floor(x) == x && Math.floor(y) == y)
          return (long) ((x * 3 + y * 1));
        return 0;
      }
      return 0;
    }
  }
}
