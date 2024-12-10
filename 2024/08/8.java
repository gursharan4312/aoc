import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static void main(String args[]) throws IOException {
    int sum = 0,sum2=0;
    var lines = Files.lines(Paths.get("input.txt"))
        .map(line -> line.toCharArray())
        .toArray(char[][]::new);

    var map = new HashMap<Character,List<int[]>>();

    for (int i=0;i<lines.length;i++) {
      for(int j=0;j<lines[i].length;j++){
        char ch = lines[i][j];
        if(Character.toString(ch).matches("[a-zA-Z0-9]")){
            map.computeIfAbsent(ch,k->new ArrayList<int[]>()).add(new int[]{i,j});
        }
      }
    }

    for(char ch : map.keySet()){
      List<int[]> entries = map.get(ch);
      for(int i=0;i<entries.size();i++){
          int[] curr = entries.get(i);
        for(int j=i+1;j<entries.size();j++){
          int[] next = entries.get(j);
          int dx = curr[0] + (curr[0] - next[0]), dy = curr[1] + (curr[1] - next[1]);
          int dx2 = next[0] + (-curr[0] + next[0]), dy2 = next[1] + (-curr[1] + next[1]);
          if(dx>=0 && dy>=0 && dx<lines.length &&dy<lines[0].length &&  !Character.toString(lines[dx][dy]).matches("[a-zA-Z0-9]")){
            lines[dx][dy] = '#';
            sum++;
          }
          if(dx2>=0 && dy2>=0 && dx2<lines.length && dy2<lines[0].length &&  !Character.toString(lines[dx2][dy2]).matches("[a-zA-Z0-9]")){
            lines[dx2][dy2] = '#';
            sum++;
          }
        }
      }
    }

    // part 2
    lines = Files.lines(Paths.get("input.txt"))
        .map(line -> line.toCharArray())
        .toArray(char[][]::new);

    for(char ch : map.keySet()){
      List<int[]> entries = map.get(ch);
      for(int i=0;i<entries.size();i++){
        int[] curr = entries.get(i);
        if(entries.size()>1) sum2++;
        for(int j=i+1;j<entries.size();j++){
          int[] next = entries.get(j);
          int diffX = curr[0] - next[0], diffY = curr[1]-next[1];
          int dx = curr[0] + diffX, dy = curr[1] + diffY;
          while(dx>=0 && dy>=0 && dx<lines.length &&dy<lines[0].length ){
            if(!Character.toString(lines[dx][dy]).matches("[a-zA-Z0-9#]")){
              lines[dx][dy] = '#';
              sum2++;
            }
            dx += diffX;
            dy += diffY;
          }
          dx = next[0] - diffX;
          dy = next[1] - diffY;
          while(dx>=0 && dy>=0 && dx<lines.length &&dy<lines[0].length){
            if(!Character.toString(lines[dx][dy]).matches("[a-zA-Z0-9#]")){
              lines[dx][dy] = '#';
              sum2++;
            }
            dx -= diffX;
            dy -= diffY;
          }
        }
      }
    }

    System.out.println(sum);
    System.out.println(sum2);
    for(char[] line:lines){
      System.out.println(new String(line));
    }
  }
}
