import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;

class Main {
  public static void main(String args[]) throws IOException {
    var arr = Files.lines(Path.of("input.txt")).map(line -> line.split("\\s+"))
        .map(line -> Arrays.stream(line)
            .map(word -> Arrays.stream(word.split(",")).map(w -> w.replaceAll("[^-\\d.]", ""))
                .mapToInt(Integer::parseInt)
                .toArray())
            .toArray(int[][]::new))
        .toArray(int[][][]::new);

    int[][] space = new int[103][101];
    for (int[][] robort : arr) {
      int y = robort[0][0];
      int x = robort[0][1];
      space[x][y] += 1;
    }

    int seconds = 0;
    for (seconds = 0; seconds < 10000; seconds++) {
      var set = new HashSet<String>();
      for (int[][] robort : arr) {
        int y = robort[0][0], x = robort[0][1], sx = robort[1][1], sy = robort[1][0];
        space[x][y] -= 1;
        int dx = (x + sx % space.length + space.length) % space.length,
            dy = (y + sy % space[0].length + space[0].length) % space[0].length;
        space[dx][dy] += 1;
        robort[0][0] = dy;
        robort[0][1] = dx;
        var key = dx + " " + dy;
        set.add(key);
      }
      if (set.size() == arr.length) {
        System.out.println(seconds);
        for (var item : space) {
          for (var item2 : item) {
            System.out.print(item2 > 0 ? "#" : " ");
          }
          System.out.println();
        }
      }
    }

    // int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
    //
    // for (int i = 0; i < space.length; i++) {
    // int xmid = space.length / 2;
    // if (i == xmid)
    // continue;
    // for (int j = 0; j < space[0].length; j++) {
    // int ymid = space[0].length / 2;
    // if (j == ymid)
    // continue;
    // if (i < xmid) {
    // if (j < ymid)
    // q1 += space[i][j];
    // else
    // q2 += space[i][j];
    // } else {
    // if (j > ymid)
    // q3 += space[i][j];
    // else
    // q4 += space[i][j];
    // }
    // }
    // }

    // System.out.println(q1 + " " + q2 + " " + q3 + " " + q4);
    // System.out.println(q1 * q2 * q3 * q4);

    // for (var item : space) {
    // for (var item2 : item) {
    // System.out.print(item2 + " ");
    // }
    // System.out.println();
    // }
  }

}
