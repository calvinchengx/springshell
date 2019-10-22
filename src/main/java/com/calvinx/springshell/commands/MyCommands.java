package com.calvinx.springshell.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MyCommands {

  @ShellMethod(value = "Add two integers together.", group = "Mathematical Commands")
  public int add(int a, int b) {
    return a + b;
  }

}