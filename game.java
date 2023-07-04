package ccli;
//exceptions
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.io.IOException;

//stat saving
import java.io.File; 
import java.io.FileWriter;

import java.util.Scanner; //user input and stat reading

//idle cookie gain
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    


public class game implements Runnable {

    public static Scanner sc = new Scanner(System.in);
    
    //stats
    public static String playername = playername();
    public static double money = mny();
    public static int cursorlvl = statgrab(new File("stats/cursorlvl"));
    public static int grandmalvl = statgrab(new File("stats/grandmalvl"));
    public static int farmlvl = statgrab(new File("stats/farmlvl"));
    public static int minelvl = statgrab(new File("stats/minelvl"));



    //probably don't change these
    public static boolean init = true; //allows startup process to be run
    public static boolean calcps = true; //controls whether cps in calculated every second.
    public static boolean main; //used to determine if main menu is active
    public static boolean upgrade = false;
    public static void display(String mode) {
        if(main == true) {
        clear();
        System.out.print("player: ");
        System.out.println(playername());
        System.out.print("cookies: ");
        System.out.println(money);
        if (cursorlvl >= 1) {
        System.out.print("cursor: ");
        System.out.println(cursorlvl);
        }
        if (grandmalvl >= 1) {
        System.out.print("grandma: ");
        System.out.println(grandmalvl);
        }
        if(farmlvl >= 1) {
        System.out.print("farm: ");
        System.out.println(farmlvl);
        }
        if(minelvl >= 1) {
            System.out.print("mine: ");
            System.out.println(minelvl);
        }
        System.out.print("CPS: ");
        System.out.println(cps());
        System.out.println("");
        System.out.println("");

        System.out.println("Welcome to Cookie Clicker - cli edition");
        System.out.println("0: save and exit");
        System.out.println("1: click cookie");
        System.out.println("2: upgrade buildings");
        System.out.println("...");
        System.out.println("9: wipe save");
    } else if (upgrade == true) {
        clear();
        
        System.out.println("0: exit to menu");
        if(money>= uniCost(15, cursorlvl)) {
        System.out.println("~*1: Cursor: "+"cost: $"+uniCost(15, cursorlvl)+" owned: "+cursorlvl+"*~");
        } else {
        System.out.println("1: Cursor: "+"cost: $"+uniCost(15, cursorlvl)+" owned: "+cursorlvl);
        } if(money>= uniCost(100, grandmalvl)) {
        System.out.println("~*2: Grandma: "+"cost: $"+uniCost(100, grandmalvl)+" owned: "+grandmalvl+"*~");
        } else {
        System.out.println("2: Grandma: "+"cost: $"+uniCost(100, grandmalvl)+" owned: "+grandmalvl);
        } if(money>= uniCost(1000, farmlvl)) {
            System.out.println("~*3: Farm: "+"cost: $"+uniCost(1000, farmlvl)+" owned: "+farmlvl+"*~");
        } else {
        System.out.println("3: Farm: "+"cost: $"+uniCost(1000, farmlvl)+" owned: "+farmlvl);
        } if(money>=uniCost(10000, minelvl)) {
        System.out.println("~*4: Mine: "+"cost: $"+uniCost(10000, minelvl)+" owned: "+minelvl+"*~");
        } else {
        System.out.println("4: Mine: "+"cost: $"+uniCost(10000, minelvl)+" ownaed: "+minelvl);
        }

        System.out.println("you have:"+money+" cookies");
        System.out.println("");
    }
    }
    //settings
    public static Boolean autosave = true; //toggle whether the game automatically saves after each upgrade purchase (9/10 recommend)
    public static void main(String [] args) {
        
        if(init == true) {
            game obj = new game();
            Thread cps = new Thread(obj); 
            cps.start(); 
            init = false;
        }

        main = true;
        int choice = sc.nextInt(10);
        main = false; 
        if (choice == 0) {
            save();
            System.exit(0);
        } else if (choice == 1) {
            money = Math.round(money+clickCPS());
            main(args);
        } else if (choice == 2) {
            upgrade();
        } else if (choice == 9) {
            wipe();
                }

    }

    //
    public void run() {
        while (calcps == true) {
        money = Math.round(money+cps());
        if (main == true) {
            display("main");
        }
        if (upgrade == true) {
            display("upgrade");
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }
    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    
    public static void upgrade() {
        upgrade = true;
        try {
        int choice = sc.nextInt(5);
        if (choice == 0) {
            main(null);
        } else if (choice == 1) {
            buy(cursorlvl, 15);
            cursorlvl++;
        } else if (choice == 2) {
            buy(grandmalvl, 100);
            grandmalvl++;
        } else if (choice == 3) {
            buy(farmlvl, 1000);
            farmlvl++;
        } else if (choice == 4) {
            buy(minelvl, 10000);
            minelvl++;
        }
    } catch (InputMismatchException e) {
        System.out.println("error: input invalid");
        upgrade();
    }
        upgrade();



    }   
    public static void buy(int buildinglvl, int mod) {
        if (money>=uniCost(mod, buildinglvl)) {
            money = money-uniCost(mod, buildinglvl);
            if (autosave == true) {
                save();
            }
        } else {
            System.out.println("purchase failed, not enough money");
            System.out.println("need: "+uniCost(mod, buildinglvl)+" have: "+money);
            try {
                Thread.sleep(3000);
                upgrade();
            } catch(InterruptedException e) {
                upgrade();
            }
        }
    }

    public static void save() {
        try {
        FileWriter moneyq = new FileWriter("stats/money");
        FileWriter cursorq = new FileWriter("stats/cursorlvl");
        FileWriter grandmaq = new FileWriter("stats/grandmalvl");
        FileWriter farmq = new FileWriter("stats/farmlvl");

        moneyq.write(money + "");
        cursorq.write(cursorlvl + "");
        grandmaq.write(grandmalvl + "");
        farmq.write(farmlvl+ "");
        moneyq.close();
        cursorq.close();
        grandmaq.close();
        farmq.close();

        System.out.println("data saved");

        } catch (IOException e) {  
            e.printStackTrace();
            System.out.println("data save failed");
            main(null);

        }

    }
    public static void wipe() {
        clear();
        System.out.println("are you SURE you would like to reset all game data");
        System.out.println("stats after wipe:");
        System.out.println("playername: whatever it was before");
        System.out.println("money: 0");
        System.out.println("all building levels: 0");
        System.out.println("");
        System.out.println("0 = no");
        System.out.println("1 = yes");
        int confirm = sc.nextInt(2);
        if (confirm == 0) {
            main(null);
        } else if (confirm == 1) {
            money = 0;
            cursorlvl = 0;
            grandmalvl = 0;
            farmlvl = 0;
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
  public static int statgrab(File txt) {
        try (Scanner read = new Scanner(txt)) {
        int stat0 = read.nextInt();
        read.close();
        return stat0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 404; 
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
//MATH
//MATH
//MATH

    //calculate cps
    public static double cps() {
        
        double cps0 = (cursorlvl*1)+(grandmalvl*1.2)+(farmlvl*1.4)+(minelvl*1.6); 
        return cps0;
    }

    public static double clickCPS() {
        //upgrades coming soon
        double cps0 = 1;
        return cps0;
    }
    //calculate building cost
    public static int uniCost(int mod, int lev) {
        int cost0 = mod+lev*10;
        return cost0;
    }


}