package yusama125718.man10_serialcode;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Function {
    public static ItemStack GetItem(Material mate, Integer amount, String name, Integer cmd){
        ItemStack item = new ItemStack(mate,amount);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        meta.setCustomModelData(cmd);
        item.setItemMeta(meta);
        return item;
    }

    public static boolean checknull(YamlConfiguration file){
        return file.getString("name") != null && file.getString("code") != null && file.getItemStack("reward") != null && file.getString("mode") != null && file.getString("span") != null && file.getString("count") != null && file.getString("sub") != null;
    }

    public static StringBuilder AddNumber(Inventory inv){
        StringBuilder pass = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (inv.getItem(i) == null) continue;
            if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "0", 48))) pass.append("0");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "1", 49))) pass.append("1");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "2", 50))) pass.append("2");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "3", 51))) pass.append("3");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "4", 52))) pass.append("4");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "5", 53))) pass.append("5");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "6", 54))) pass.append("6");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "7", 55))) pass.append("7");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "8", 56))) pass.append("8");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "9", 57))) pass.append("9");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "-", 45))) pass.append("-");
            else if (inv.getItem(i).isSimilar(GetItem(Material.QUARTZ, 1, "*", 42))) pass.append("*");
        }
        return pass;
    }
}
