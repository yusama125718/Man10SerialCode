package yusama125718.man10_serialcode;

import org.bukkit.inventory.ItemStack;

public class Data {
    public static class SerialCode{
        public String name;
        public String code;
        public ItemStack reward;
        public Boolean mode;
        public Integer count;
        public Byte span;
        public Integer sub;
        public SerialCode(String Name, String Code, ItemStack Reward, Boolean Mode, Integer Count, Byte Span, Integer Sub){
            name = Name;
            code = Code;
            reward = Reward;
            mode = Mode;
            count = Count;
            span = Span;
            sub = Sub;
        }
    }

    public static class AddSerial{
        public String name;
        public Integer count;
        public AddSerial(String Name, Integer Count){
            name = Name;
            count = Count;
        }
    }
}
