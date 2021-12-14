package me.teakivy.wcc.Utils;

import me.teakivy.wcc.Main;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.Arrays;

public class FakeSpec{

    public static void setSpectator(Player player, boolean spectator) throws IOException {
        if (spectator) {
            if (!player.getScoreboardTags().contains("spectator")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, true, true));
                player.addScoreboardTag("spectator");

                PersistentDataContainer container = player.getPersistentDataContainer();
                container.set(new NamespacedKey(Main.getInstance(), "lastGameMode"), PersistentDataType.STRING, player.getGameMode().toString());
                container.set(new NamespacedKey(Main.getInstance(), "specHealth"), PersistentDataType.STRING, player.getHealth() + "");
                container.set(new NamespacedKey(Main.getInstance(), "specHunger"), PersistentDataType.STRING, player.getFoodLevel() + "");
                container.set(new NamespacedKey(Main.getInstance(), "specSaturation"), PersistentDataType.STRING, player.getSaturation() + "");
                container.set(new NamespacedKey(Main.getInstance(), "specExhaustion"), PersistentDataType.STRING, player.getExhaustion() + "");
                container.set(new NamespacedKey(Main.getInstance(), "specLevel"), PersistentDataType.STRING, player.getLevel() + "");
                container.set(new NamespacedKey(Main.getInstance(), "specExp"), PersistentDataType.STRING, player.getExp() + "");

                container.set(new NamespacedKey(Main.getInstance(), "specInventory"), PersistentDataType.STRING, SerializeInventory.playerInventoryToBase64(player.getInventory()));

                player.setGameMode(GameMode.ADVENTURE);
                player.setAllowFlight(true);
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.setExhaustion(0);
                player.setLevel(0);
                player.setExp(0);
                player.getInventory().clear();
            }
        } else {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.removeScoreboardTag("spectator");

            PersistentDataContainer container = player.getPersistentDataContainer();
            if (container.has(new NamespacedKey(Main.getInstance(), "lastGameMode"), PersistentDataType.STRING)) {
                player.setGameMode(GameMode.valueOf(container.get(new NamespacedKey(Main.getInstance(), "lastGameMode"), PersistentDataType.STRING)));
            } else {
                player.setGameMode(GameMode.SURVIVAL);
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "specHealth"), PersistentDataType.STRING)) {
                player.setHealth(Double.parseDouble(container.get(new NamespacedKey(Main.getInstance(), "specHealth"), PersistentDataType.STRING)));
            } else {
                player.setHealth(20);
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "specHunger"), PersistentDataType.STRING)) {
                player.setFoodLevel(Integer.parseInt(container.get(new NamespacedKey(Main.getInstance(), "specHunger"), PersistentDataType.STRING)));
            } else {
                player.setFoodLevel(20);
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "specSaturation"), PersistentDataType.STRING)) {
                player.setSaturation((float) Double.parseDouble(container.get(new NamespacedKey(Main.getInstance(), "specSaturation"), PersistentDataType.STRING)));
            } else {
                player.setSaturation(20);
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "specExhaustion"), PersistentDataType.STRING)) {
                player.setExhaustion(Float.parseFloat(container.get(new NamespacedKey(Main.getInstance(), "specExhaustion"), PersistentDataType.STRING)));
            } else {
                player.setExhaustion(0);
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "specLevel"), PersistentDataType.STRING)) {
                player.setLevel(Integer.parseInt(container.get(new NamespacedKey(Main.getInstance(), "specLevel"), PersistentDataType.STRING)));
            } else {
                player.setLevel(0);
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "specExp"), PersistentDataType.STRING)) {
                player.setExp(Float.parseFloat(container.get(new NamespacedKey(Main.getInstance(), "specExp"), PersistentDataType.STRING)));
            } else {
                player.setExp(0);
            }

            if (container.has(new NamespacedKey(Main.getInstance(), "specInventory"), PersistentDataType.STRING)) {
                ItemStack[] items = SerializeInventory.itemStackArrayFromBase64(container.get(new NamespacedKey(Main.getInstance(), "specInventory"), PersistentDataType.STRING));
                player.getInventory().setContents(items);
            }

            container.remove(new NamespacedKey(Main.getInstance(), "lastGameMode"));
            container.remove(new NamespacedKey(Main.getInstance(), "specHealth"));
            container.remove(new NamespacedKey(Main.getInstance(), "specHunger"));
            container.remove(new NamespacedKey(Main.getInstance(), "specSaturation"));
            container.remove(new NamespacedKey(Main.getInstance(), "specExhaustion"));
            container.remove(new NamespacedKey(Main.getInstance(), "specLevel"));
            container.remove(new NamespacedKey(Main.getInstance(), "specExp"));
            container.remove(new NamespacedKey(Main.getInstance(), "specInventory"));
            if (player.getGameMode() != GameMode.SPECTATOR && player.getGameMode() != GameMode.CREATIVE) {
                player.setAllowFlight(false);
            }
        }
    }

    private static String[] fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        String result[] = new String[strings.length];
        System.arraycopy(strings, 0, result, 0, result.length);
        return result;
    }

}
