package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum HotelOwnerMenuCommands {
    OWNER_ADD_ROOM("^add\\s+room\\s+n\\s+(?<number>\\d+)\\s+s\\s+(?<size>\\d+)\\s+p\\s+(?<price>\\d+)\\s+t\\s+(?<type>\\S+)$"),
    OWNER_REMOVE_ROOM("^remove\\s+room\\s+(?<number>\\d+)$"),
    OWNER_SHOW_ALL_HOTELS("^show\\s+all\\s+hotels\\s+$"),
    OWNER_SHOW_HOTEL_GUESTS("^show\\s+my\\s+hotel\\s+guests$"),
    OWNER_SHOW_HOTEL_FOODS("^show\\s+my\\s+hotel\\s+foods$"),
    OWNER_SHOW_HOTEL_ROOMS("^show\\s+my\\s+hotel\\s+rooms$"),
    OWNER_SHOW_HOTEL_BALANCE("^show\\s+my\\s+hotel\\s+balance$"),
    OWNER_INCREASE_HOTEL_BALANCE("^increase\\s+my\\s+hotel\\s+balance\\s+by\\s+(?<amount>\\d+)$"),
    OWNER_ADD_FOOD("^add\\s+food\\s+n\\s+(?<name>\\w+)\\s+c\\s+(?<cost>\\d+)\\s+p\\s+(?<price>\\d+)\\s+d\\s+(?<description>.+)$"),
    OWNER_REMOVE_FOOD("^remove\\s+food\\s+(?<name>\\w+)$"),
    OWNER_INCREASE_ONE_ROOM_PRICE("^increase\\s+room\\s+(?<roomNumber>\\d+)\\s+price\\s+by\\s+(?<percent>\\d+)%$"),
    OWNER_INCREASE_ALL_ROOMS_PRICE("^increase\\s+all\\s+rooms\\s+price\\s+by\\s+(?<percent>\\d+)%$"),
    OWNER_SET_DISCOUNT_ONE_ROOM("^set\\s+discount\\s+room\\s+(?<roomNumber>\\d+)\\s+(?<discountPercent>\\d+)%$"),
    OWNER_SET_DISCOUNT_ALL_ROOMS("^set\\s+discount\\s+all\\s+rooms\\s+(?<discountPercent>\\d+)%$"),
    OWNER_SET_DISCOUNT_ONE_FOOD("^set\\s+discount\\s+(?<foodName>\\w+)\\s+(?<discountPercent>\\d+)%$"),
    OWNER_SET_DISCOUNT_ALL_FOODS("^set\\s+discount\\s+all\\s+foods\\s+(?<discountPercent>\\d+)%$"),
    OWNER_INCREASE_ONE_FOOD_PRICE("^increase\\s+(?<foodName>\\w+)\\s+price\\s+by\\s+(?<percent>\\d+)%$"),
    OWNER_INCREASE_ALL_FOODS_PRICE("^increase\\s+all\\s+foods\\s+price\\s+by\\s+(?<percent>\\d+)%$");


    public String regex;
    HotelOwnerMenuCommands(String regex){
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, HotelOwnerMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
