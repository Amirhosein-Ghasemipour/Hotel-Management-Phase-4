package view;

import controller.GuestMenuController;
import model.*;
import model.user.Guest;
import view.enums.commands.GuestMenuCommands;
import view.enums.messages.GuestMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GuestMenu {
    public static void run(Scanner scanner) {
        while (true) {
            String command = scanner.nextLine();
            Matcher matcher;
            if (command.matches("^\\s*logout\\s*$")) {
                System.out.println("logged out to login menu");
                break;
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_RESERVE_ROOM)) != null) {
                reserveRoom(matcher);
            } else if (GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_LEAVE_ROOM) != null) {
                leaveRoom();
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_BUY_FOOD)) != null) {
                buyFood(matcher);
            } else if (GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_ALL_HOTELS) != null) {
                showAllHotels();
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_HOTEL_ROOMS)) != null) {
                showHotelRooms(matcher);
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_HOTEL_FOODS)) != null) {
                showHotelFoods(matcher);
            } else if (GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_BALANCE) != null) {
                showBalance();
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_INCREASE_BALANCE)) != null) {
                increaseBalance(matcher);
            } else if (GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_MY_ROOM) != null) {
                showMyRoom();
            } else if (GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_MY_FOODS) != null) {
                showMyFoods();
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_RATE_MY_ROOM)) != null) {
                rateMyRoom(matcher);
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.LEAVE_COMMENT_FOR_HOTEL)) != null) {
                leaveComment(matcher);
            } else if ((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_HOTEL_COMMENTS)) != null) {
                showHotelComments(matcher);
            } else
                System.out.println("invalid command");
        }
    }

    private static void reserveRoom(Matcher matcher) {
        String hotelName = matcher.group("hotelName");
        int roomNumber = Integer.parseInt(matcher.group("roomNumber"));
        GuestMenuMessages message = GuestMenuController.reserveRoom(hotelName, roomNumber);
        switch (message) {
            case RESERVE_ROOM_HOTEL_NOT_FOUND:
                System.out.println("reserve room failed, there is no hotel with this name");
                break;
            case RESERVE_ROOM_NOT_FOUND:
                System.out.println("reserve room failed, there is no room with this number in this hotel");
                break;
            case RESERVE_ROOM_NOT_AVAILABLE:
                System.out.println("reserve room failed, this room is already booked");
                break;
            case RESERVE_ROOM_SECOND_ROOM:
                System.out.println("reserve room failed, you have already booked a room");
                break;
            case RESERVE_ROOM_INSUFFICIENT_BALANCE:
                System.out.println("reserve room failed, your balance is insufficient for this room");
                break;
            case RESERVE_ROOM_SUCCESS:
                System.out.println("you successfully reserved this room");
                break;
            default:
                break;
        }

    }

    private static void leaveRoom() {
        GuestMenuMessages message = GuestMenuController.leaveRoom();
        switch (message) {
            case LEAVE_ROOM_NOT_FOUND:
                System.out.println("leave room failed, you haven't reserved a room yet");
                break;
            case LEAVE_ROOM_SUCCESS:
                System.out.println("you successfully left your room");
                break;
            default:
                break;
        }

    }

    private static void buyFood(Matcher matcher) {
        String foodName = matcher.group("foodName");
        int number = Integer.parseInt(matcher.group("number"));
        GuestMenuMessages message = GuestMenuController.buyFood(foodName, number);
        switch (message) {
            case BUY_FOOD_NEGATIVE:
                System.out.println("buy food failed, you can't buy a negative number of foods");
                break;
            case BUY_FOOD_ROOM_NOT_RESERVED:
                System.out.println("buy food failed, you have to reserve a room in a hotel before buying a food");
                break;
            case BUY_FOOD_NOT_FOUND:
                System.out.println("buy food failed, your hotel doesn't have this food");
                break;
            case BUY_FOOD_INSUFFICIENT_BALANCE:
                System.out.println("buy food failed, your balance is not enough to buy this number of this food");
                break;
            case BUY_FOOD_SUCCESS:
                System.out.println("you successfully bought this food");
                break;
            default:
                break;
        }
    }

    private static void showAllHotels() {
        int number = 1;
        if (HotelManagement.getAllHotels().isEmpty()) {
            System.out.println("there are no hotels yet");
        } else {
            for (Hotel hotel : HotelManagement.getAllHotels()) {
                System.out.println(number + "-" + hotel.toString());
            }
        }
    }

    private static void showHotelRooms(Matcher matcher) {
        int number = 1;
        String hotelName = matcher.group("hotelName");
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        if (hotel == null) {
            System.out.println("show hotel rooms failed, hotel not found");
        } else if (hotel.getRooms().isEmpty()) {
            System.out.println("there is no room yet");
        } else {
            for (Room room : hotel.getRooms()) {
                System.out.println(number + "-" + room.toString());
            }
        }
    }

    private static void showHotelFoods(Matcher matcher) {
        int number = 1;
        String hotelName = matcher.group("hotelName");
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        if (hotel == null) {
            System.out.println("show hotel foods failed, hotel not found");
        } else if (hotel.getAllFoods().isEmpty()) {
            System.out.println("there is no food on this hotel's menu yet");
        } else {
            for (Food food : hotel.getAllFoods()) {
                System.out.println(number + "-" + food.toString());
            }
        }
    }

    private static void showBalance() {
        Guest guest = HotelManagement.getCurrentGuest();
        System.out.println("your balance is: " + guest.getBalance());
    }

    private static void increaseBalance(Matcher matcher) {
        double amount = Double.parseDouble(matcher.group("amount"));
        GuestMenuMessages message = GuestMenuController.increaseBalance(amount);
        switch (message) {
            case INCREASE_BALANCE_NEGATIVE:
                System.out.println("increase balance failed, you must increase your balance by a positive amount");
                break;
            case INCREASE_BALANCE_SUCCESS:
                System.out.println("you successfully increased your balance");
                break;
            default:
                break;
        }
    }

    private static void showMyRoom() {
        Guest guest = HotelManagement.getCurrentGuest();
        Room room = guest.getReservedRoom();
        if (room == null) {
            System.out.println("you don't have a room yet");
        } else {
            System.out.println(room.toString());
        }
    }

    private static void showMyFoods() {
        int number = 1;
        Guest guest = HotelManagement.getCurrentGuest();
        if (guest.getBoughtFoods().isEmpty()) {
            System.out.println("you haven't bought any food yet");
        } else {
            for (Food food : guest.getBoughtFoods()) {
                System.out.println(number + "-" + food.toString());
            }
        }
    }

    private static void rateMyRoom(Matcher matcher) {
        double rating = Double.parseDouble(matcher.group("rating"));
        GuestMenuMessages message = GuestMenuController.rateMyRoom(rating);
        switch (message) {
            case RATE_MY_ROOM_NOT_EXISTS:
                System.out.println("rate my room failed, you don't have a room to rate");
                break;
            case RATE_MY_ROOM_INVALID:
                System.out.println("rate my room failed, your rating must be between 0 and 5");
                break;
            case RATE_MY_ROOM_SUCCESS:
                System.out.println("you successfully rated your room");
                break;
            default:
                break;
        }
    }

    private static void leaveComment(Matcher matcher) {
        String content = matcher.group("content");
        GuestMenuMessages message = GuestMenuController.leaveComment(content);
        switch (message) {
            case LEAVE_COMMENT_ROOM_NOT_EXISTS:
                System.out.println("leave comment failed, you don't have a room in any hotel");
                break;
            case LEAVE_COMMENT_SUCCESS:
                System.out.println("you successfully left a comment for this hotel");
                break;
            default:
                break;
        }
    }

    private static void showHotelComments(Matcher matcher) {
        String hotelName = matcher.group("hotelName");
        GuestMenuMessages message = GuestMenuController.showHotelComments(hotelName);
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        switch (message) {
            case SHOW_COMMENTS_HOTEL_NOT_FOUND:
                System.out.println("show hotel comments failed, there is no hotel with that name");
                break;
            case SHOW_COMMENTS_NO_COMMENT:
                System.out.println("show hotel comments failed, there is no comment yet");
                break;
            case SHOW_COMMENTS_SUCCESS:
                int number = 1;
                for (Comment comment : hotel.getComments()) {
                    System.out.println(number + "- " + comment.toString());
                }
        }
    }
}
