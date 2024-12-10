import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static void main(String args[]) throws IOException {
    long sum = 0;
    long sum2 = 0;
    var lines = Files.lines(Paths.get("input.txt"))
        .map(line -> line.replace(":", ""))
        .map(line -> line.split("\\s+"))
        .map(report -> Arrays.stream(report)
            .mapToLong(Long::parseLong)
            .toArray())
        .toArray(long[][]::new);

    for (long[] line : lines) {
      if (canCalibrate(line[1], line[0], 2, line))
        sum += line[0];
    }

    for (long[] line : lines) {
      if (canCalibratePart2(line[1], line[0], 2, line))
        sum2 += line[0];
    }

    System.out.println(sum);
    System.out.println(sum2);
  }

  public static boolean canCalibrate(long currSum, long sum, int i, long[] line) {
    if (currSum == sum && i == line.length)
      return true;
    if (currSum > sum || i >= line.length || line.length <= 1)
      return false;
    return canCalibrate(currSum + line[i], sum, i + 1, line)
        || canCalibrate(currSum * line[i], sum, i + 1, line);
  }

  public static boolean canCalibratePart2(long currSum, long sum, int i, long[] line) {
    if (currSum == sum && i >= line.length)
      return true;
    if (currSum > sum || i >= line.length || line.length <= 1)
      return false;
    return canCalibratePart2(currSum + line[i], sum, i + 1, line)
        || canCalibratePart2(currSum * line[i], sum, i + 1, line)
        || canCalibratePart2(Long.parseLong(("" + currSum + line[i])), sum, i + 1, line);
  }
}
