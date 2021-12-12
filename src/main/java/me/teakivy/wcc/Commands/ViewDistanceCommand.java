package me.teakivy.wcc.Commands;

import me.teakivy.wcc.Main;
import me.teakivy.wcc.Utils.AbstractCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.*;

public class ViewDistanceCommand extends AbstractCommand {
    public ViewDistanceCommand() {
        super("viewdistance", "/viewdistance", "Change the server's View Distance", Arrays.asList("vd", "renderdistance"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§cUsage: /viewdistance <set|reset> [distance]");
            return true;
        }

        if (args[0].equalsIgnoreCase("set")) {
            if (args.length == 1) {
                sender.sendMessage("§cUsage: /viewdistance set <distance>");
                return true;
            }

            int distance;
            try {
                distance = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cInvalid distance: " + args[1]);
                return true;
            }

            if (distance < 1 || distance > 32) {
                sender.sendMessage("§cInvalid distance: " + args[1]);
                return true;
            }

            sender.sendMessage("§aSet the server's View Distance to " + distance);
            for (World world : Main.getPlugin(Main.class).getServer().getWorlds()) {
                world.setViewDistance(distance);
            }

            return true;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            sender.sendMessage("§aReset the server's View Distance to 8");
            Objects.requireNonNull(Bukkit.getWorld("world")).setViewDistance(8);
            Objects.requireNonNull(Bukkit.getWorld("world_nether")).setViewDistance(8);
            Objects.requireNonNull(Bukkit.getWorld("world_the_end")).setViewDistance(8);

            return true;
        }

        return false;
    }

    List<String> arguments = new ArrayList<String>();
    List<String> arguments2 = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        arguments2.clear();
        arguments.add("set");
        arguments.add("reset");

        if (args.length == 2 && args[0].equalsIgnoreCase("set")) {
            arguments2.add("2");
            arguments2.add("4");
            arguments2.add("8");
            arguments2.add("16");
            arguments2.add("32");
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
