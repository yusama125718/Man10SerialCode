package yusama125718.man10_serialcode;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
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
}
