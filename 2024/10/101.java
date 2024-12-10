import java.io.*;
import java.nio.file.*;

class Main {

  public static void main(String[] args) throws IOException {
    var arr = Files.lines(Paths.get("test.txt"))
        .map(line -> line.trim().chars().map(c -> Character.getNumericValue(c)).toArray()).toArray(int[][]::new);

    int sum = 0;
    int sum2 = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j] == 0) {
          sum += findTrail(arr, new int[arr.length][arr[0].length], i, j, 0);
          sum2 += findTrail2(arr, new int[arr.length][arr[0].length], i, j, 0);
        }
      }
    }

    System.out.println(sum);
    System.out.println(sum2);
  }

  public static int findTrail(int[][] arr, int[][] visited, int i, int j, int curr) {
    if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length)
      return 0;
    if (arr[i][j] != curr)
      return 0;
    if (visited[i][j] == 1)
      return 0;
    visited[i][j] = 1;
    if (arr[i][j] == 9)
      return 1;
    int res = findTrail(arr, visited, i + 1, j, curr + 1)
        + findTrail(arr, visited, i, j + 1, curr + 1)
        + findTrail(arr, visited, i - 1, j, curr + 1)
        + findTrail(arr, visited, i, j - 1, curr + 1);
    visited[i][j] = 0;
    return res;
  }

  public static int findTrail2(int[][] arr, int[][] visited, int i, int j, int curr) {
    if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || arr[i][j] != curr)
      return 0;
    if (visited[i][j] == 1)
      return 0;
    if (arr[i][j] == 9)
      return 1;
    visited[i][j] = 1;
    int res = findTrail(arr, visited, i + 1, j, curr + 1)
        + findTrail(arr, visited, i, j + 1, curr + 1)
        + findTrail(arr, visited, i - 1, j, curr + 1)
        + findTrail(arr, visited, i, j - 1, curr + 1);
    visited[i][j] = 0;
    return res;
  }
}
