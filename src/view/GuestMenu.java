package view;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GuestMenu {
    public static void run(Scanner scanner){
        while(true){
            String command = scanner.nextLine();
            Matcher matcher;
            if(command.matches("^\\s*logout\\s*$")){
                System.out.println("logged out to login menu");
                break;
            }
        }
    }
}
