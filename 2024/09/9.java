import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static void main(String args[]) throws IOException {
    var arr = Files.readString(Paths.get("input.txt")).trim().chars().map(c -> Character.getNumericValue(c))
        .toArray();

    var list = new ArrayList<Integer>();

    int val = 0;
    for (int i = 0; i < arr.length; i += 2) {
      for (int j = 0; j < arr[i]; j++) {
        list.add(val);
      }
      val++;
      if (i + 1 < arr.length) {
        for (int j = 0; j < arr[i + 1]; j++) {
          list.add(-1);
        }
      }
    }

    List<Integer> listCopy = new ArrayList<>(list); // for part 2

    int left = 0, right = list.size() - 1;
    while (left < right) {
      if (list.get(left) != -1)
        left++;
      else if (list.get(right) == -1)
        right--;
      else {
        list.set(left, list.get(right));
        list.set(right, -1);
        left++;
        right--;
      }
    }

    long sum = 0;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) != -1)
        sum += i * list.get(i);
    }
    System.out.println(sum);

    // part 2
    left = 0;
    right = listCopy.size() - 1;
    while (right > 0) {
      int size = 1;
      while (right > 0 && listCopy.get(right).equals(-1)) {
        right--;
      }
      while (right > 0 && (listCopy.get(right).equals(listCopy.get(right - 1)))) {
        right--;
        size++;
      }
      if (right < 0)
        break;
      while (left < listCopy.size()) {
        if (listCopy.get(left).equals(-1)) {
          int spaces = 0, j = left;
          while (j < listCopy.size() && listCopy.get(j++).equals(-1))
            spaces++;
          if (spaces >= size && left < right) {
            int l = 0;
            while (l < spaces && l < size) {
              listCopy.set(left + l, listCopy.get(right + l));
              listCopy.set(right + l, -1);
              l++;
            }
          } else if (left >= right)
            break;
          left++;
        }
        left++;
      }
      right--;
      left = 0;
    }

    long sum2 = 0;
    for (int i = 0; i < listCopy.size(); i++) {
      if (listCopy.get(i) != -1)
        sum2 += i * listCopy.get(i);
    }
    System.out.println(sum2);
  }
}
