package yusama125718.man10_serialcode;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import red.man10.man10score.ScoreDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.event.ClickEvent.suggestCommand;
import static yusama125718.man10_serialcode.Function.AddNumber;
import static yusama125718.man10_serialcode.GUI.*;
import static yusama125718.man10_serialcode.Man10_SerialCode.*;

import static yusama125718.man10_serialcode.Function.GetItem;

public class Event implements Listener {
    public Event(Man10_SerialCode plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void InputGUIClick(InventoryClickEvent e){
        if (!e.getView().title().equals(text("[Man10SerialCode] 入力画面")) || e.getCurrentItem() == null) return;
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
                }
                break;

            case 43: //delete
                for (int i = 0; i < 9; i++){
                    if (i == 8){
                        e.getInventory().setItem(i, null);
                        break;
                    }
                    if (e.getInventory().getItem(i) != null) continue;
                    if (i == 0) break;
                    e.getInventory().setItem(i - 1, null);
                    break;
                }
                break;

            case 52:
                StringBuilder pass = AddNumber(e.getInventory());
                if (pass.toString().equals("")) return;
                for (Data.SerialCode s : serial){
                    if (!s.code.equals(pass.toString())) continue;
                    players.put(e.getWhoClicked().getUniqueId(), s);
                    RewardGUI((Player) e.getWhoClicked(), s);
                    break;
                }
                break;
        }
    }

    @EventHandler
    public void RewardGUIClick(InventoryClickEvent e) {
        if (!e.getView().title().equals(text("[Man10SerialCode] 受け取り画面")) || e.getCurrentItem() == null) return;
        e.setCancelled(true);
        if (e.getRawSlot() != 22) return;
        Thread th = new Thread(() -> {
            Data.SerialCode t = players.get(e.getWhoClicked().getUniqueId());
            MySQLManager mysql = new MySQLManager(mserial, "mserial");
            int Count = 0;
            ResultSet res = mysql.query("select time, serial, code, name, uuid from mserial_data where code = '"+ t.code +"' and serial = '"+ t.name +"'");
            LocalDateTime time = null;
            List<UUID> useaccount = new ArrayList<>();
            if (res == null){
                if (debug && e.getWhoClicked().hasPermission("mserial.op")){
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rDBの接続に失敗しましたがそのまま続行します");
                    Bukkit.broadcast(text("§c§l[Man10SerialCode] §rデバッグ：DBの取得に失敗しましたがそのまま続行します"),"mserial.op");
                } else {
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rDBの取得に失敗しました");
                    e.getWhoClicked().closeInventory();
                    try {
                        mysql.close();
                    } catch (NullPointerException throwables) {
                        throwables.printStackTrace();
                    }
                    return;
                }
            } else {
                List<UUID> account = ScoreDatabase.INSTANCE.getSubAccount(e.getWhoClicked().getUniqueId());
                while (true){
                    try {
                        if (!res.next()) break;
                        if (t.sub == 0 && res.getString("uuid").equals(e.getWhoClicked().getUniqueId().toString())) {
                            if (time != null && LocalDateTime.parse(res.getString("time")).isAfter(time)) time = LocalDateTime.parse(res.getString("time"));
                            else if (time == null) time = LocalDateTime.parse(res.getString("time"));
                        }
                        else if (account.contains(UUID.fromString(res.getString("uuid")))){
                            if (time != null && LocalDateTime.parse(res.getString("time")).isAfter(time)) time = LocalDateTime.parse(res.getString("time"));
                            else if (time == null) time = LocalDateTime.parse(res.getString("time"));
                        }
                        if (t.sub != 0 && account.contains(UUID.fromString(res.getString("uuid"))) && !useaccount.contains(UUID.fromString(res.getString("uuid")))) useaccount.add(UUID.fromString(res.getString("uuid")));
                        if (t.mode) Count++;
                        else if (res.getString("uuid").equals(e.getWhoClicked().getUniqueId().toString())) Count++;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                try {
                    res.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            try {
                mysql.close();
            } catch (NullPointerException throwables) {
                throwables.printStackTrace();
            }
            long between = 0;
            if (time == null) between = -1;
            else between = ChronoUnit.DAYS.between(time,LocalDateTime.now());
            if (t.sub != 0 && !useaccount.contains(e.getWhoClicked().getUniqueId()) && useaccount.size() >= t.sub){
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rこのコードで受け取れるアカウント数を超えています");
                return;
            }
            if (Count >= t.count && t.count != 0) {
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rこのコードは受け取り上限に達しています");
                return;
            }
            if (t.span == 1 && between <= 1 && between != -1){      //スパン1日
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rクールタイムです");
                return;
            }
            else if (t.span == 2 && between <= 7 && between != -1){      //スパン１週
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rクールタイムです");
                return;
            }
            else if (t.span == 3 && between <= 30 && between != -1){      //スパン１月
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rクールタイムです");
                return;
            }
            else if (t.span == 4 && between != -1){      //スパン１月
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rクールタイムです");
                return;
            }
            if (e.getWhoClicked().getInventory().firstEmpty() == -1){
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rインベントリが満杯のため受け取れません");
                return;
            }
            if (!mysql.execute("insert into mserial_data (time, serial, code, name, uuid) values ('"+ LocalDateTime.now() +"', '"+ t.name +"', '"+ t.code +"', '"+ e.getWhoClicked().getName() +"', '"+ e.getWhoClicked().getUniqueId() +"');")){
                if (debug && e.getWhoClicked().hasPermission("mserial.op")){
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rDBの接続に失敗しましたがそのまま続行します");
                    Bukkit.broadcast(text("§c§l[Man10SerialCode] §rデバッグ：DBの取得に失敗しましたがそのまま続行します"),"mserial.op");
                } else {
                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rDBの接続に失敗しました");
                    e.getWhoClicked().closeInventory();
                    return;
                }
            }
            Bukkit.getScheduler().runTask(mserial, new Runnable()
            {
                @Override
                public void run()
                {
                    e.getWhoClicked().getInventory().addItem(t.reward.clone());
                }
            });
            e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §r獲得しました");
            if (debug) Bukkit.broadcast(text("§c§l[Man10SerialCode] §rデバッグ："+e.getWhoClicked().getName()+"が"+ t.name +"を受け取りました。"),"mserial.op");
        });
        th.start();
        try {
            th.join();
        } catch (InterruptedException ignored) {}
        e.getInventory().close();
    }

    @EventHandler
    public void RewardGUIClose(InventoryCloseEvent e) {
        if (!e.getView().title().equals(text("[Man10SerialCode] 受け取り画面")) || players == null) return;
        if (!players.containsKey(e.getPlayer().getUniqueId())) return;
        players.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void AddGUIClick(InventoryClickEvent e) {
        if (!e.getView().title().equals(text("[Man10SerialCode] 追加画面")) || e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasCustomModelData()) return;
        Material mate = e.getCurrentItem().getType();
        int cmd = e.getCurrentItem().getItemMeta().getCustomModelData();
        if (!(mate.equals(Material.QUARTZ) && (cmd == 48 || cmd == 49 || cmd == 50 || cmd == 51 || cmd == 52 || cmd == 53 || cmd == 54 || cmd == 55 || cmd == 56 || cmd == 57 || cmd == 42 || cmd == 45)) && !(mate.equals(Material.WHITE_STAINED_GLASS_PANE) && cmd == 1) && !(mate.equals(Material.BLACK_STAINED_GLASS_PANE) && cmd == 1) && !(mate.equals(Material.CLOCK) && cmd == 0) && !(mate.equals(Material.COMPASS) && cmd == 0) && !(mate.equals(Material.TNT) && cmd == 0) && !(mate.equals(Material.REDSTONE_BLOCK) && cmd == 0) && !(mate.equals(Material.EMERALD_BLOCK) && cmd == 0) && !(mate.equals(Material.TOTEM_OF_UNDYING) && cmd == 0)) return;
        e.setCancelled(true);
        switch (e.getRawSlot()) {
            case 50: //0
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "0", 48));
                    break;
                }
                break;

            case 40: //1
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "1", 49));
                    break;
                }
                break;

            case 41: //2
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "2", 50));
                    break;
                }
                break;

            case 42: //3
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "3", 51));
                    break;
                }
                break;

            case 31: //4
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "4", 52));
                    break;
                }
                break;

            case 32: //5
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "5", 53));
                    break;
                }
                break;

            case 33: //6
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "6", 54));
                    break;
                }
                break;

            case 22: //7
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "7", 55));
                    break;
                }
                break;

            case 23: //8
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "8", 56));
                    break;
                }
                break;

            case 24: //9
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "9", 57));
                    break;
                }
                break;

            case 49: //-
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "-", 45));
                    break;
                }
                break;

            case 51: //*
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) != null) continue;
                    e.getInventory().setItem(i, GetItem(Material.QUARTZ, 1, "*", 42));
                    break;
                }
                break;

            case 34: //clear
                for (int i = 0; i < 9; i++) {
                    if (e.getInventory().getItem(i) == null) continue;
                    e.getInventory().setItem(i, null);
                }
                break;

            case 43: //delete
                for (int i = 0; i < 9; i++) {
                    if (i == 8){
                        e.getInventory().setItem(i, null);
                        break;
                    }
                    if (e.getInventory().getItem(i) != null) continue;
                    if (i == 0) break;
                    e.getInventory().setItem(i - 1, null);
                    break;
                }
                break;

            case 46:
                if (e.getCurrentItem().isSimilar(GetItem(Material.CLOCK, 1, "スパン：無し", 0))) e.getInventory().setItem(46, GetItem(Material.CLOCK, 1, "スパン：1日", 0));
                else if (e.getCurrentItem().isSimilar(GetItem(Material.CLOCK, 1, "スパン：1日", 0))) e.getInventory().setItem(46, GetItem(Material.CLOCK, 1, "スパン：1週", 0));
                else if (e.getCurrentItem().isSimilar(GetItem(Material.CLOCK, 1, "スパン：1週", 0))) e.getInventory().setItem(46, GetItem(Material.CLOCK, 1, "スパン：1月", 0));
                else if (e.getCurrentItem().isSimilar(GetItem(Material.CLOCK, 1, "スパン：1月", 0))) e.getInventory().setItem(46, GetItem(Material.CLOCK, 1, "スパン：無限", 0));
                else if (e.getCurrentItem().isSimilar(GetItem(Material.CLOCK, 1, "スパン：無限", 0))) e.getInventory().setItem(46, GetItem(Material.CLOCK, 1, "スパン：無し", 0));
                break;

            case 48:
                if (e.getCurrentItem().isSimilar(GetItem(Material.COMPASS, 1, "モード：個人制限", 0))) e.getInventory().setItem(48, GetItem(Material.COMPASS, 1, "モード：全体制限", 0));
                else if (e.getCurrentItem().isSimilar(GetItem(Material.COMPASS, 1, "モード：全体制限", 0))) e.getInventory().setItem(48, GetItem(Material.COMPASS, 1, "モード：個人制限", 0));
                break;

            case 52:
                if (e.getInventory().getItem(29) == null) return;
                StringBuilder pass = AddNumber(e.getInventory());
                if (pass.toString().equals("")) return;
                Data.AddSerial t = addlist.get(e.getWhoClicked().getUniqueId());
                Thread th = new Thread(() -> {
                    MySQLManager mysql = new MySQLManager(mserial, "mserial");
                    ResultSet res = mysql.query("select count(*) from mserial_data where code = '"+ pass +"' and serial = '"+ t.name +"'");
                    if (res == null) {
                        e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §rDBの接続に失敗しました");
                    } else {
                        try {
                            if (res.next()) {
                                if (res.getInt("count(*)") != 0){
                                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §r"+ t.name +"で"+ pass +"のコードはデータが存在しています");
                                    e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §r削除したい場合は /mserial deletedata [内部名] [コード] で削除できます");
                                }
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        try {
                            res.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    try {
                        mysql.close();
                    } catch (NullPointerException throwables) {
                        throwables.printStackTrace();
                    }
                });
                th.start();
                boolean mode = e.getInventory().getItem(48).equals(GetItem(Material.COMPASS, 1, "モード：全体制限", 0));
                byte span = 0;
                if (e.getInventory().getItem(46).equals(GetItem(Material.CLOCK, 1, "スパン：1日", 0))) span = 1;
                if (e.getInventory().getItem(46).equals(GetItem(Material.CLOCK, 1, "スパン：1週", 0))) span = 2;
                if (e.getInventory().getItem(46).equals(GetItem(Material.CLOCK, 1, "スパン：1月", 0))) span = 3;
                if (e.getInventory().getItem(46).equals(GetItem(Material.CLOCK, 1, "スパン：無限", 0))) span = 4;
                Data.SerialCode s = new Data.SerialCode(t.name, pass.toString(), e.getInventory().getItem(29), mode, t.count, span, 0);
                addsublist.remove(e.getWhoClicked().getUniqueId());
                addsublist.put(e.getWhoClicked().getUniqueId(), s);
                e.getWhoClicked().sendMessage("§c§l[Man10SerialCode] §r/mserial sub [数] で制限する数を設定してください");
                e.getWhoClicked().sendMessage("§0を入力することでOFFになります");
                e.getWhoClicked().sendMessage(text("§c§l[ここをクリックで自動入力する]").clickEvent(suggestCommand("/mserial sub ")));
                e.getInventory().close();
                break;
        }
    }

    @EventHandler
    public void ListGUIClick(InventoryClickEvent e) {     //レシピ確認用
        if (e.getInventory().getSize() != 54) return;
        String title = null;
        Component component = e.getView().title();
        if (component instanceof TextComponent text) title = text.content();
        if (title == null || title.length() != 29 || !title.startsWith("[Man10SerialCode] SerialList")) return;
        if (e.getCurrentItem() == null) {
            e.setCancelled(true);
            return;
        }
        boolean isNumeric = title.substring(28).matches("-?\\d+");
        if (!isNumeric) return;
        int page = parseInt(title.substring(28));
        if (51 <= e.getRawSlot() && e.getRawSlot() <= 53 && e.getCurrentItem().getType().equals(Material.BLUE_STAINED_GLASS_PANE)){    //次のページへ
            if (serial.size() / 45 > page) ListGUI((Player) e.getWhoClicked(),page + 1);
            e.setCancelled(true);
            return;
        }
        if (45 <= e.getRawSlot() && e.getRawSlot() <= 47 && e.getCurrentItem().getType().equals(Material.RED_STAINED_GLASS_PANE)){     //前のページへ
            if (page != 1) ListGUI((Player) e.getWhoClicked(),page);
            e.setCancelled(true);
            return;
        }
        if (45 <= e.getRawSlot() && e.getRawSlot() <= 53 || e.getRawSlot() + 45 * (page) >= serial.size()) {
            e.setCancelled(true);
            return;
        }
        SerialExample((Player) e.getWhoClicked(), e.getRawSlot() + 45 * (page));
        e.setCancelled(true);
    }

    @EventHandler
    public void ExampleGUIClick(InventoryClickEvent e) {     //レシピ詳細確認用
        if (e.getInventory().getSize() != 36) return;
        String title = null;
        Component component = e.getView().title();
        if (component instanceof TextComponent text) title = text.content();
        if (title == null || !title.startsWith("[Man10SerialCode] Example ")) return;
        e.setCancelled(true);
    }
}