package yusama125718.man10_serialcode;

import org.bukkit.inventory.ItemStack;

public class Data {
    public static class SerialCode{
        public String name;
        public String code;
        public ItemStack reward;
        public Integer count;
        public Integer publiccount;
        public Byte span;
        public Integer sub;
        public SerialCode(String Name, String Code, ItemStack Reward, Integer pCount, Integer Count, Byte Span, Integer Sub){
            name = Name;
            code = Code;
            reward = Reward;
            publiccount = pCount;
            count = Count;
            span = Span;
            sub = Sub;
        }
    }
}