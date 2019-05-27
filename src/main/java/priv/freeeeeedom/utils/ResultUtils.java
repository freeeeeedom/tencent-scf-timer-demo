package priv.freeeeeedom.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

public class ResultUtils {


    public static String getNumDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        SimpleDateFormat sfs = new SimpleDateFormat("yyyyMMdd");
        return sfs.format(cal.getTime());
    }

    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     *
     * @param source
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        // (先计算出余数)
        int remaider = source.size() % n;
        // 然后是商
        int number = source.size() / n;
        // 偏移量
        int offset = 0;
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    public static String getExceptionLineSize(Exception e, int count) {
        StringBuffer sb = new StringBuffer(e.toString() + " Cause by:");
        for (int i = 0; i < count; i++) {
            sb.append(e.getStackTrace()[i]).append(";\n");
        }
        return sb.toString();
    }

    public static List<List> splitList(List list, int splitSize) {
        List<List> result = new ArrayList();
        int count = (list.size() / splitSize);
        if (list.size() % splitSize != 0) {
            count = count + 1;
        }
        for (int i = 0; i < count; i++) {
            if (i * splitSize + splitSize > list.size()) {
                result.add(list.subList(i * splitSize, list.size()));
                continue;
            }
            result.add(list.subList(i * splitSize, i * splitSize + splitSize));
        }

        return result;
    }

    /**
     * 返回一定时间后的日期
     *
     * @param date   开始计时的时间
     * @param year   增加的年
     * @param month  增加的月
     * @param day    增加的日
     * @param hour   增加的小时
     * @param minute 增加的分钟
     * @param second 增加的秒
     * @return
     */
    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if (year != 0) {
            cal.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            cal.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            cal.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != 0) {
            cal.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

}