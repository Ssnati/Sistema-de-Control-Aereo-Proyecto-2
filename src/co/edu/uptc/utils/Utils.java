package co.edu.uptc.utils;

public class Utils {
    public static void sleepThread(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getRedMessage(){
        return "\033[31m";
    }
    public static String getGreenMessage(){
        return "\033[32m";
    }
    public static String getYellowMessage(){
        return "\033[33m";
    }
    public static String getBlueMessage(){
        return "\033[34m";
    }
    public static String getPurpleMessage(){
        return "\033[35m";
    }
    public static String getCyanMessage(){
        return "\033[36m";
    }
    public static String getWhiteMessage(){
        return "\033[37m";
    }
    public static String getResetMessage(){
        return "\033[0m";
    }
}
