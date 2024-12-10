import java.io.*;
import java.nio.file.*;

class Main {
public static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
  public static void main(String[] args) throws IOException {
    var arr = Files.lines(Paths.get("input.txt"))
    .map(line -> line.trim().chars().map(c -> Character.getNumericValue(c)).toArray()).toArray(int[][]::new);

    int sum=0;
    int sum2=0;
    for(int i=0;i<arr.length;i++){
      for(int j=0;j<arr[0].length;j++){
        if(arr[i][j]==0){
           sum += findTrail(arr,new int[arr.length][arr[0].length],i,j,0);
           sum2 += findTrail2(arr,new int[arr.length][arr[0].length],i,j,0);
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
    if (arr[i][j] == 9 && curr==9){
      visited[i][j] = 1;
      return 1;
    }
    int res = 0;
    for (int[] dir : directions) {
      int dx = i + dir[0], dy = j + dir[1];
      res += findTrail(arr, visited, dx, dy, curr + 1);
    }
    return res;
  }

  public static int findTrail2(int[][] arr, int[][] visited, int i, int j, int curr){
    if(i<0||j<0||i>=arr.length||j>=arr[0].length) return 0;
    if(arr[i][j]!=curr)return 0;
    if(visited[i][j]==1) return 0;
    if(arr[i][j] == 9 && curr==9) return 1;
    visited[i][j] = 1;
    int res = 0;
    for (int[] dir : directions) {
      int dx = i + dir[0], dy = j + dir[1];
      res += findTrail2(arr, visited, dx, dy, curr + 1);
    }
    visited[i][j]=0;
    return res;
  }
}
