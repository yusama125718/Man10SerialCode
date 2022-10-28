package yusama125718.man10_serialcode;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import javax.swing.plaf.IconUIResource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static yusama125718.man10_serialcode.GUI.RewardGUI;
import static yusama125718.man10_serialcode.Man10_SerialCode.*;

import static yusama125718.man10_serialcode.Function.GetItem;

public class Event implements Listener {
    public Event(Man10_SerialCode plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void InputGUIClick(InventoryClickEvent e){
        if (!e.getView().title().equals(Component.text("[Man10SerialCode] 入力画面")) || e.getCurrentItem() == null) return;
        e.setCancelled(true);
        switch (e.getRawSlot()){
            case 50: //0
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "0", 48));
                    break;
                }
                break;

            case 40: //1
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "1", 49));
                    break;
                }
                break;

            case 41: //2
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "2", 50));
                    break;
                }
                break;

            case 42: //3
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "3", 51));
                    break;
                }
                break;

            case 31: //4
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "4", 52));
                    break;
                }
                break;

            case 32: //5
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "5", 53));
                    break;
                }
                break;

            case 33: //6
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "6", 54));
                    break;
                }
                break;

            case 22: //7
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "7", 55));
                    break;
                }
                break;

            case 23: //8
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "8", 56));
                    break;
                }
                break;

            case 24: //9
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "9", 57));
                    break;
                }
                break;

            case 49: //-
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "-", 45));
                    break;
                }
                break;

            case 51: //*
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "*", 42));
                    break;
                }
                break;

            case 34: //clear
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) == null) continue;
                    e.getInventory().setItem(i, null);
                    break;
                }
                break;

            case 43: //delete
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    if (i == 0) break;
                    e.getInventory().setItem(i - 1, null);
                    break;
                }
                break;

            case 52:
                StringBuilder pass = new StringBuilder();
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) == null) continue;
                    if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "0", 48))) pass.append("0");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "1", 49))) pass.append("1");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "2", 50))) pass.append("2");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "3", 51))) pass.append("3");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "4", 52))) pass.append("4");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "5", 53))) pass.append("5");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "6", 54))) pass.append("6");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "7", 55))) pass.append("7");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "8", 56))) pass.append("8");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "9", 57))) pass.append("9");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "-", 45))) pass.append("-");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "*", 42))) pass.append("*");
                }
                if (pass.toString().equals("")) return;
                for (Data.SerialCode s : serial){
                    if (!s.code.equals(pass.toString())) continue;
                    players.put((Player) e.getWhoClicked(), s);
                    RewardGUI((Player) e.getWhoClicked(), s);
                    break;
                }
                break;
        }
    }

    @EventHandler
    public void RewardGUIClick(InventoryClickEvent e) {
        if (!e.getView().title().equals(Component.text("[Man10SerialCode] 受け取り画面")) || e.getCurrentItem() == null) return;
        e.setCancelled(true);
        if (e.getRawSlot() != 22) return;
        new Thread(() -> {
            Data.SerialCode t = players.get((Player) e.getWhoClicked());
            MySQLManager mysql = new MySQLManager(mserial, "mserial");
            int Count = 0;
            ResultSet res0 = null;
            if (t.mode) res0 = mysql.query("select count(*) from mserial_data where uuid = '"+ e.getWhoClicked().getUniqueId() +"' and code = '"+ t.code +"' and serial = '"+ t.name +"'");
            else res0 = mysql.query("select count(*) from mserial_data where code = '"+ t.code +"' and serial = '"+ t.name +"'");
            if (res0 == null){
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rDBの取得に失敗しました");
                e.getWhoClicked().closeInventory();
                try {
                    mysql.close();
                } catch (NullPointerException throwables) {
                    throwables.printStackTrace();
                }
                return;
            }
            try {
                Count = res0.getInt("count(*)");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                res0.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ResultSet res1 = mysql.query("select time, serial, code, name, uuid from mserial_data");
            if (res1 == null){
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rDBの取得に失敗しました");
                e.getWhoClicked().closeInventory();
                try {
                    mysql.close();
                } catch (NullPointerException throwables) {
                    throwables.printStackTrace();
                }
                return;
            }
            LocalDateTime time = null;
            while (true){
                try {
                    if (!res1.next()) break;
                    if (!res1.getString("code").equals(t.code) || !res1.getString("serial").equals(t.name) || !res1.getString("uuid").equals(e.getWhoClicked().getUniqueId().toString())) continue;
                    if (time != null && LocalDateTime.parse(res1.getString("time")).isAfter(time)) time = LocalDateTime.parse(res1.getString("time"));
                    else if (time == null) time = LocalDateTime.parse(res1.getString("time"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                res1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                mysql.close();
            } catch (NullPointerException throwables) {
                throwables.printStackTrace();
            }
            long between = 0;
            if (time == null) between = 30;
            else between = ChronoUnit.DAYS.between(time,LocalDateTime.now());
            if (t.span == 0){       //スパン無し
                if (e.getWhoClicked().getInventory().firstEmpty() == -1){
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rインベントリが満杯のため受け取れません");
                    return;
                }
                e.getWhoClicked().getInventory().addItem(t.reward);
            }
            else if (t.span == 1){      //スパン1日
                if (between >= 1){
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rクールタイムです");
                    return;
                } else {
                    if (Count >= t.count && t.count != 0) {
                        e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rこのコードは受け取り上限に達しています");
                        return;
                    }
                    e.getWhoClicked().getInventory().addItem(t.reward);
                }
            }
            else if (t.span == 2){      //スパン１週
                if (between >= 7){
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rクールタイムです");
                    return;
                } else {
                    if (Count >= t.count && t.count != 0) {
                        e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rこのコードは受け取り上限に達しています");
                        return;
                    }
                    e.getWhoClicked().getInventory().addItem(t.reward);
                }
            }
            else if (t.span == 3){      //スパン１月
                if (between >= 30){
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rクールタイムです");
                    return;
                } else {
                    if (Count >= t.count && t.count != 0) {
                        e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rこのコードは受け取り上限に達しています");
                        return;
                    }
                    e.getWhoClicked().getInventory().addItem(t.reward);
                }
            }
            mysql.execute("insert into mserial_data (time, serial, code, name, uuid) values ("+ LocalDateTime.now() +", "+ t.name +", "+ t.code +", "+ e.getWhoClicked().getName() +", "+ e.getWhoClicked().getUniqueId() +")");
            e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §r獲得しました");
            e.getWhoClicked().closeInventory();
        }).start();
    }

    @EventHandler
    public void RewardGUIClose(InventoryCloseEvent e) {
        if (!e.getView().title().equals(Component.text("[Man10SerialCode] 受け取り画面")) || players == null) return;
        if (!players.containsKey((Player) e.getPlayer())) return;
        players.remove((Player) e.getPlayer());
    }

    @EventHandler
    public void AddGUIClick(InventoryClickEvent e){
        if (!e.getView().title().equals(Component.text("[Man10SerialCode] 追加画面")) || e.getCurrentItem() == null) return;
        e.setCancelled(true);
        switch (e.getRawSlot()){
            case 50: //0
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "0", 48));
                    break;
                }
                break;

            case 40: //1
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "1", 49));
                    break;
                }
                break;

            case 41: //2
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "2", 50));
                    break;
                }
                break;

            case 42: //3
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "3", 51));
                    break;
                }
                break;

            case 31: //4
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "4", 52));
                    break;
                }
                break;

            case 32: //5
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "5", 53));
                    break;
                }
                break;

            case 33: //6
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "6", 54));
                    break;
                }
                break;

            case 22: //7
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "7", 55));
                    break;
                }
                break;

            case 23: //8
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "8", 56));
                    break;
                }
                break;

            case 24: //9
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "9", 57));
                    break;
                }
                break;

            case 49: //-
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "-", 45));
                    break;
                }
                break;

            case 51: //*
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "*", 42));
                    break;
                }
                break;

            case 34: //clear
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) == null) continue;
                    e.getInventory().setItem(i, null);
                    break;
                }
                break;

            case 43: //delete
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) != null) continue;
                    if (i == 0) break;
                    e.getInventory().setItem(i - 1, null);
                    break;
                }
                break;

            case 46:
                if (e.getCurrentItem().equals(GetItem(Material.CLOCK, 1, "スパン：無し", 0))) e.getInventory().setItem(46,GetItem(Material.CLOCK, 1, "スパン：1日", 0));
                else if (e.getCurrentItem().equals(GetItem(Material.CLOCK, 1, "スパン：1日", 0))) e.getInventory().setItem(46,GetItem(Material.CLOCK, 1, "スパン：1週", 0));
                else if (e.getCurrentItem().equals(GetItem(Material.CLOCK, 1, "スパン：1週", 0))) e.getInventory().setItem(46,GetItem(Material.CLOCK, 1, "スパン：1月", 0));
                else if (e.getCurrentItem().equals(GetItem(Material.CLOCK, 1, "スパン：1月", 0))) e.getInventory().setItem(46,GetItem(Material.CLOCK, 1, "スパン：無し", 0));
                break;

            case 48:
                if (e.getCurrentItem().equals(GetItem(Material.COMPASS, 1, "モード：個人制限", 0))) e.getInventory().setItem(48, GetItem(Material.COMPASS, 1, "モード：全体制限", 0));
                else if (e.getCurrentItem().equals(GetItem(Material.COMPASS, 1, "モード：全体制限", 0))) e.getInventory().setItem(48, GetItem(Material.COMPASS, 1, "モード：個人制限", 0));
                break;

            case 52:
                if (e.getInventory().getItem(38) == null) return;
                StringBuilder pass = new StringBuilder();
                for (int i = 0; i < 9; i++){
                    if (e.getInventory().getItem(i) == null) continue;
                    if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "0", 48))) pass.append("0");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "1", 49))) pass.append("1");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "2", 50))) pass.append("2");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "3", 51))) pass.append("3");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "4", 52))) pass.append("4");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "5", 53))) pass.append("5");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "6", 54))) pass.append("6");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "7", 55))) pass.append("7");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "8", 56))) pass.append("8");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "9", 57))) pass.append("9");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "-", 45))) pass.append("-");
                    else if (e.getInventory().getItem(i).equals(GetItem(Material.QUARTZ, 1, "*", 42))) pass.append("*");
                }
                if (pass.toString().equals("")) return;
                boolean mode = e.getInventory().getItem(48).equals(GetItem(Material.COMPASS, 1, "モード：全体制限", 0));
                byte span = 0;
                if (e.getInventory().getItem(46).equals(GetItem(Material.CLOCK, 1, "スパン：1日", 0))) span = 1;
                if (e.getInventory().getItem(46).equals(GetItem(Material.CLOCK, 1, "スパン：1週", 0))) span = 2;
                if (e.getInventory().getItem(46).equals(GetItem(Material.CLOCK, 1, "スパン：1月", 0))) span = 3;
                Data.AddSerial t = addlist.get((Player) e.getWhoClicked());
                Data.SerialCode s = new Data.SerialCode(t.name, pass.toString(), e.getInventory().getItem(38), mode, t.count, span);
                Config.CreateSerial(s);
                serial.add(s);
                break;
        }
    }
}
