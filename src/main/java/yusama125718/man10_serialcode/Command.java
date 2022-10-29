package yusama125718.man10_serialcode;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;
import static yusama125718.man10_serialcode.Man10_SerialCode.*;

public class Command implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("mserial.p")) return true;
        switch (args.length){
            case 0:
                if (!system){
                    sender.sendMessage("§c§l[Man10SerialCode] §r現在システム停止中です");
                    return true;
                }
                GUI.InputGUI((Player) sender);
                break;

            case 1:
                if (args[0].equals("help")){
                    if (sender.hasPermission("mserial.op")){
                        sender.sendMessage("§c§l[Man10SerialCode] §r/mserial on/off システムをon/offします");
                        sender.sendMessage("§c§l[Man10SerialCode] §r/mserial list シリアルコードのリストを表示します");
                        sender.sendMessage("§c§l[Man10SerialCode] §r/mserial debug on/off onにするとDBにつながらなくても処理できるようになります");
                        sender.sendMessage("§c§l[Man10SerialCode] §r/mserial add [内部名] [回数] シリアルコードを追加します(0で回数無限)");
                        sender.sendMessage("※モードが全体制限の時は回数は全体の回数になり、個人制限の時は1人毎の回数になります。");
                        sender.sendMessage("§c§l[Man10SerialCode] §r/mserial deletedata [内部名] [コード] DBのデータを削除します");
                    }
                    sender.sendMessage("§c§l[Man10SerialCode] §r/mserial 入力画面を開きます");
                    return true;
                }
                else if (args[0].equals("on") && sender.hasPermission("mserial.op")){
                    if (system){
                        sender.sendMessage("§c§l[Man10SerialCode] §rすでにonになっています");
                        return true;
                    }
                    system = true;
                    mserial.getConfig().set("system", system);
                    mserial.saveConfig();
                    sender.sendMessage("§c§l[Man10SerialCode] §ronにしました");
                    return true;
                }
                else if (args[0].equals("off") && sender.hasPermission("mserial.op")){
                    if (!system){
                        sender.sendMessage("§c§l[Man10SerialCode] §rすでにoffになっています");
                        return true;
                    }
                    system = false;
                    mserial.getConfig().set("system", system);
                    mserial.saveConfig();
                    sender.sendMessage("§c§l[Man10SerialCode] §roffにしました");
                    return true;
                }
                else if (args[0].equals("list") && sender.hasPermission("mserial.op")){
                    GUI.ListGUI((Player) sender,0);
                    return true;
                }
                sender.sendMessage("§c§l[Man10SerialCode] §r/mserial help でhelpを表示");
                break;

            case 2:
                if (args[0].equals("debug") && sender.hasPermission("mserial.op")){
                    if (args[1].equals("on")){
                        if (debug){
                            sender.sendMessage("§c§l[Man10SerialCode] §rすでにonになっています");
                            return true;
                        }
                        debug = true;
                        sender.sendMessage("§c§l[Man10SerialCode] §ronにしました");
                        return true;
                    }
                    else if (args[1].equals("off")){
                        if (!debug){
                            sender.sendMessage("§c§l[Man10SerialCode] §rすでにoffになっています");
                            return true;
                        }
                        debug = false;
                        sender.sendMessage("§c§l[Man10SerialCode] §roffにしました");
                        return true;
                    }
                }
                else if (args[0].equals("delete") && sender.hasPermission("mserial.op")){
                    Data.SerialCode t = null;
                    for (Data.SerialCode s : serial) if (s.name.equals(args[1])) t = s;
                    if (t == null){
                        sender.sendMessage("§c§l[Man10SerialCode] §rその名前は存在しません");
                        return true;
                    }
                    serial.remove(t);
                    sender.sendMessage("§c§l[Man10SerialCode] §r削除しました");
                    return true;
                }

            case 3:
                if (args[0].equals("add") && sender.hasPermission("mserial.op")){
                    if (args[1].length() > 20){
                        sender.sendMessage("§c§l[Man10SerialCode] §r内部名は20文字以下にしてください");
                        return true;
                    }
                    for (Data.SerialCode s : serial){
                        if (args[1].equals(s.name)){
                            sender.sendMessage("§c§l[Man10SerialCode] §rその名前はすでに存在してます");
                            return true;
                        }
                    }
                    boolean isNumeric = args[2].matches("-?\\d+");
                    if (!isNumeric){
                        sender.sendMessage("§c§l[Man10SerialCode] §r時間が無効です");
                        return true;
                    }
                    addlist.put((Player) sender, new Data.AddSerial(args[1], parseInt(args[2])));
                    GUI.AddGUI((Player) sender);
                    return true;
                }
                else if (args[0].equals("deletedata") && sender.hasPermission("mserial.op")){
                    if (args[1].length() > 20){
                        sender.sendMessage("§c§l[Man10SerialCode] §r内部名は20文字以下にしてください");
                        return true;
                    }
                    if (args[2].length() > 9) {
                        sender.sendMessage("§c§l[Man10SerialCode] §r不正なコードです");
                        return true;
                    }
                    Thread th = new Thread(() -> {
                        MySQLManager mysql = new MySQLManager(mserial, "mserial");
                        if (mysql.execute("delete from mserial_data where code = '"+ args[2] +"' and serial = '"+ args[1] +"'")) sender.sendMessage("§c§l[Man10SerialCode] §r削除しました");
                        else sender.sendMessage("§c§l[Man10SerialCode] §r削除に失敗ました");
                    });
                    th.start();
                    return true;
                }
                sender.sendMessage("§c§l[Man10SerialCode] §r/mserial help でhelpを表示");
                break;

            default:
                sender.sendMessage("§c§l[Man10SerialCode] §r/mserial help でhelpを表示");
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (!sender.hasPermission("mserial.p")) return null;
        if (args.length == 1){
            if (args[0].length() == 0){
                if (sender.hasPermission("mserial.op")) return Arrays.asList("add", "debug" , "delete", "help", "list", "off", "on");
                else return Collections.singletonList("help");
            } else if ("help".startsWith(args[0])) return Collections.singletonList("help");
            else if (sender.hasPermission("mserial.op")){
                if ("add".startsWith(args[0])) return Collections.singletonList("add");
                else if ("debug".startsWith(args[0]) && "delete".startsWith(args[0])) return Arrays.asList("debug", "delete");
                else if ("debug".startsWith(args[0])) return Collections.singletonList("debug");
                else if ("delete".startsWith(args[0])) return Collections.singletonList("delete");
                else if ("list".startsWith(args[0])) return Collections.singletonList("list");
                else if ("on".startsWith(args[0]) && "off".startsWith(args[0])) return Arrays.asList("on", "off");
                else if ("on".startsWith(args[0])) return Collections.singletonList("on");
                else if ("off".startsWith(args[0])) return Collections.singletonList("off");
            }
        } else if (args.length == 2 && sender.hasPermission("mserial,op")){
            if (args[0].equals("add")) return Collections.singletonList("[内部名]");
            else if (args[0].equals("debug")) return Arrays.asList("on", "off");
            else if (args[0].equals("delete")) {
                ArrayList<String> list = new ArrayList<>();
                for (Data.SerialCode s : serial) list.add(s.name);
                return list;
            }
        } else if (args.length == 3 && sender.hasPermission("mserial,op") && args[0].equals("add")) return Collections.singletonList("[回数]");
        return null;
    }
}
