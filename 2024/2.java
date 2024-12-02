import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get("2-data.txt"));
    int res = 0;
    for (String line : lines) {
      String[] parts = line.trim().split(" ");
      if(isSafe(parts)) res++;
      else{
        for(int i=0;i<parts.length;i++){
          String[] newArr = new String[parts.length-1];
          for (int j = 0, k = 0; j < parts.length; j++) {
            if (j != i) {
              newArr[k++] = parts[j];
            }
          }
          if(isSafe(newArr)){
            res++;
            break;
          }
        }
      }
    }
    System.out.println(res);
  }

  private static  boolean isSafe(String[] parts){
    boolean increasing = true,decreasing=true;
    for(int i=1;i<parts.length;i++){
      int f= Integer.valueOf(parts[i-1]);
      int s = Integer.valueOf(parts[i]);
      int diff = s-f;
      if((Math.abs(diff) > 3 || Math.abs(diff) < 1)){
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
