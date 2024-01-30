package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GuestMenuCommands {
    GUEST_RESERVE_ROOM("^reserve\\s+from\\s+hotel\\s+(?<hotelName>\\S+)\\s+room\\s+(?<roomNumber>\\d+)$"),
    GUEST_LEAVE_ROOM("^leave\\s+room$"),
    GUEST_BUY_FOOD("^buy\\s+food\\s+(?<foodName>\\w+)\\s+(?<number>\\d+)$"),
    GUEST_SHOW_ALL_HOTELS("^show\\s+all\\s+hotels$"),
    GUEST_SHOW_HOTEL_ROOMS("^show\\s+hotel\\s+(?<hotelName>\\S+)\\s+rooms$"),
    GUEST_SHOW_HOTEL_FOODS("^show\\s+hotel\\s+(?<hotelName>\\S+)\\s+foods$"),
    GUEST_SHOW_BALANCE("^show\\s+my\\s+balance$"),
    GUEST_INCREASE_BALANCE("^increase\\s+my\\s+balance\\s+by\\s+(?<amount>\\d+)$"),
    GUEST_SHOW_MY_ROOM("^show\\s+my\\s+room$"),
    GUEST_SHOW_MY_FOODS("^show\\s+my\\s+foods$"),
    GUEST_RATE_MY_ROOM("^rate\\s+my\\s+room\\s+(?<rating>\\d+)$"),
    GUEST_SHOW_HOTEL_COMMENTS("^show\\s+hotel\\s+(?<hotelName>\\S+)\\s+comments$"),
    LEAVE_COMMENT_FOR_HOTEL("^leave\\s+comment\\s+(?<content>.+)$");


    public String regex;

    GuestMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, GuestMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }


}
