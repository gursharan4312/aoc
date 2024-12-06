import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static int visited = 0;
  public static Map<Character, int[]> guardPositions = Map.of(
      '^', new int[] { -1, 0 },
      '>', new int[] { 0, 1 },
      'v', new int[] { 1, 0 },
      '<', new int[] { 0, -1 });

  public static void main(String[] args) throws IOException {
    var arr = Files.lines(Paths.get("input.txt"))
        .map(line -> line.toCharArray()).toArray(char[][]::new);

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        char ch = arr[i][j];
        if (guardPositions.containsKey(ch)) {
          moveGuard(arr, i, j);
        }
      }
    }

    System.out.println(visited);
    for (char[] row : arr) {
      System.out.println(new String(row));
    }
  }

  public static void moveGuard(char[][] arr, int x, int y) {
    while (true) {
      int[] direction = guardPositions.get(arr[x][y]);
      int dx = direction[0] + x, dy = direction[1] + y;
      if (dx >= 0 && dy >= 0 && dx < arr.length && dy < arr[0].length) {
        char ch = arr[x][y], nextCh = arr[dx][dy];
        if (nextCh == '.' || nextCh == 'X') {
          if (nextCh == '.')
            visited++;
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
  }
}
