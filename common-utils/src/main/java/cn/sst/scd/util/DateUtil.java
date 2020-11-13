package cn.sst.scd.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/11/11 9:11 上午
 * @Version 1.1.0
 **/
public class DateUtil {
    /**
     * 获取本周的第一天
     *
     * @param date:
     * @return java.util.Date
     * @author shengtengsun
     * @date 2020/11/11 9:12 上午
     **/
    public static Date getStartDayOfWeek(TemporalAccessor date) {
        TemporalField fieldIso = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate = localDate.with(fieldIso, 1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取本周的最后一天
     *
     * @param date:
     * @return java.util.Date
     * @author shengtengsun
     * @date 2020/11/11 9:12 上午
     **/
    public static Date getEndDayOfWeek(TemporalAccessor date) {
        TemporalField fieldIso = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate = localDate.with(fieldIso, 7);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取给定时间的前几天的日期
     *
     * @param now:
     * @param day:
     * @return java.util.Date
     * @author shengtengsun
     * @date 2020/11/11 9:26 上午
     **/
    public static Date getBeforeDay(LocalDate now, int day) {
        LocalDate resDate = now.plusDays(0 - Math.abs(day));
        return Date.from(resDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取当前日期往后推迟几周后的最后一天
     *
     * @param now:
     * @param week:
     * @return java.util.Date
     * @author shengtengsun
     * @date 2020/11/11 9:32 上午
     **/
    public static Date getLastDayOfAfterWeek(LocalDate now, int week) {
        return getEndDayOfWeek(now.plusWeeks(Math.abs(week)));
    }

    /**
     * 获取当前日期往后推迟几周后的第一天
     *
     * @param now:
     * @param week:
     * @return java.util.Date
     * @author shengtengsun
     * @date 2020/11/11 9:32 上午
     **/
    public static Date getFirstDayOfAfterWeek(LocalDate now, int week) {
        return getStartDayOfWeek(now.plusWeeks(Math.abs(week)));
    }

    /**
     * 根据开始时间、结束时间、获取范围内的所有日期(升序)
     *
     * @param startDate:
     * @param endDate:
     * @return java.util.List<java.time.LocalDate>
     * @author shengtengsun
     * @date 2020/11/11 11:37 上午
     **/
    public static List<LocalDate> getAscDateList(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> result = new ArrayList<>();
        if (null == startDate || null == endDate) {
            return result;
        }
        LocalDate tempDate = startDate;
        while (endDate.toEpochDay() - tempDate.toEpochDay() >= 0) {
            result.add(tempDate);
            tempDate = tempDate.plusDays(1);
        }
        return result;
    }

    /**
     * 根据开始时间、结束时间、获取范围内的所有日期字符串(升序)
     *
     * @param start:
     * @param end:
     * @return java.util.List<java.lang.String>
     * @author shengtengsun
     * @date 2020/11/11 11:38 上午
     **/
    public static List<String> getAscDateStrList(Date start, Date end) {

        List<String> result = new ArrayList<>();
        if (null == start || null == end) {
            return result;
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tempDate = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()).toLocalDate();
        while (endDate.toEpochDay() - tempDate.toEpochDay() >= 0) {
            result.add(tempDate.format(dtf));
            tempDate = tempDate.plusDays(1);
        }
        return result;
    }
}