import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.IntStream;

class Main {
  public static void main(String[] args) throws IOException {
    var p1 = Files.lines(Paths.get("input.txt"))
        .map(line -> line.split("\\s+"))
        .map(report -> Arrays.stream(report)
            .mapToInt(Integer::parseInt)
            .toArray())
        .filter(Main::isSafe)
        .count();

    System.out.println(p1);

    var p2 = Files.lines(Paths.get("input.txt"))
        .map(line -> line.split("\\s+"))
        .map(arr -> Arrays.stream(arr).mapToInt(Integer::parseInt).toArray())
        .filter(arr -> {
          if (isSafe(arr))
            return true;
          for (int i = 0; i < arr.length; i++) {
            int excludedIndex = i;
            int[] newArr = IntStream.range(0, arr.length)
                .filter(index -> index != excludedIndex)
                .map(index -> arr[index])
                .toArray();
            if (isSafe(newArr))
              return true;
          }
          return false;
        }).count();
    System.out.println(p2);
  }

  private static boolean isSafe(int[] parts) {
    boolean increasing = true, decreasing = true;
    for (int i = 1; i < parts.length; i++) {
      int diff = parts[i] - parts[i - 1];
      if ((Math.abs(diff) > 3 || Math.abs(diff) < 1)) {
        return false;
      }
      if (diff < 0) {
        increasing = false;
      } else if (diff > 0) {
        decreasing = false;
      }
    }
    return (increasing || decreasing);
  }
}
