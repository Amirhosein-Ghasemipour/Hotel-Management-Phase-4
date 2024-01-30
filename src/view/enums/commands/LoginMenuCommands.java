package view.enums.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    REGISTER_GUEST("^register\\s+guest\\s+u\\s+(?<username>\\S+)\\s+p\\s+(?<password>\\S+)$"),
    REGISTER_HOTEL_OWNER("^register\\s+hotel\\s+owner\\s+u\\s+(?<username>\\S+)\\s+p\\s+(?<password>\\S+)\\s+hn\\s+(?<hotelName>\\S+)\\s+hl\\s+(?<hotelLocation>.+)$"),
    USERNAME_VALID("^[A-Za-z0-9_]+$"),
    LOGIN("^login\\s+u\\s+(?<username>\\S+)\\s+p\\s+(?<password>\\S+)$"),
    PASSWORD_STRONG("(?<password>(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&()\\[{}\\]:;><,?/~_+\\-=|]).{8,32})"),
    CHANGE_PASSWORD("^change\\s+password\\s+u\\s+(?<username>\\S+)\\s+op\\s+(?<oldPassword>\\S+)\\s+np\\s+(?<newPassword>\\S+)$"),
    REMOVE_ACCOUNT("^remove\\s+account\\s+u\\s+(?<username>\\S+)\\s+p\\s+(?<password>\\S+)$");
    public String regex;

    LoginMenuCommands(String regex){
        this.regex = regex;
    }

    public static Matcher getMatcher(String command, LoginMenuCommands mainRegex) {
        Matcher matcher = Pattern.compile(mainRegex.regex).matcher(command);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
