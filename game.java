import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class game implements Runnable {
    
    public static Scanner sc = new Scanner(System.in);
    public static Boolean calcps = true;
    public static int CPS = math.cps();
    
    
    //stats
    public static String playername = grab.playername();
    public static int money = grab.money();
    public static int cursorlvl = grab.cursorlvl();
    
    //settings
    public static Boolean autosave = false; //togglle whether the game automatically saves after each upgrade purchase
    public static void main(String [] args) {
        
        game obj = new game();
        Thread cps = new Thread(obj); 
        cps.start();      

        System.out.print("player: ");
        System.out.println(playername);
        System.out.print("money: ");
        System.out.println(money);
        System.out.print("cursor: ");
        System.out.println(cursorlvl);
        System.out.print("CPS: ");
        System.out.println(CPS);



        System.out.println("Welcome to Cookie Clicker - cli edition");
        System.out.println("by watercereal");

        System.out.println("0: save and exit");
        System.out.println("1: view display");
        System.out.println("2: upgrade buildings");
        int choice = sc.nextInt();
        if (choice == 0) {
            save();
            System.exit(0);
        } else if (choice == 1) {
            displaying = true;
        } else if (choice == 2) {
            upgrade();
        }
    }

    //
    public void run() {
        while (calcps = true) {
        try {
            money = money+CPS;
            Thread.sleep(1000);
            System.out.println(money);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        if (displaying == true) {
        System.out.println("     ---------        Cookies: "+money);
        System.out.println("    |  .   () |       CPS: "+CPS);
        System.out.println("   |       .   |      ------------------------");
        System.out.println("   | ,  .      |      Cursors: "+cursorlvl);
        System.out.println("   |  .     [] |");
        System.out.println("    |,  ()   ,|");
        System.out.println("     |____,__|");
        }
    }
      }
    public static Boolean displaying = false;
    public static void upgrade() {
        System.out.println("0: exit to menu");
        System.out.println("1: cursor:");
        System.out.println("cost: "+math.cursor()+" owned: "+cursorlvl);

        System.out.println("");
        int choice = sc.nextInt();
        if (choice == 0) {
            main(null);
        } else if (choice == 1) {
            if (money>math.cursor()) {
                cursorlvl++;
                money = money-math.cursor();
                System.out.println("bought cursor");
                if (autosave = true) {
                    save();
                }
                upgrade();
            }
        }

    }   
    public static void save() {
        try {
        FileWriter moneyq = new FileWriter("stats/money");
        FileWriter cursorq = new FileWriter("stats/cursorlvl");
        moneyq.write(money + "");
        cursorq.write(cursorlvl + "");
        moneyq.close();
        cursorq.close();
        System.out.println("data saved");

        } catch (IOException e) {  
            e.printStackTrace();
        }

    }
}