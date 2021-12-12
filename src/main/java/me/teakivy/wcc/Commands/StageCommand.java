package me.teakivy.wcc.Commands;

import me.teakivy.wcc.Managers.StageManager;
import me.teakivy.wcc.Utils.AbstractCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class StageCommand extends AbstractCommand {
    public StageCommand() {
        super("stage", "/stage", "Control the game stages");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§cYou don't have permission to do that!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /stage <set|get|advance|demote> [1/2/3/4/5/6/7]");
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length != 2) {
                sender.sendMessage("§cUsage: /stage set <1/2/3/4/5/6/7>");
            }
            int stage = Integer.parseInt(args[1]);
            if (stage < 1 || stage > 7) {
                sender.sendMessage("§cInvalid stage number!");
            } else {
                sender.sendMessage("§aStage set to " + stage);
                StageManager.setStage(StageManager.getStage(stage));
            }
        }

        if (args[0].equalsIgnoreCase("advance")) {
            sender.sendMessage("§aStage advanced!");
            StageManager.setStage(StageManager.getNextStage());
        }

        if (args[0].equalsIgnoreCase("demote")) {
            sender.sendMessage("§aStage demoted!");
            StageManager.setStage(StageManager.getPreviousStage());

        }

        if (args[0].equalsIgnoreCase("get")) {
            sender.sendMessage("§aCurrent stage: " + StageManager.getStageNumber());
        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        arguments2.clear();
        arguments.add("set");
        arguments.add("get");
        arguments.add("advance");
        arguments.add("demote");

        if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            arguments2.add("1");
            arguments2.add("2");
            arguments2.add("3");
            arguments2.add("4");
            arguments2.add("5");
            arguments2.add("6");
            arguments2.add("7");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }
        if (args.length == 2) {
            for (String a : arguments2) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        return null;
    }
}
