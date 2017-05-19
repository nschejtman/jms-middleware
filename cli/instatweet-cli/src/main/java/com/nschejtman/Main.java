package com.nschejtman;

import com.nschejtman.client.ApplicationContext;
import com.nschejtman.client.Color;

public class Main {

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Color.ANSI_BLUE.set();
        System.out.println();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();
        System.out.println();
        System.out.println("                         (っ◔◡◔)っ ♥ INSTATWEET ♥");
        System.out.println();
        System.out.println();
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();
        Color.ANSI_RESET.set();
        System.out.println("Please enter a command");
        System.out.println();
        final ApplicationContext context = new ApplicationContext();
        context.run();
    }


}
