package view;

import controller.HotelOwnerMenuController;
import model.Food;
import model.Hotel;
import model.HotelManagement;
import model.Room;
import model.user.Guest;
import view.enums.commands.HotelOwnerMenuCommands;
import view.enums.messages.HotelOwnerMenuMessages;

import java.util.Scanner;
import java.util.regex.Matcher;

public class HotelOwnerMenu {
    public static void run(Scanner scanner){
        while(true){
            String command = scanner.nextLine();
            Matcher matcher;
            if(command.matches("^\\s*logout\\s*$")){
                System.out.println("logged out to login menu");
                break;
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_ADD_ROOM)) != null){
                addRoom(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_REMOVE_ROOM)) != null){
                removeRoom(matcher);
            } else if( HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SHOW_ALL_HOTELS) != null){
                showAllHotels();
            } else if(HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SHOW_HOTEL_GUESTS) != null){
                showHotelGuests();
            } else if(HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SHOW_HOTEL_FOODS) != null){
                showHotelFoods();
            } else if(HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SHOW_HOTEL_ROOMS) != null){
                showHotelRooms();
            } else if(HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SHOW_HOTEL_BALANCE) != null){
                showHotelBalance();
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_INCREASE_HOTEL_BALANCE)) != null){
                increaseHotelBalance(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_ADD_FOOD)) != null){
                addFood(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_REMOVE_FOOD)) != null){
                removeFood(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_INCREASE_ONE_ROOM_PRICE)) != null){
                increaseOneRoomPrice(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_INCREASE_ALL_ROOMS_PRICE)) != null){
                increaseAllRoomsPrice(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SET_DISCOUNT_ONE_ROOM)) != null){
                setDiscountOneRoom(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SET_DISCOUNT_ALL_ROOMS)) != null){
                setDiscountAllRooms(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SET_DISCOUNT_ONE_FOOD)) != null){
                setDiscountOneFood(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_SET_DISCOUNT_ALL_FOODS)) != null){
                setDiscountAllFoods(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_INCREASE_ONE_FOOD_PRICE)) != null){
                increaseOneFoodPrice(matcher);
            } else if((matcher = HotelOwnerMenuCommands.getMatcher(command, HotelOwnerMenuCommands.OWNER_INCREASE_ALL_FOODS_PRICE)) != null){
                increaseAllFoodsPrice(matcher);
            } else
                System.out.println("invalid command");
        }
    }

    private static void addRoom(Matcher matcher){
        int number = Integer.parseInt(matcher.group("number"));
        double size = Double.parseDouble(matcher.group("size"));
        double price = Double.parseDouble(matcher.group("price"));
        String type = matcher.group("type");
        HotelOwnerMenuMessages message = HotelOwnerMenuController.addRoom(number, size, price, type);
        switch(message){
            case ADD_ROOM_NUMBER_INVALID:
                System.out.println("add room failed, your room number must be a positive number");
                break;
            case ADD_ROOM_SIZE_INVALID:
                System.out.println("add room failed, the size of your hotel room must be a positive number");
                break;
            case ADD_ROOM_PRICE_INVALID:
                System.out.println("add room failed, the price of your hotel room must a positive number");
                break;
            case ADD_ROOM_NUMBER_EXISTS:
                System.out.println("add room failed, there is already a room with this number in your hotel");
                break;
            case ADD_ROOM_SUCCESS:
                System.out.println("room added to your hotel successfully");
                break;
            default:
                break;

        }

    }

    private static void removeRoom(Matcher matcher){
        int number = Integer.parseInt(matcher.group("number"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.removeRoom(number);
        switch(message){
            case REMOVE_ROOM_NUMBER_INVALID:
                System.out.println("remove room failed, your room number must be a positive number");
                break;
            case REMOVE_ROOM_NUMBER_NOT_FOUND:
                System.out.println("remove room failed, there is no room with this room number");
                break;
            case REMOVE_ROOM_IS_BOOKED:
                System.out.println("remove room failed, this room is booked you can't remove it");
                break;
            case REMOVE_ROOM_SUCCESS:
                System.out.println("room was removed successfully");
                break;
            default:
                break;
        }
    }

    private static void showAllHotels(){
        int number = 1;
        for(Hotel hotel: HotelManagement.getAllHotels()){
            System.out.println(number + "-" + " name: " + hotel.getName() + " | location: " + hotel.getLocation() +
                    " | number of available rooms: " + hotel.getAvailableRooms().size());
            number++;
        }
    }

    private static void showHotelGuests(){
        int number = 1;
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        for(Guest guest: hotel.getGuests()){
            System.out.println(number + "-" + " username: " + guest.getUsername() + " | room number: "
                    + guest.getReservedRoom().getNumber());
            number++;
        }
    }

    private static void showHotelFoods(){
        int number = 1;
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        for(Food food : hotel.getAllFoods()){
            System.out.println(number + "-" + " name: " + food.getName() + " | price: " + food.getPrice()
                    + " | description: " + food.getDescription());
            number++;
        }
    }

    private static void showHotelRooms(){
        int number = 1;
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        String status;
        for(Room room: hotel.getRooms()){
            if(room.isAvailable()){
                status = "available";
            } else{
                status = "booked";
            }
            System.out.println(number + "-" + "  room number: " + room.getNumber() + " | size: " + room.getSize() +
                    " | price: " + room.getSize() + " | type: " + room.getType() + " | availability status: " + status);
            number++;
        }
    }

    private static void showHotelBalance(){
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        System.out.println("your hotel balance is: " + hotel.getBalance());
    }

    private static void increaseHotelBalance(Matcher matcher){
        double amount = Double.parseDouble(matcher.group("amount"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.increaseHotelBalance(amount);
        switch (message){
            case INCREASE_HOTEL_BALANCE_INVALID:
                System.out.println("increase balance failed, you must increase by a positive amount");
                break;
            case INCREASE_HOTEL_BALANCE_SUCCESS:
                System.out.println("the amount of " + amount + " was added to your hotel balance successfully");
                break;
            default:
                break;
        }
    }

    private static void addFood(Matcher matcher){
        String name = matcher.group("name");
        double cost = Double.parseDouble(matcher.group("cost"));
        double price = Double.parseDouble(matcher.group("price"));
        String description = matcher.group("description");
        HotelOwnerMenuMessages message = HotelOwnerMenuController.addFood(name, cost, price, description);
        switch(message){
            case ADD_FOOD_COST_INVALID:
                System.out.println("add food failed, the cost of the food must be positive");
                break;
            case ADD_FOOD_PRICE_INVALID:
                System.out.println("add food failed, the price of the food must be positive");
                break;
            case ADD_FOOD_EXISTS:
                System.out.println("add food failed, this food is already in your menu");
                break;
            case ADD_FOOD_INSUFFICIENT_BALANCE:
                System.out.println("add food failed, you can't afford to add this food. increase your balance before trying again");
                break;
            case ADD_FOOD_PROFIT_NEGATIVE:
                System.out.println("add food failed, your food price must be more than its cost");
                break;
            case ADD_FOOD_SUCCESS:
                System.out.println("food was added to your hotel menu successfully");
                break;
            default:
                break;
        }

    }

    private static void removeFood(Matcher matcher){
        String name = matcher.group("name");
        HotelOwnerMenuMessages message = HotelOwnerMenuController.removeFood(name);
        switch (message){
            case REMOVE_FOOD_NOT_FOUND :
                System.out.println("remove food failed, there is no food with this name on your hotel's menu");
                break;
            case REMOVE_FOOD_SUCCESS:
                System.out.println("food was removed from your hotel's menu successfully");
                break;
            default:
                break;
        }
    }

    private static void increaseOneRoomPrice(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("percent"));
        int roomNumber = Integer.parseInt(matcher.group("roomNumber"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.increaseOneRoomPrice(percent, roomNumber);
        switch(message){
            case INCREASE_ONE_ROOM_PRICE_INVALID:
                System.out.println("increase one room price failed, you must increase the price by a positive percent");
                break;
            case INCREASE_ONE_ROOM_PRICE_NOT_FOUND:
                System.out.println("increase one room price failed, there is no room with this number in your hotel");
                break;
            case INCREASE_ONE_ROOM_PRICE_SUCCESS:
                System.out.println("you successfully increased the price of the room");
                break;
            default:
                break;
        }

    }

    private static void increaseAllRoomsPrice(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("percent"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.increaseAllRoomsPrice(percent);
        switch (message){
            case INCREASE_ALL_ROOMS_PRICE_EMPTY:
                System.out.println("increase all rooms price failed, there is no room in your hotel yet");
                break;
            case INCREASE_ALL_ROOMS_PRICE_INVALID:
                System.out.println("increase all rooms price failed, you must increase the price by a positive percent");
                break;
            case INCREASE_ALL_ROOMS_PRICE_SUCCESS:
                System.out.println("you successfully increased the price of every room in your hotel");
                break;
            default:
                break;
        }

    }

    private static void setDiscountOneRoom(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("discountPercent"));
        int roomNumber = Integer.parseInt(matcher.group("roomNumber"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.setDiscountOneRoom(percent, roomNumber);
        switch (message){
            case SET_DISCOUNT_ONE_ROOM_INVALID:
                System.out.println("set discount one room failed, you must set a discount by positive percent");
                break;
            case SET_DISCOUNT_ONE_ROOM_PRICE_NEGATIVE:
                System.out.println("set discount one room failed, the discount percentage must be less than 100");
                break;
            case SET_DISCOUNT_ONE_ROOM_NOT_FOUND:
                System.out.println("set discount one room failed, there is no room with this room number in your hotel");
                break;
            case SET_DISCOUNT_ONE_ROOM_SUCCESS:
                System.out.println("you successfully set a discount on this room");
                break;
            default:
                break;
        }

    }

    private static void setDiscountAllRooms(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("discountPercent"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.setDiscountAllRooms(percent);
        switch (message){
            case SET_DISCOUNT_ALL_ROOMS_EMPTY:
                System.out.println("set discount all rooms failed, there is no room in your hotel yet");
                break;
            case SET_DISCOUNT_ALL_ROOMS_INVALID:
                System.out.println("set discount all rooms failed, you must set a discount by positive percentage");
                break;
            case SET_DISCOUNT_ALL_ROOMS_PRICE_NEGATIVE:
                System.out.println("set discount all rooms failed, the discount percentage must be less than 100");
                break;
            case SET_DISCOUNT_ALL_ROOMS_SUCCESS:
                System.out.println("you successfully set a discount on all of your hotel's rooms");
                break;
            default:
                break;
        }

    }

    private static void setDiscountOneFood(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("discountPercent"));
        String name = matcher.group("foodName");
        HotelOwnerMenuMessages message = HotelOwnerMenuController.setDiscountOneFood(percent, name);
        switch (message){
            case SET_DISCOUNT_ONE_FOOD_INVALID:
                System.out.println("set discount one food failed, you must set a discount by a positive percentage");
                break;
            case SET_DISCOUNT_ONE_FOOD_PRICE_NEGATIVE:
                System.out.println("set discount one food failed, the discount percentage must be less than 100");
                break;
            case SET_DISCOUNT_ONE_FOOD_NOT_FOUND:
                System.out.println("set discount one food failed, there is no food with this name on your hotel menu");
                break;
            case SET_DISCOUNT_ONE_FOOD_SUCCESS:
                System.out.println("you successfully set a discount on this food");
                break;
            default:
                break;
        }

    }

    private static void setDiscountAllFoods(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("discountPercent"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.setDiscountAllFoods(percent);
        switch (message){
            case SET_DISCOUNT_ALL_FOODS_EMPTY:
                System.out.println("set discount all foods failed, there is no food on your hotel menu yet");
                break;
            case SET_DISCOUNT_ALL_FOODS_INVALID:
                System.out.println("set discount all foods failed, you must set a discount by a positive percentage");
                break;
            case SET_DISCOUNT_ALL_FOODS_PRICE_NEGATIVE:
                System.out.println("set discount all foods failed, the discount percentage must be less than 100");
                break;
            case SET_DISCOUNT_ALL_FOODS_SUCCESS:
                System.out.println("you successfully set a discount on every food on your hotel menu");
                break;
            default:
                break;
        }

    }

    private static void increaseOneFoodPrice(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("percent"));
        String name = matcher.group("foodName");
        HotelOwnerMenuMessages message = HotelOwnerMenuController.increaseOneFoodPrice(percent, name);
        switch(message){
            case INCREASE_ONE_FOOD_PRICE_INVALID:
                System.out.println("increase one food price failed, you must increase the price by a positive percent");
                break;
            case INCREASE_ONE_FOOD_PRICE_NOT_FOUND:
                System.out.println("increase one food price failed, there is no food with this name in your hotel menu");
                break;
            case INCREASE_ONE_FOOD_PRICE_SUCCESS:
                System.out.println("you successfully increased the price of the food");
                break;
            default:
                break;
        }

    }

    private static void increaseAllFoodsPrice(Matcher matcher){
        double percent = Double.parseDouble(matcher.group("percent"));
        HotelOwnerMenuMessages message = HotelOwnerMenuController.increaseAllFoodsPrice(percent);
        switch (message){
            case INCREASE_ALL_FOODS_PRICE_EMPTY:
                System.out.println("increase all foods price failed, there is no food on your hotel menu yet");
                break;
            case INCREASE_ALL_FOODS_PRICE_INVALID:
                System.out.println("increase all foods price failed, you must increase the price by a positive percent");
                break;
            case INCREASE_ALL_FOODS_PRICE_SUCCESS:
                System.out.println("you successfully increased the price of every food in your hotel menu");
                break;
            default:
                break;
        }

    }
}
