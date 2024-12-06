import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static int visited = 0;
  public static int numObs = 0;
  public static Set<int[]> seen = new HashSet<>();
  public static Map<Character, int[]> guardPositions = Map.of(
      '^', new int[] { -1, 0 },
      '>', new int[] { 0, 1 },
      'v', new int[] { 1, 0 },
      '<', new int[] { 0, -1 });

  public static void main(String[] args) throws IOException {
    // part1
    var arr = Files.lines(Paths.get("input.txt"))
        .map(line -> line.toCharArray()).toArray(char[][]::new);
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        char ch = arr[i][j];
        if (guardPositions.containsKey(ch)) {
          moveGuard(arr, i, j, true);
        }
      }
    }
    System.out.println(visited);

    // part 2
    var arrCopy = Files.lines(Paths.get("input.txt"))
        .map(line -> line.toCharArray()).toArray(char[][]::new);
    for (int[] coord : seen) {
      int i = coord[0], j = coord[1];
      char[][] gridCopy = new char[arrCopy.length][arrCopy[0].length];
      for (int k = 0; k < arrCopy.length; k++) {
        for (int l = 0; l < arrCopy[0].length; l++) {
          gridCopy[k][l] = arrCopy[k][l];
        }
      }
      gridCopy[i][j] = '0';
      if (isStuckInLoop(gridCopy)) {
        numObs++;
      }
    }
    System.out.println(numObs);
  }

  public static boolean moveGuard(char[][] arr, int x, int y, boolean part1) {
    var guardPosition = new HashSet<String>();
    while (true) {
      String key = "" + x + y + arr[x][y];
      if (guardPosition.contains(key)) {
        return false;
      } else {
        guardPosition.add(key);
      }
      int[] direction = guardPositions.get(arr[x][y]);
      int dx = direction[0] + x, dy = direction[1] + y;
      if (dx >= 0 && dy >= 0 && dx < arr.length && dy < arr[0].length) {
        char ch = arr[x][y], nextCh = arr[dx][dy];
        if (nextCh == '.' || nextCh == 'X') {
          if (nextCh == '.') {
            visited++;
            if (part1)
              seen.add(new int[] { x, y });

          }
          arr[dx][dy] = ch;
          arr[x][y] = 'X';
          x = dx;
          y = dy;
          dx += direction[0];
          dy += direction[1];
        } else {
          if (ch == '^') {
            arr[x][y] = '>';
          } else if (ch == '>') {
            arr[x][y] = 'v';
          } else if (ch == 'v') {
            arr[x][y] = '<';
          } else if (ch == '<') {
            arr[x][y] = '^';
          }
        }
      } else {
        if (guardPositions.containsKey(arr[x][y])) {
          arr[x][y] = 'X';
          visited++;
        }
        break;
      }
    }
    return true;
  }

  public static boolean isStuckInLoop(char[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (guardPositions.containsKey(arr[i][j])) {
          return !moveGuard(arr, i, j, false);
        }
      }
    }
    return false;
  }
}
