package org.example.command;

import java.util.ArrayList;
import java.util.List;

public class PromotionInvoker {
    private final List<Command> commandQueue = new ArrayList<>();

    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    public void executeAll() {
        for (Command command : commandQueue) {
            command.execute();
        }
        commandQueue.clear();
    }
}
