package yusama125718.man10_serialcode;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static yusama125718.man10_serialcode.Function.GetItem;
import static yusama125718.man10_serialcode.Man10_SerialCode.serial;

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
        inv.setItem(52, GetItem(Material.EMERALD_BLOCK, 1, "決定", 0));
        inv.setItem(19, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(20, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(21, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(30, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(28, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(39, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(37, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(38, GetItem(Material.BLACK_STAINED_GLASS_PANE, 1, "枠内にリワードをセット", 1));
        inv.setItem(46, GetItem(Material.CLOCK, 1, "スパン：無し", 0));
        inv.setItem(47, GetItem(Material.TOTEM_OF_UNDYING, 1, "IP制限：OFF", 0));
        inv.setItem(48, GetItem(Material.COMPASS, 1, "モード：個人制限", 0));
        p.openInventory(inv);
    }

    public static void ListGUI(Player p, Integer page){
        Inventory inv = Bukkit.createInventory(null,54, Component.text("[Man10SerialCode] SerialList"+ page));
        for (int i = 51;i < 54;i++){
            inv.setItem(i,GetItem(Material.BLUE_STAINED_GLASS_PANE,1,"次のページへ",1));
            inv.setItem(i - 3,GetItem(Material.WHITE_STAINED_GLASS_PANE,1,"",1));
            inv.setItem(i - 6,GetItem(Material.RED_STAINED_GLASS_PANE,1,"前のページへ",1));
        }
        for (int i = 0;i < serial.size();i++){
            if (i == 45 || serial.size() == i + 45 * (page - 1)){
                p.openInventory(inv);
                return;
            }
            Data.SerialCode list;
            list = serial.get(i + 45 * (page));
            ItemStack item = new ItemStack(list.reward);
            inv.setItem(i, item);
        }
        p.openInventory(inv);
    }

    public static void SerialExample(Player p, Integer n){
        Inventory inv = Bukkit.createInventory(null,36, Component.text("[Man10SerialCode] Example "+ serial.get(n).name));
        String s = serial.get(n).code;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') inv.setItem(i, GetItem(Material.QUARTZ, 1, "0", 48));
            else if (s.charAt(i) == '1') inv.setItem(i, GetItem(Material.QUARTZ, 1, "1", 49));
            else if (s.charAt(i) == '2') inv.setItem(i, GetItem(Material.QUARTZ, 1, "2", 50));
            else if (s.charAt(i) == '3') inv.setItem(i, GetItem(Material.QUARTZ, 1, "3", 51));
            else if (s.charAt(i) == '4') inv.setItem(i, GetItem(Material.QUARTZ, 1, "4", 52));
            else if (s.charAt(i) == '5') inv.setItem(i, GetItem(Material.QUARTZ, 1, "5", 53));
            else if (s.charAt(i) == '6') inv.setItem(i, GetItem(Material.QUARTZ, 1, "6", 54));
            else if (s.charAt(i) == '7') inv.setItem(i, GetItem(Material.QUARTZ, 1, "7", 55));
            else if (s.charAt(i) == '8') inv.setItem(i, GetItem(Material.QUARTZ, 1, "8", 56));
            else if (s.charAt(i) == '9') inv.setItem(i, GetItem(Material.QUARTZ, 1, "9", 57));
            else if (s.charAt(i) == '-') inv.setItem(i, GetItem(Material.QUARTZ, 1, "-", 45));
            else if (s.charAt(i) == '*') inv.setItem(i, GetItem(Material.QUARTZ, 1, "*", 42));
        }
        for (int i = 9; i < 36; i++) inv.setItem(i, GetItem(Material.WHITE_STAINED_GLASS_PANE, 1, "", 1));
        inv.setItem(20, serial.get(n).reward);
        if (serial.get(n).span == 0) inv.setItem(23, GetItem(Material.CLOCK, 1, "スパン：無し", 0));
        else if (serial.get(n).span == 1) inv.setItem(23, GetItem(Material.CLOCK, 1, "スパン：1日", 0));
        else if (serial.get(n).span == 2) inv.setItem(23, GetItem(Material.CLOCK, 1, "スパン：1週", 0));
        else if (serial.get(n).span == 3) inv.setItem(23, GetItem(Material.CLOCK, 1, "スパン：1月", 0));
        if (serial.get(n).sub == 0) inv.setItem(24, GetItem(Material.TOTEM_OF_UNDYING, 1, "IP制限：無し", 0));
        else inv.setItem(24, GetItem(Material.TOTEM_OF_UNDYING, 1, "IP制限："+ serial.get(n).sub, 0));
        if (serial.get(n).mode) inv.setItem(25, GetItem(Material.COMPASS, 1, "モード：全体制限", 0));
        else inv.setItem(25,GetItem(Material.COMPASS, 1, "モード：個人制限", 0));
        inv.setItem(29,GetItem(Material.BLACK_STAINED_GLASS_PANE, 1, "リワードの受け取り回数："+serial.get(n).count, 1));
        p.openInventory(inv);
    }
}
