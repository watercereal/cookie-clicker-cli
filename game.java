package ccli;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class game implements Runnable {

    public static Scanner sc = new Scanner(System.in);
    public static Boolean calcps = true;
    public static double CPS = cps();
  
    public static String playername = playername();
    public static double money = mny();
    public static int cursorlvl = curs();
    public static int grandmalvl = gran();

    
    //settings
    public static Boolean autosave = false; //togglle whether the game automatically saves after each upgrade purchase
    public static void main(String [] args) {
        clear();
        game obj = new game();
        Thread cps = new Thread(obj); 
        cps.start();      

        System.out.print("player: ");
        System.out.println(playername());
        System.out.print("money: ");
        System.out.println(money);
        System.out.print("cursor: ");
        System.out.println(cursorlvl);
        System.out.print("grandma: ");
        System.out.println(grandmalvl);
        System.out.print("CPS: ");
        System.out.println(CPS);



        System.out.println("Welcome to Cookie Clicker - cli edition");

        System.out.println("0: save and exit");
        System.out.println("1: click cookie (also updates display)");
        System.out.println("2: upgrade buildings");
        int choice = sc.nextInt();
        if (choice == 0) {
            save();
            System.exit(0);
        } else if (choice == 1) {
            money = money+CPS;
            clear();
            main(args);
        } else if (choice == 2) {
            upgrade();
        } else if (choice == 9) {
            wipe();
                }
        main(args);
    }

    //
    public void run() {
        while (calcps = true) {
        try {
            money = money+CPS;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        if (displaying == true) {
       
        System.out.println("Cookies: "+money);
        System.out.println("CPS: "+CPS);
        System.out.println("");
        System.out.println("Cursors: "+cursorlvl);
        System.out.println("Grandma: "+grandmalvl);
        System.out.println("");
        System.out.println("");
        
        
        }
    }
      }
    public static Boolean displaying = false;
walkffkas
    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }


    public static void upgrade() {
        clear();
        
        System.out.println("0: exit to menu");
        System.out.println("1: cursor: "+"cost: "+cursorCost()+" owned: "+cursorlvl);
        System.out.println("2: grandma: "+"cost: "+grandmaCost()+" owned: "+grandmalvl);
        System.out.println("you have: $"+money);
        System.out.println("");
        int choice = sc.nextInt(3);
        if (choice == 0) {
            main(null);
        } else if (choice == 1) {
            if (money>cursorCost()) {
                cursorlvl++;
                money = money-cursorCost();
                System.out.println("bought cursor");
                if (autosave = true) {
                    save();
                }
            }
        } else if (choice == 2) {
            if (money>=grandmaCost()) {
                grandmalvl++;
                money = money-grandmalvl;
                if (autosave = true) {
                    save();
                }
            } else {
                System.out.println("you do not have enough money");
                System.out.println("have: "+money+" need: "+grandmaCost());
            }

        }
        upgrade();



    }   
    public static void save() {
        try {
        FileWriter moneyq = new FileWriter("stats/money");
        FileWriter cursorq = new FileWriter("stats/cursorlvl");
        FileWriter grandmaq = new FileWriter("stats/grandmalvl");

        moneyq.write(money + "");
        cursorq.write(cursorlvl + "");
        grandmaq.write(grandmalvl + "");
        moneyq.close();
        cursorq.close();
        grandmaq.close();

        System.out.println("data saved");

        } catch (IOException e) {  
            e.printStackTrace();
        }

    }
    public static void wipe() {
        clear();
        System.out.println("are you SURE you would like to reset all game data");
        System.out.println("stats after wipe:");
        System.out.println("playername: whatever it was before");
        System.out.println("money: 0");
        System.out.println("cursor level: 1");
        System.out.println("other building levels: 0");
        System.out.println("cps: 1");
        System.out.println("");
        System.out.println("0 = no");
        System.out.println("1 = yes");
        int confirm = sc.nextInt();
        if (confirm == 0) {
            main(null);
        } else if (confirm == 1) {
            money = 0;
            cursorlvl = 1;
            grandmalvl = 0;
            save();
            System.exit(0);

        }







        System.exit(0);
    }



 //
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
        return "null";
      }
  }

  public static double mny() {
    File readtxt = new File("stats/money");
    try (Scanner read = new Scanner(readtxt)) {
      double money0 = read.nextDouble();
      read.close();
      return money0;
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 404;
    }
    }

    public static int curs() {
      File readtxt = new File("stats/cursorlvl");
      try (Scanner read = new Scanner(readtxt)) {
        int cursorlvl0 = read.nextInt();
        read.close();
        return cursorlvl0;
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return 404;
      }
}

  public static int gran() {
    File readtxt = new File("stats/grandmalvl");
    try (Scanner read = new Scanner(readtxt)) {
     int level0 = read.nextInt();
      read.close();
      return level0;
   } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 404;
  }
}
//MATH
//MATH
//MATH

    
    public static double cps() {
        
        double cps0 = (1)+(cursorlvl*1)+(grandmalvl*1.2); 
        return cps0;
    }
    //building costs
    public static int cursorCost() {
        int cost0 = 1+(game.cursorlvl*2);
        return cost0;
    }
    public static int grandmaCost() {
        int cost0 = 1+(game.grandmalvl*2);
        return cost0;
    }



}