import java.io.*;
import java.nio.file.*;

class Main {
  public static int sum = 0;
  public static int sum2 = 0;

  public static void main(String[] args) throws IOException {
    var arr = Files.lines(Paths.get("input.txt"))
        .map(line -> line.toCharArray()).toArray(char[][]::new);
    String str = "XMAS";
    int[][] directions = {
        { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 },
        { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }
    };
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j] == str.charAt(0)) {
          for (int[] dir : directions) {
            sum += checkXMAS(arr, i, j, str, dir);
          }
        } else if (arr[i][j] == 'A') {
          if (checkX_MAS(arr, i, j)) {
            sum2++;
          }
        }
      }
    }
    System.out.println(sum);
    System.out.println(sum2);
  }

  public static int checkXMAS(char[][] arr, int x, int y, String str, int[] direction) {
    int dx = direction[0], dy = direction[1];
    int i = 0;
    while (x >= 0 && y >= 0 && x < arr.length && y < arr[0].length && i < str.length() && arr[x][y] == str.charAt(i)) {
      if (i >= str.length())
        break;
      if (i == str.length() - 1 && arr[x][y] == str.charAt(i)) {
        return 1;
      }
      x += dx;
      y += dy;
      i++;
    }
    return 0;
  }

  public static boolean checkX_MAS(char[][] arr, int x, int y) {
    boolean topLeftToBottomRight = x >= 1 && y >= 1 && x + 1 < arr.length && y + 1 < arr[0].length &&
        ((arr[x - 1][y - 1] == 'M' && arr[x + 1][y + 1] == 'S') ||
            (arr[x - 1][y - 1] == 'S' && arr[x + 1][y + 1] == 'M'));

    boolean topRightToBottomLeft = x >= 1 && y + 1 < arr[0].length && x + 1 < arr.length && y >= 1 &&
        ((arr[x - 1][y + 1] == 'M' && arr[x + 1][y - 1] == 'S') ||
            (arr[x - 1][y + 1] == 'S' && arr[x + 1][y - 1] == 'M'));

    return topLeftToBottomRight && topRightToBottomLeft;
  }
}
