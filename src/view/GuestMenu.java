package view;

import controller.GuestMenuController;
import model.Food;
import model.Hotel;
import model.HotelManagement;
import model.Room;
import model.user.Guest;
import view.enums.commands.GuestMenuCommands;
import view.enums.messages.GuestMenuMessages;

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
            } else if((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_RESERVE_ROOM)) != null){
                reserveRoom(matcher);
            } else if(GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_LEAVE_ROOM) != null){
                leaveRoom();
            } else if((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_BUY_FOOD)) != null){
                buyFood(matcher);
            } else if(GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_ALL_HOTELS) != null){
                showAllHotels();
            } else if((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_HOTEL_ROOMS)) != null){
                showHotelRooms(matcher);
            } else if((matcher = GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_HOTEL_FOODS)) != null){
                showHotelFoods(matcher);
            } else if(GuestMenuCommands.getMatcher(command, GuestMenuCommands.GUEST_SHOW_BALANCE) != null){
                showBalance();
            }
        }
    }

    private static void reserveRoom(Matcher matcher){
        String hotelName = matcher.group("hotelName");
        int roomNumber = Integer.parseInt(matcher.group("roomNumber"));
        GuestMenuMessages message = GuestMenuController.reserveRoom(hotelName, roomNumber);
        switch(message){
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

    public static void leaveRoom(){
        GuestMenuMessages message = GuestMenuController.leaveRoom();
        switch (message){
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

    public static void buyFood(Matcher matcher){
        String foodName = matcher.group("foodName");
        int number = Integer.parseInt(matcher.group("number"));
        GuestMenuMessages message = GuestMenuController.buyFood(foodName, number);
        switch (message){
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

    public static void showAllHotels(){
        int number = 1;
        for(Hotel hotel: HotelManagement.getAllHotels()){
            System.out.println(number + "-" + hotel.toString());
        }
    }

    public static void showHotelRooms(Matcher matcher){
        int number = 1;
        String hotelName = matcher.group("hotelName");
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        if(hotel == null){
            System.out.println("show hotel rooms failed, hotel not found");
        } else if(hotel.getRooms().isEmpty()){
            System.out.println("there is no room yet");
        } else{
            for(Room room: hotel.getRooms()){
                System.out.println(number + "-" + room.toString());
            }
        }
    }

    public static void showHotelFoods(Matcher matcher){
        int number = 1;
        String hotelName = matcher.group("hotelName");
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        if(hotel == null){
            System.out.println("show hotel foods failed, hotel not found");
        } else if(hotel.getAllFoods().isEmpty()){
            System.out.println("there is no food on this hotel's menu yet");
        } else{
            for(Food food : hotel.getAllFoods()){
                System.out.println(number + "-" + food.toString());
            }
        }
    }

    public static void showBalance(){
        Guest guest = HotelManagement.getCurrentGuest();
        System.out.println("your balance is: " + guest.getBalance());
    }
}
