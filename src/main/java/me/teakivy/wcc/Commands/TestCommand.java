package me.teakivy.wcc.Commands;

import me.teakivy.wcc.Managers.StageManager;
import me.teakivy.wcc.Utils.AbstractCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class TestCommand extends AbstractCommand {
    public TestCommand() {
        super("test", "/test", "WCC Testing Command", Collections.emptyList());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (!player.isOp()) {
            sender.sendMessage("§cYou don't have permission to do that!");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage("Test");
            return false;
        }

        if (args[0].equalsIgnoreCase("stage1")) {
            player.sendMessage("Stage 1");
            StageManager.setStage(StageManager.Stage.ONE);
            return false;
        }

        if (args[0].equalsIgnoreCase("stage2")) {
            player.sendMessage("Stage 2");
            StageManager.setStage(StageManager.Stage.TWO);
            return false;
        }

        if (args[0].equalsIgnoreCase("stage3")) {
            player.sendMessage("Stage 3");
            StageManager.setStage(StageManager.Stage.THREE);
            return false;
        }

        if (args[0].equalsIgnoreCase("stage4")) {
            player.sendMessage("Stage 4");
            StageManager.setStage(StageManager.Stage.FOUR);
            return false;
        }

        if (args[0].equalsIgnoreCase("stage5")) {
            player.sendMessage("Stage 5");
            StageManager.setStage(StageManager.Stage.FIVE);
            return false;
        }

        if (args[0].equalsIgnoreCase("stage6")) {
            player.sendMessage("Stage 6");
            StageManager.setStage(StageManager.Stage.SIX);
            return false;
        }

        if (args[0].equalsIgnoreCase("stage7")) {
            player.sendMessage("Stage 7");
            StageManager.setStage(StageManager.Stage.SEVEN);
            return false;
        }

        return false;
    }
}
