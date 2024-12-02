import java.io.*;
import java.nio.file.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    List<Integer> arr1 = new ArrayList<>();
    List<Integer> arr2 = new ArrayList<>();

    // File myFile = new File("1-data.txt");
    // Scanner myReader = new Scanner(myFile);
    // while (myReader.hasNextLine()) {
    // String line = myReader.nextLine();
    // String[] items = line.split(" ");
    // arr1.add(Integer.parseInt(items[0]));
    // arr2.add(Integer.parseInt(items[1]));
    // }
    // myReader.close();

    // String str = new String(Files.readAllBytes(Paths.get("1-data.txt")));
    // for (String line : str.trim().split("\n")) {
    // String[] parts = line.split("\\s{3,}");
    // arr1.add(Integer.parseInt(parts[0]));
    // arr2.add(Integer.parseInt(parts[1]));
    // }

    List<String> lines = Files.readAllLines(Paths.get("1-data.txt"));
    for (String line : lines) {
      String[] parts = line.split("   ");
      arr1.add(Integer.parseInt(parts[0]));
      arr2.add(Integer.parseInt(parts[1]));
    }

    Collections.sort(arr1);
    Collections.sort(arr2);

    int sum = 0;
    for (int i = 0; i < arr1.size(); i++) {
      sum += Math.abs(arr1.get(i) - arr2.get(i));
    }
    System.out.println(sum);

    sum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : arr2) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (int num : arr1) {
      sum += map.getOrDefault(num, 0) * num;
    }
    System.out.println(sum);
  }
}
