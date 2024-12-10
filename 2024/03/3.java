import java.io.*;
import java.nio.file.*;

class Main {
  public static void main(String[] args) throws IOException {
    int sum = 0, left = 0, right = 0;
    boolean enable = true;
    String str = Files.readString(Paths.get("input.txt"));
    for (int i = 0; i < str.length(); i++) {
      if (str.length() >= i + 4 && str.substring(i, i + 4).equals("mul(")) {
        int j = i + 4;
        String num = "";
        while (isInteger("" + str.charAt(j))) {
          num += str.charAt(j);
          j++;
        }
        if (num.length() <= 3 && isInteger(num)) {
          left = Integer.valueOf(num);
        }
        if (isInteger(num)) {
          num = "";
          j++;
          while (isInteger("" + str.charAt(j))) {
            num += str.charAt(j);
            j++;
          }
          if (num.length() <= 3 && isInteger(num)) {
            right = Integer.valueOf(num);
          }
        }
        if (str.charAt(j) == ')' && enable == true) {
          sum += left * right;
          left = 0;
          right = 0;
        } else {
          left = 0;
          right = 0;
        }
      } else if (str.length() >= i + 4 && str.substring(i, i + 4).equals("do()")) {
        enable = true;
      } else if (str.length() >= i + 7 && str.substring(i, i + 7).equals("don't()")) {
        enable = false;
      }
    }
    System.out.println(sum);
  }

  public static boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
