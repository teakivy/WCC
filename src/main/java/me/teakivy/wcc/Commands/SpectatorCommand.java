package me.teakivy.wcc.Commands;

import me.teakivy.wcc.Utils.AbstractCommand;
import me.teakivy.wcc.Utils.FakeSpec;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Collections;

public class SpectatorCommand  extends AbstractCommand {
    public SpectatorCommand() {
        super("spectator", "/spectator", "WCC Spectator Command", Collections.singletonList("spec"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (!player.isOp()) {
            sender.sendMessage("§cYou don't have permission to do that!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§cUsage: /spectator <player>");
            return true;
        }

        boolean toSpectator = Boolean.parseBoolean(args[0]);

        try {
            FakeSpec.setSpectator(player, toSpectator);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}