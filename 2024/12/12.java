import java.nio.file.*;
import java.io.*;

class Main {
  static int[][] directions = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

  public static void main(String[] args) throws IOException {
    long sum = 0L, sum2 = 0L;
    var grid = Files.lines(Paths.get("input.txt"))
        .map(line -> line.toCharArray())
        .toArray(char[][]::new);

    var calculated = new boolean[grid.length][grid[0].length];
    var calculated2 = new boolean[grid.length][grid[0].length];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (!calculated[i][j]) {
          sum += calculateParam(i, j, grid[i][j], grid, calculated, 0) *
              calculateNum(i, j, grid[i][j], grid, calculated2, 0);
        }
      }
    }

    var calculatedP2 = new boolean[grid.length][grid[0].length];
    var calculated2P2 = new boolean[grid.length][grid[0].length];

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (!calculatedP2[i][j]) {
          long side = calculateParamP2(i, j, grid[i][j], grid, calculatedP2);
          long num = calculateNumP2(i, j, grid[i][j], grid, calculated2P2);
          sum2 += side * num;
        }
      }
    }

    System.out.println(sum);
    System.out.println(sum2);
  }

  public static long calculateParam(int i, int j, char current, char[][] grid,
      boolean[][] calculated, int count) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || calculated[i][j])
      return 0;
    if (grid[i][j] != current)
      return 0;
    calculated[i][j] = true;
    long sum = 0L;
    int connected = 0;
    count++;
    for (int[] direction : directions) {
      int dx = i + direction[0], dy = j + direction[1];
      if (dx >= 0 && dy >= 0 && dx < grid.length && dy < grid[i].length) {
        if (grid[dx][dy] == current) {
          connected++;
        }
        ;
        sum += calculateParam(dx, dy, current, grid, calculated, count);
      }
    }
    return sum + (4 - connected);
  }

  public static long calculateNum(int i, int j, char current, char[][] grid,
      boolean[][] calculated, int count) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || calculated[i][j])
      return 0;
    if (grid[i][j] != current)
      return 0;
    calculated[i][j] = true;
    long sum = 0L;
    count++;
    for (int[] direction : directions) {
      int dx = i + direction[0], dy = j + direction[1];
      if (dx >= 0 && dy >= 0 && dx < grid.length && dy < grid[i].length) {
        sum += calculateNum(dx, dy, current, grid, calculated, count);
      }
    }
    return sum + 1;
  }

  public static long calculateParamP2(int i, int j, char current, char[][] grid, boolean[][] calculated) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
      return 0;
    }
    if (calculated[i][j])
      return 0;
    if (grid[i][j] != current) {
      return 1;
    }
    calculated[i][j] = true;
    long sum = 0L;
    int[][] newDir = { { 1, 0 }, { 0, 1 } };
    for (int[] direction : newDir) {
      int dx = i + direction[0], dy = j + direction[1];
      if (grid[dx][dy] == current) {
        sum += calculateParamP2(dx, dy, current, grid, calculated);
      } else {
        sum += 1;
      }
    }
    return sum;
  }

  public static long calculateNumP2(int i, int j, char current, char[][] grid, boolean[][] calculated) {
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || calculated[i][j])
      return 0;
    if (grid[i][j] != current)
      return 0;
    calculated[i][j] = true;
    long sum = 0L;
    for (int[] direction : directions) {
      int dx = i + direction[0], dy = j + direction[1];
      sum += calculateNumP2(dx, dy, current, grid, calculated);
    }
    return sum + 1;
  }
}
