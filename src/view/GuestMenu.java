package view;

import controller.GuestMenuController;
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
}
