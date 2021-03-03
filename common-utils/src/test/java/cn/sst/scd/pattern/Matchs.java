package cn.sst.scd.pattern;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/3/13 3:31 下午
 * @Version 1.1.0
 **/
public class Matchs {
    public static void main(String[] args) {
        String s = "sunshengteng sun   sheng  teng";
        String[] split = s.split(" +");
        ArrayList<String> list = new ArrayList<>();

        System.out.println(Arrays.toString(split));


    }
}
