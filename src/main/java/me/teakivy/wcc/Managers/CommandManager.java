package me.teakivy.wcc.Managers;

import me.teakivy.wcc.Commands.SpectatorCommand;
import me.teakivy.wcc.Commands.StageCommand;
import me.teakivy.wcc.Commands.TestCommand;
import me.teakivy.wcc.Commands.ViewDistanceCommand;

public class CommandManager {

    public static void init() {
        new TestCommand().register();
        new StageCommand().register();
        new ViewDistanceCommand().register();
        new SpectatorCommand().register();
    }

}
