package com.nschejtman.client.console;

import com.nschejtman.client.console.Color;

public class Logger {

    public static void info(String message) {
        System.out.print("[" + Color.ANSI_BLUE.get() + "INFO" + Color.ANSI_RESET.get() + "]     ");
        System.out.print(message + "\n");
    }

    public static void error(String message) {
        System.out.print("[" + Color.ANSI_RED.get() + "INFO" + Color.ANSI_RESET.get() + "]     ");
        System.out.print(message + "\n");
    }

    public static void warning(String message) {
        System.out.print("[" + Color.ANSI_YELLOW.get() + "INFO" + Color.ANSI_RESET.get() + "]     ");
        System.out.print(message + "\n");
    }
}
