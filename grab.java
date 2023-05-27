
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class grab {
  public static void main(String[] args) {
  System.out.println(playername());
  System.out.println(money());
  System.out.println(cursorlvl());
  }
 //^
 //
 //
 //STATS
  public static String playername() {
      File readtxt = new File("stats/playername");
      try (Scanner read = new Scanner(readtxt)) {
        String playername0 = read.nextLine();
        read.close();
        return playername0;
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
      }
  }

  public static int money() {
    File readtxt = new File("stats/money");
    try (Scanner read = new Scanner(readtxt)) {
      int money0 = read.nextInt();
      read.close();
      return money0;
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 69;
    }
    }

    public static int cursorlvl() {
      File readtxt = new File("stats/cursorlvl");
      try (Scanner read = new Scanner(readtxt)) {
        int cursorlvl0 = read.nextInt();
        read.close();
        return cursorlvl0;
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return 69;
      }
}


}