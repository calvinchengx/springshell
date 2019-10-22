package com.example.demo.commands;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.demo.TestApplicationRunner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Input;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.Shell;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ReflectionUtils;

@ActiveProfiles("test")
@SpringBootTest
@Import(TestApplicationRunner.class)
public class MyCommandsIntegrationTest {

    @Autowired
    private Shell shell;

    @Test
    public void testAdd() {
        Map<String, MethodTarget> commands = shell.listCommands();
        MethodTarget methodTarget = commands.get("add");
        assertThat(methodTarget, notNullValue());
        assertTrue(methodTarget.getGroup().equals("Mathematical Commands"));
        assertThat(methodTarget.getHelp(), is("Add two integers together."));
        assertThat(methodTarget.getMethod(), is(
                ReflectionUtils.findMethod(MyCommands.class, "add", int.class,
                        int.class)));
        assertThat(methodTarget.getAvailability().isAvailable(), is(true));
        assertEquals(3, shell.evaluate(new Input() {
            public String rawText() {
                return "add 1 2";
            }

            public List<String> words() {
                return Arrays.asList("add", "1", "2");
            }
        }));
    }
}