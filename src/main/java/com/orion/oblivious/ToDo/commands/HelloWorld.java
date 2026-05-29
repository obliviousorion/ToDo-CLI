package com.orion.oblivious.ToDo.commands;

import org.springframework.shell.core.command.annotation.Command;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    @Command(name = "niggaa", description = "Prints a greeting")
    public String sayHello() {
        return "Hello Niggaa!";
    }
}