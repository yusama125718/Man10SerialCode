package yusama125718.man10_serialcode;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static yusama125718.man10_serialcode.Function.GetItem;

public class GUI {
    public static void InputGUI(Player p){
        Inventory inv = Bukkit.createInventory(null,54, Component.text("[Man10SerialCode] 入力画面"));
        inv.setItem(22, GetItem(Material.QUARTZ, 1, "7", 55));
        inv.setItem(23, GetItem(Material.QUARTZ, 1, "8", 56));
        inv.setItem(24, GetItem(Material.QUARTZ, 1, "9", 57));
        inv.setItem(31, GetItem(Material.QUARTZ, 1, "4", 52));
        inv.setItem(32, GetItem(Material.QUARTZ, 1, "5", 53));
        inv.setItem(33, GetItem(Material.QUARTZ, 1, "6", 54));
        inv.setItem(34, GetItem(Material.TNT, 1, "クリア", 0));
        inv.setItem(40, GetItem(Material.QUARTZ, 1, "1", 49));
        inv.setItem(41, GetItem(Material.QUARTZ, 1, "2", 50));
        inv.setItem(42, GetItem(Material.QUARTZ, 1, "3", 51));
        inv.setItem(43, GetItem(Material.REDSTONE_BLOCK, 1, "１文字消す", 0));
        inv.setItem(49, GetItem(Material.QUARTZ, 1, "-", 45));
        inv.setItem(50, GetItem(Material.QUARTZ, 1, "0", 48));
        inv.setItem(51, GetItem(Material.QUARTZ, 1, "*", 42));
        inv.setItem(52, GetItem(Material.EMERALD_BLOCK, 1, "決定", 1));
        p.openInventory(inv);
    }

    public static void RewardGUI(Player p, Data.SerialCode t){
        Inventory inv = Bukkit.createInventory(null,27, Component.text("[Man10SerialCode] 受け取り画面"));
        for (int i = 0;i < 27; i++) inv.setItem(i,GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(13, t.reward);
        inv.setItem(22, GetItem(Material.RED_STAINED_GLASS_PANE, 1, "受け取り", 1));
        p.openInventory(inv);
    }

    public static void AddGUI(Player p){
        Inventory inv = Bukkit.createInventory(null,54, Component.text("[Man10SerialCode] 追加画面"));
        inv.setItem(22, GetItem(Material.QUARTZ, 1, "7", 55));
        inv.setItem(23, GetItem(Material.QUARTZ, 1, "8", 56));
        inv.setItem(24, GetItem(Material.QUARTZ, 1, "9", 57));
        inv.setItem(31, GetItem(Material.QUARTZ, 1, "4", 52));
        inv.setItem(32, GetItem(Material.QUARTZ, 1, "5", 53));
        inv.setItem(33, GetItem(Material.QUARTZ, 1, "6", 54));
        inv.setItem(34, GetItem(Material.TNT, 1, "クリア", 0));
        inv.setItem(40, GetItem(Material.QUARTZ, 1, "1", 49));
        inv.setItem(41, GetItem(Material.QUARTZ, 1, "2", 50));
        inv.setItem(42, GetItem(Material.QUARTZ, 1, "3", 51));
        inv.setItem(43, GetItem(Material.REDSTONE_BLOCK, 1, "１文字消す", 0));
        inv.setItem(49, GetItem(Material.QUARTZ, 1, "-", 45));
        inv.setItem(50, GetItem(Material.QUARTZ, 1, "0", 48));
        inv.setItem(51, GetItem(Material.QUARTZ, 1, "*", 42));
        inv.setItem(52, GetItem(Material.EMERALD_BLOCK, 1, "決定", 1));
        inv.setItem(19, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(20, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(21, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(30, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(28, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(39, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(37, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(38, GetItem(Material.BLACK_STAINED_GLASS_PANE, 1, "枠内にリワードをセット", 1));
        inv.setItem(46, GetItem(Material.CLOCK, 1, "スパン：無し", 0));
        inv.setItem(48, GetItem(Material.COMPASS, 1, "モード：個人制限", 0));
        p.openInventory(inv);
    }
}
