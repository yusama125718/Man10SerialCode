package yusama125718.man10_serialcode;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class Man10_SerialCode extends JavaPlugin {

    public static JavaPlugin mserial;
    public static List<Data.SerialCode> serial = new ArrayList<>();
    public static Boolean system;
    public static Boolean debug;
    public static File configfile;
    public static HashMap<UUID, String> addlist = new HashMap<>();
    public static HashMap<UUID, Data.SerialCode> players = new HashMap<>();
    public static HashMap<UUID, Data.SerialCode> addsublist = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("mserial").setExecutor(new Command());
        new Event(this);
        mserial = this;
        mserial.saveDefaultConfig();
        Config.LoadFile();
        if (configfile.listFiles() != null){
            for (File file : configfile.listFiles()){
                if (Config.getConfig(YamlConfiguration.loadConfiguration(file),file) != null) serial.add(Config.getConfig(YamlConfiguration.loadConfiguration(file),file));
            }
        }
        system = mserial.getConfig().getBoolean("system");
        debug = false;
        MySQLManager mysql = new MySQLManager(mserial, "mserial");
        mysql.execute("create table if not exists mserial_data(id int auto_increment,time varchar(35),serial varchar(20), code varchar(9),name varchar(16),uuid varchar(36),primary key(id))");
    }
}