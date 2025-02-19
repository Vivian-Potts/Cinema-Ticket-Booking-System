package com.example.CinemaTicketServer;

public class StringCapitaliser {
    public static String capitaliseString(String string){

        string = string.toLowerCase();
        char[] test = string.toCharArray();


        for (int i = 1; i< test.length; i++){
            if (test[i] == ' '){
                test[i-1] = Character.toLowerCase(test[i-1]);
            }
        }

        string = String.copyValueOf(test);
        return string;
    }
}
