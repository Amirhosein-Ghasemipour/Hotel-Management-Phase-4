package view;

import controller.LoginMenuController;
import view.enums.commands.LoginMenuCommands;
import view.enums.messages.LoginMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    public static void run(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.nextLine();
            Matcher matcher;
            if(command.matches("^\\s*exit\\s*$")){
                break;
            }else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.REGISTER_HOTEL_OWNER)) != null){
                checkRegisterHotelOwner(matcher);
            } else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.REGISTER_GUEST)) != null){
                checkRegisterGuest(matcher);
            } else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.LOGIN)) != null){
                checkLogin(matcher, scanner);
            } else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.CHANGE_PASSWORD)) != null){
                checkChangePassword(matcher);
            } else if((matcher = LoginMenuCommands.getMatcher(command, LoginMenuCommands.REMOVE_ACCOUNT)) != null){
                checkRemoveAccount(matcher);
            } else
                System.out.println("invalid command!");
        }
    }

    private static void checkRegisterGuest(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        LoginMenuMessages message = LoginMenuController.checkRegisterGuest(username, password);
        switch(message){
            case REGISTER_SUCCESS:
                System.out.println("guest was registered successfully");
                break;
            case REGISTER_INVALID_USERNAME:
                System.out.println("register failed, username format was invalid");
                break;
            case REGISTER_USERNAME_EXISTS:
                System.out.println("register failed, a user with this username already exists");
                break;
            case REGISTER_WEAK_PASSWORD:
                System.out.println("register failed, password is weak");
                break;
            default:
                break;
        }
    }

    private static void checkRegisterHotelOwner(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        String hotelName = matcher.group("hotelName");
        String hotelLocation = matcher.group("hotelLocation");
        LoginMenuMessages message = LoginMenuController.checkRegisterHotelOwner(username, password, hotelName, hotelLocation);
        switch (message){
            case REGISTER_SUCCESS:
                System.out.println("hotel owner was registered successfully");
                break;
            case REGISTER_INVALID_USERNAME:
                System.out.println("register failed, username format was invalid");
                break;
            case REGISTER_USERNAME_EXISTS:
                System.out.println("register failed, a user with this username already exists");
                break;
            case REGISTER_WEAK_PASSWORD:
                System.out.println("register failed, password is weak");
                break;
            case REGISTER_HOTEL_NAME_EXISTS:
                System.out.println("register failed, a hotel with this name already exists");
                break;
            default:
                break;

        }
    }

    private static void checkLogin(Matcher matcher, Scanner scanner){
        String username = matcher.group("username");
        String password = matcher.group("password");
        LoginMenuMessages message = LoginMenuController.checkLogin(username, password);
        switch(message){
            case LOGIN_USERNAME_NOT_FOUND:
                System.out.println("login failed, no user with this username was found");
                break;
            case LOGIN_WRONG_PASSWORD:
                System.out.println("login failed, password is wrong");
                break;
            case LOGIN_SUCCESS_GUEST:
                System.out.println("logged in to guest menu successfully");
                GuestMenu.run(scanner);
                break;
            case LOGIN_SUCCESS_HOTEL_OWNER:
                System.out.println("logged in to hotel owner menu successfully");
                HotelOwnerMenu.run(scanner);
                break;
            default:
                break;
        }
    }

    private static void checkChangePassword(Matcher matcher){
        String username = matcher.group("username");
        String oldPassword = matcher.group("oldPassword");
        String newPassword = matcher.group("newPassword");
        LoginMenuMessages message = LoginMenuController.checkChangePassword(username, oldPassword, newPassword);
        switch(message){
            case PASSWORD_CHANGE_USERNAME_NOT_FOUND:
                System.out.println("password change failed, no user with this username was found");
                break;
            case PASSWORD_CHANGE_WRONG_PASSWORD:
                System.out.println("password change failed, password is wrong");
                break;
            case PASSWORD_CHANGE_WEAK_PASSWORD:
                System.out.println("password change failed, new password is weak");
                break;
            case PASSWORD_CHANGE_SUCCESSFUL:
                System.out.println("password was changed successfully");
                break;
            default:
                break;
        }

    }

    private static void checkRemoveAccount(Matcher matcher){
        String username = matcher.group("username");
        String password = matcher.group("password");
        LoginMenuMessages message = LoginMenuController.checkRemoveAccount(username, password);
        switch(message){
            case REMOVE_ACCOUNT_USERNAME_NOT_FOUND:
                System.out.println("account remove failed, no user with this username was found");
                break;
            case REMOVE_ACCOUNT_WRONG_PASSWORD:
                System.out.println("account remove failed, password is wrong");
                break;
            case REMOVE_ACCOUNT_SUCCESSFUL:
                System.out.println("account was removed successfully");
                break;
            default:
                break;
        }
    }
}
