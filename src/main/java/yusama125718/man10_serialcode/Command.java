package yusama125718.man10_serialcode;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
                        sender.sendMessage("§c§l[Man10SerialCode] §r/mserial add [内部名] [回数] シリアルコードを追加します(0で回数無限)");
                        sender.sendMessage("※モードが全体制限の時は回数は全体の回数になり、個人制限の時は1人毎の回数になります。");
                    }
                    sender.sendMessage("§c§l[Man10SerialCode] §r/mserial 入力画面を開きます");
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
                }
                sender.sendMessage("§c§l[Man10SerialCode] §r/mserial help でhelpを表示");
                break;

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
        return null;
    }
}
