import java.io.*;
import java.nio.file.Files;
import java.util.*;

class Main {
  private static final int[][] DIRS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // up, right, down, left

  public static void main(String[] args) throws IOException {
    String infile = args.length >= 1 ? args[0] : "12.in";
    String data = new String(Files.readAllBytes(new File("input.txt").toPath())).strip();

    String[] grid = data.split("\n");
    int R = grid.length;
    int C = grid[0].length();

    Set<String> seen = new HashSet<>();
    int p1 = 0;
    int p2 = 0;

    for (int r = 0; r < R; r++) {
      for (int c = 0; c < C; c++) {
        if (seen.contains(r + "," + c))
          continue;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { r, c });

        int area = 0;
        int perimeter = 0;
        Map<int[], Set<int[]>> perimMap = new HashMap<>();

        while (!queue.isEmpty()) {
          int[] cell = queue.poll();
          int r2 = cell[0];
          int c2 = cell[1];

          if (seen.contains(r2 + "," + c2))
            continue;

          seen.add(r2 + "," + c2);
          area++;

          for (int[] dir : DIRS) {
            int rr = r2 + dir[0];
            int cc = c2 + dir[1];

            if (rr >= 0 && rr < R && cc >= 0 && cc < C && grid[rr].charAt(cc) == grid[r2].charAt(c2)) {
              queue.add(new int[] { rr, cc });
            } else {
              perimeter++;
              perimMap.computeIfAbsent(dir, k -> new HashSet<>()).add(new int[] { r2, c2 });
            }
          }
        }

        int sides = 0;
        for (Map.Entry<int[], Set<int[]>> entry : perimMap.entrySet()) {
          Set<int[]> visitedPerimeter = new HashSet<>();

          for (int[] coord : entry.getValue()) {
            if (!visitedPerimeter.contains(coord)) {
              sides++;
              Deque<int[]> sideQueue = new ArrayDeque<>();
              sideQueue.add(coord);

              while (!sideQueue.isEmpty()) {
                int[] sideCell = sideQueue.poll();
                int sr = sideCell[0];
                int sc = sideCell[1];

                if (visitedPerimeter.contains(sideCell))
                  continue;

                visitedPerimeter.add(sideCell);

                for (int[] dir : DIRS) {
                  int rr = sr + dir[0];
                  int cc = sc + dir[1];

                  if (entry.getValue().contains(new int[] { rr, cc })) {
                    sideQueue.add(new int[] { rr, cc });
                  }
                }
              }
            }
          }
        }

        p1 += area * perimeter;
        p2 += area * sides;
      }
    }

    System.out.println(p1);
    System.out.println(p2);
  }
}
