package yusama125718.man10_serialcode;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static yusama125718.man10_serialcode.Man10_SerialCode.mserial;
import static yusama125718.man10_serialcode.Man10_SerialCode.configfile;

public class Config {
    private static final File folder = new File(mserial.getDataFolder().getAbsolutePath() + File.separator + "serial");

    public static void LoadFile(){
        if (mserial.getDataFolder().listFiles() != null){
            for (File file : Objects.requireNonNull(mserial.getDataFolder().listFiles())) {
                if (file.getName().equals("serial")) {
                    configfile = file;
                    return;
                }
            }
        }
        if (folder.mkdir()) {
            Bukkit.broadcast("§c§l[Man10SerialCode] §rレシピフォルダを作成しました", "mserial.op");
            configfile = folder;
        } else {
            Bukkit.broadcast("§c§l[Man10SerialCode] §rレシピフォルダの作成に失敗しました", "mserial.op");
        }
    }

    public static Data.SerialCode getConfig(YamlConfiguration config, File file){
        if (!Function.checknull(config)) {
            Bukkit.broadcast("§c§l[Man10SerialCode] §r" + file.getName() + "の読み込みに失敗しました","mserial.op");
            return null;
        }
        return new Data.SerialCode(config.getString("name"), config.getString("code"), config.getItemStack("reward"), config.getBoolean("mode"), config.getInt("count"), (byte) config.getInt("span"));
    }

    public static void CreateSerial(Data.SerialCode r){
        File folder = new File(configfile.getAbsolutePath() + File.separator + r.name + ".yml");
        YamlConfiguration yml = new YamlConfiguration();        //config作成
        yml.set("name", r.name);
        yml.set("code", r.code);
        yml.set("mode", r.mode);
        yml.set("count", r.count);
        yml.set("span", r.span);
        yml.set("reward", r.reward);
        try {
            yml.save(folder);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.broadcast(Component.text("§c§l[Man10SerialCode] §r" + r.name + "の保存に失敗しました"),"mserial.op");
        }
    }
}
