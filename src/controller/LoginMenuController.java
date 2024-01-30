package controller;

import model.HotelManagement;
import model.user.Guest;
import model.user.HotelOwner;
import model.user.User;
import view.enums.commands.LoginMenuCommands;
import view.enums.messages.LoginMenuMessages;

public class LoginMenuController {
    public static LoginMenuMessages checkRegisterGuest(String username, String password) {
        if (!username.matches(LoginMenuCommands.USERNAME_VALID.regex))
            return LoginMenuMessages.REGISTER_INVALID_USERNAME;

        if (HotelManagement.getUserByUsername(username) != null)
            return LoginMenuMessages.REGISTER_USERNAME_EXISTS;

        if (!password.matches(LoginMenuCommands.PASSWORD_STRONG.regex))
            return LoginMenuMessages.REGISTER_WEAK_PASSWORD;

        Guest guest = new Guest(username, password);
        HotelManagement.addUser(guest);
        HotelManagement.addGuest(guest);
        return LoginMenuMessages.REGISTER_SUCCESS;
    }

    public static LoginMenuMessages checkRegisterHotelOwner(String username, String password, String hotelName, String hotelLocation) {
        if (!username.matches(LoginMenuCommands.USERNAME_VALID.regex))
            return LoginMenuMessages.REGISTER_INVALID_USERNAME;

        if (HotelManagement.getUserByUsername(username) != null)
            return LoginMenuMessages.REGISTER_USERNAME_EXISTS;

        if (!password.matches(LoginMenuCommands.PASSWORD_STRONG.regex))
            return LoginMenuMessages.REGISTER_WEAK_PASSWORD;

        if (HotelManagement.getHotelByName(hotelName) != null)
            return LoginMenuMessages.REGISTER_HOTEL_NAME_EXISTS;

        HotelOwner hotelOwner = new HotelOwner(username, password, hotelName, hotelLocation);
        HotelManagement.addUser(hotelOwner);
        HotelManagement.addHotelOwner(hotelOwner);
        return LoginMenuMessages.REGISTER_SUCCESS;
    }

    public static LoginMenuMessages checkLogin(String username, String password) {
        User user = HotelManagement.getUserByUsername(username);
        if (user == null)
            return LoginMenuMessages.LOGIN_USERNAME_NOT_FOUND;

        if (!user.isPasswordCorrect(password))
            return LoginMenuMessages.LOGIN_WRONG_PASSWORD;

        if (user instanceof Guest) {
            HotelManagement.setCurrentUser(user);
            HotelManagement.setCurrentGuest((Guest) user);
            return LoginMenuMessages.LOGIN_SUCCESS_GUEST;
        }

        HotelManagement.setCurrentUser(user);
        HotelManagement.setCurrentHotelOwner((HotelOwner) user);
        return LoginMenuMessages.LOGIN_SUCCESS_HOTEL_OWNER;

    }

    public static LoginMenuMessages checkChangePassword(String username, String oldPassword, String newPassword) {
        User user = HotelManagement.getUserByUsername(username);
        if (user == null)
            return LoginMenuMessages.PASSWORD_CHANGE_USERNAME_NOT_FOUND;

        if (!user.isPasswordCorrect(oldPassword))
            return LoginMenuMessages.PASSWORD_CHANGE_WRONG_PASSWORD;

        if (!newPassword.matches(LoginMenuCommands.PASSWORD_STRONG.regex))
            return LoginMenuMessages.PASSWORD_CHANGE_WEAK_PASSWORD;

        user.setPassword(newPassword);
        return LoginMenuMessages.PASSWORD_CHANGE_SUCCESSFUL;

    }

    public static LoginMenuMessages checkRemoveAccount(String username, String password) {
        User user = HotelManagement.getUserByUsername(username);
        if (user == null)
            return LoginMenuMessages.REMOVE_ACCOUNT_USERNAME_NOT_FOUND;

        if (!user.isPasswordCorrect(password))
            return LoginMenuMessages.REMOVE_ACCOUNT_WRONG_PASSWORD;

        HotelManagement.removeUser(user);
        if (user instanceof Guest) {
            HotelManagement.removeGuest((Guest) user);
        } else {
            HotelManagement.removeHotelOwner((HotelOwner) user);
        }
        return LoginMenuMessages.REMOVE_ACCOUNT_SUCCESSFUL;
    }
}
