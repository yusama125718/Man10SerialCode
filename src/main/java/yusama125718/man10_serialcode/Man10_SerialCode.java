package yusama125718.man10_serialcode;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Man10_SerialCode extends JavaPlugin {

    public static JavaPlugin mserial;
    public static List<Data.SerialCode> serial = new ArrayList<>();
    public static Boolean system;
    public static File configfile;
    public static HashMap<Player, Data.AddSerial> addlist = new HashMap<>();
    public static HashMap<Player, Data.SerialCode> players = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("magri").setExecutor(new Command());
        new Event(this);
        mserial = this;
        mserial.saveDefaultConfig();
        system = mserial.getConfig().getBoolean("system");
        MySQLManager mysql = new MySQLManager(mserial, "mserial");
        mysql.execute("create table if not exists mserial_data(id int auto_increment,time varchar(35),serial varchar(20), code varchar(9),name varchar(16),uuid varchar(36),primary key(id))");
    }
}
