package cn.sst;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author shengtengsun
 * @Description
 * @Date 2020/7/16 8:58 上午
 * @Version 1.1.0
 **/
public class DateFormat {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.plusDays(-2);
        LocalDateTime endTime = now.plusDays(2);

        Date from = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(format(from, end));


    }

    public static String format(Date start, Date end) {

        StringBuffer buffer = new StringBuffer();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        ZoneId zoneId = ZoneId.systemDefault();
        Instant startIns = start.toInstant();
        Instant endTns = end.toInstant();

        LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(startIns, zoneId);
        LocalDateTime endDateTime = LocalDateTime.ofInstant(endTns, zoneId);
        String startTime = formatter.format(startLocalDateTime);
        String endTime = formatter.format(endDateTime);
        return buffer.append(startTime).append('-').append(endTime).toString();
    }
}
