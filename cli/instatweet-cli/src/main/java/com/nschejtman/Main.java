package com.nschejtman;

import com.nschejtman.client.ApplicationContext;
import com.nschejtman.client.console.Color;
import com.nschejtman.jms.JMSHandler;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

public class Main {
    @Resource(mappedName = "jms/__defaultConnectionFactory")
    private static ConnectionFactory connectionFactory;

    public static void main(String[] args) {
        JMSHandler.setContext(connectionFactory.createContext());
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
        System.out.println("Please enter a command. Type \"help\" to see list of available commands");
        System.out.println();
        final ApplicationContext context = new ApplicationContext();
        context.run();
    }


}
