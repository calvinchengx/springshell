package com.calvinx.springshell.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.ConfigurableCommandRegistry;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.standard.StandardMethodTargetRegistrar;
import org.springframework.util.ReflectionUtils;

public class MyCommandsTest {
  private StandardMethodTargetRegistrar registrar = new StandardMethodTargetRegistrar();
  private ConfigurableCommandRegistry registry = new ConfigurableCommandRegistry();

  @BeforeEach
  public void setUp() {
    ApplicationContext context = new AnnotationConfigApplicationContext(MyCommands.class);
    registrar.setApplicationContext(context);
    registrar.register(registry);
  }

  @Test
  public void testAdd() {
    Map<String, MethodTarget> commands = registry.listCommands();

    MethodTarget methodTarget = commands.get("add");
    assertThat(methodTarget, notNullValue());
    assertTrue(methodTarget.getGroup().equals("Mathematical Commands"));
    assertThat(methodTarget.getHelp(), is("Add two integers together."));
    assertThat(methodTarget.getMethod(), is(ReflectionUtils.findMethod(MyCommands.class, "add", int.class, int.class)));
    assertThat(methodTarget.getAvailability().isAvailable(), is(true));
    assertEquals(3, ReflectionUtils.invokeMethod(methodTarget.getMethod(), methodTarget.getBean(), 1, 2));
  }

}