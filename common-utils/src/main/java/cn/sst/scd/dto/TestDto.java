package cn.sst.scd.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/11/10 9:22 上午
 * @Version 1.1.0
 **/
public class TestDto implements Serializable {
    /**
     * 几天前
     **/
    private static final Integer BEFORE_DAY_NUM = 10;
    /**
     * 几周后
     **/
    private static final Integer AFTER_WEEK = 7;

    public static void main(String[] args) {
        LocalDate date = LocalDate.from(new Date().toInstant().atZone(ZoneId.systemDefault()));
        System.out.println(Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static void printDateStr() {
        TestDto testDto = new TestDto();

    }

    public static String dateToStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

}
