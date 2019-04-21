package com.pine.common.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>日期时间工具类。</p>
 *
 * @author 索晨成 <br />
 * 更新履历 <br />
 * 日期 : 姓名: 更新内容<br />
 * @version 1.0
 */
public class DateTimeTool {

    /**
     * <p>时间间隔转换。</p>
     *
     * @param createtime
     * @return
     */
    public static String getInterval(Date createtime) { //传入的时间格式必须类似于2012-8-21 17:53:20这样的格式

        String interval = null;

        if (createtime == null) {
            return "侏罗纪";
        }
        Date d1 = createtime;

        //用现在距离1970年的时间间隔new Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔  
        long time = System.currentTimeMillis() - d1.getTime();// 得出的时间间隔是毫秒

        if (time / 1000 < 10 && time / 1000 >= 0) {
            //如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒  
            interval = "刚刚";

        } else if (time / 1000 < 60 && time / 1000 > 0) {
            //如果时间间隔小于60秒则显示多少秒前  
            int se = (int) ((time % 60000) / 1000);
            interval = se + "秒前";

        } else if (time / 60000 < 60 && time / 60000 > 0) {
            //如果时间间隔小于60分钟则显示多少分钟前  
            int m = (int) ((time % 3600000) / 60000);//得出的时间间隔的单位是分钟  
            interval = m + "分钟前";

        } else if (time / 3600000 < 24 && time / 3600000 >= 0) {
            //如果时间间隔小于24小时则显示多少小时前  
            int h = (int) (time / 3600000);//得出的时间间隔的单位是小时  
            interval = h + "小时前";

        } else {
            //大于24小时，则显示正常的时间，但是不显示秒  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            ParsePosition pos2 = new ParsePosition(0);
            Date d2 = (Date) sdf.parse(createtime.toString(), pos2);

            Calendar calendar = Calendar.getInstance(); //创建一个日历对象
            calendar.setTime(d2); //用当前时间初始化日历时间

            if (calendar.get(Calendar.YEAR) == 2015) {
                interval = calendar.get(Calendar.MONTH) + 1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日 " + calendar.get(Calendar.HOUR_OF_DAY)
                        + "时" + calendar.get(Calendar.MINUTE) + "分";
            } else {
                interval = calendar.get(Calendar.MONTH) + 1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日 " + calendar.get(Calendar.HOUR_OF_DAY)
                        + "时" + calendar.get(Calendar.MINUTE) + "分";
            }
        }
        return interval;
    }

    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static String dateTimeToYear(Date dateDate) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        return formatter.format(dateDate);
    }

    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static String dateTimeToYearMMddhhmmss(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        return formatter.format(dateTime);
    }
    public static String dateTimeToStringYearMMdd(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyMMdd");

        return formatter.format(dateTime);
    }

    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static Date dateTimeToDate(Date dateDate) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date reDateDate = null;
        try {
            reDateDate = formatter.parse(formatter.format(dateDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDateDate;
    }


    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static Date strTimeToDate(String dateDate) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date reDateDate = null;
        try {
            reDateDate = formatter.parse(dateDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDateDate;
    }


    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd HH
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static Date dateTimeToDateTimeHours(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
        Date reDateTime = null;
        try {
            reDateTime = formatter.parse(formatter.format(dateTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDateTime;
    }


    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd HH:mm
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static Date dateTimeToDateHourTime(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date reDateTime = null;
        try {
            reDateTime = formatter.parse(formatter.format(dateTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDateTime;
    }


    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static Date dateTimeToDateSecondTime(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reDateTime = null;
        try {
            reDateTime = formatter.parse(formatter.format(dateTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDateTime;
    }


    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd HH:MM
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static Date dateTimeToDateHHMM(String dateDate) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date reDateDate = null;
        try {
            reDateDate = formatter.parse(dateDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDateDate;
    }


    /**
     * 将时间格式时间转换为短时间格式 yyyy-MM-dd HH:MM:SS
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static Date dateTimeToDateHHMMSS(String dateDate) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reDateDate = null;
        try {
            reDateDate = formatter.parse(dateDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return reDateDate;
    }


    /**
     * 将时间格式时间转换为短时间格式HH:mm:ss
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static String dateTimeToTimeStr(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        final String timeStr = formatter.format(dateTime);
        return timeStr;
    }


    /**
     * 将时间格式时间转换为短时间格式HH:mm
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static String dateTimeToTimeHHmmStr(Date dateTime) {

        String timeStr = "";
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            timeStr = formatter.format(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStr;
    }


    /**
     * 将时间格式时间转换为短时间格式yyyy-MM-dd HH:mm
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static String dateTimeToTimeYYYYHHmmStr(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final String timeStr = formatter.format(dateTime);
        return timeStr;
    }


    /**
     * 将时间格式时间转换为短时间格式 MM-dd HH:mm
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static String dateTimeToTimeMMddHHmmStr(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        final String timeStr = formatter.format(dateTime);
        return timeStr;
    }


    /**
     * 将时间格式时间转换为短时间格式HHmm
     *
     * @param dateTime 日期
     * @return 转换后日期
     */
    public static String dateTimeToHHmmStr(Date dateTime) {

        final SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        final String timeStr = formatter.format(dateTime);
        return timeStr;
    }


    /**
     * 将日期按照传入的格式进行格式化
     *
     * @param date   日期
     * @param format 格式化
     * @return 转换后日期
     */
    public static Date dateTimeToDateFormat(Date date, String format) {

        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date timeStr = null;
        try {
            timeStr = formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStr;
    }


    /**
     * 将日期按照传入的格式进行格式化
     *
     * @param dateTime 日期
     * @param format1  传入参数的时间类型
     * @param format2  格式化时间类型
     * @return 转换后日期
     */
    public static Date dateTimeStrToDateTime(String dateTime, String format1, String format2) {

        final SimpleDateFormat formatter = new SimpleDateFormat(format1);
        //final ParsePosition pos = new ParsePosition(0);
        Date dateTimeDate = null;
        try {
            dateTimeDate = formatter.parse(dateTime);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        dateTimeDate = dateTimeToDateFormat(dateTimeDate, format2);

        return dateTimeDate;
    }


    /**
     * 将字符串转换为时间类型
     *
     * @param dateTimeStr 日期和时间字符串
     * @param format      日期和时间格式
     * @return 转换后的日期和时间
     * @author tang_wenwei
     */
    public static Date dateTimeStrToDateTime(String dateTimeStr, String format) {

        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date dateTimeDate = null;
        try {
            dateTimeDate = formatter.parse(dateTimeStr);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return dateTimeDate;
    }


    /**
     * 将时间格式转换为字符串 yyyy-MM-dd
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static String dateTimeToStr(Date dateDate) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final String dateString = formatter.format(dateDate);

        return dateString;
    }


    /**
     * 将时间格式转换为字符串MM-dd
     *
     * @param dateDate 日期
     * @return 转换后日期
     */
    public static String dateTimeToStrOfMMDD(Date dateDate) {

        String dateString = "";
        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
            dateString = formatter.format(dateDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateString;
    }


    /**
     * 将时间格式转换为字符串 yyyy-MM-dd
     *
     * @param dateDate 日期
     * @param format   格式化类型
     * @return 转换后日期
     */
    public static String dateTimeToStr(Date dateDate, String format) {

        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        final String dateString = formatter.format(dateDate);

        return dateString;
    }


    /**
     * <p>将日期和时间相加返回字符型。</p>
     *
     * @param dateDate 日期
     * @param time     时间
     * @return 转换后时间
     */
    public static String dateAddTimeToStr(Date dateDate, String time) {

        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final String dateTimeStr = formatter.format(dateDate) + " " + time;

        return dateTimeStr;
    }


    /**
     * <p>将日期和时间相加返回日期型。</p>
     *
     * @param dateDate 日期
     * @param time     时间
     * @return 转换后时间
     */
    public static Date dateAddTimeToDateTimeHHMMSS(Date dateDate, String time) {

        Date dateTime = null;

        try {
            final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            if (time.split(":").length == 2) {
                time = time + ":00";
            }
            final String dateTimeStr = dateFormatter.format(dateDate) + " " + time;

            final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateTime = dateTimeFormatter.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTime;
    }


    /**
     * <p>将日期和时间相加返回日期型。</p>
     *
     * @param dateDate 日期
     * @param time     时间
     * @return 转换后时间
     */
    public static Date dateAddTimeToDateTime(Date dateDate, String time) {

        Date dateTime = null;

        try {
            final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            final String dateTimeStr = dateFormatter.format(dateDate) + " " + time;

            final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            dateTime = dateTimeFormatter.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTime;
    }


    /**
     * <p>时间加上固定秒钟数。</p>
     *
     * @param dateTime 时间
     * @param seconds  秒钟
     * @return 相加后的结果
     */
    public static Date dateTimeAddSeconds(Date dateTime, int seconds) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.SECOND, seconds);
        //long Time = (dateTime.getTime() / 1000) + 60 * minutes;
        //dateTime.setTime(Time * 1000);
        final Date reDateTime = calendar.getTime();

        return dateTimeToDateSecondTime(reDateTime);
    }


    /**
     * <p>时间减去固定秒钟数。</p>
     *
     * @param dateTime 时间
     * @param seconds  秒钟
     * @return 相减后的结果
     */
    public static Date dateTimeSubtractSeconds(Date dateTime, int seconds) {

        final int secondsTemp = -seconds;
        final Date reDateTime = dateTimeAddSeconds(dateTime, secondsTemp);
        //long Time = (dateTime.getTime() / 1000) - 60 * minutes;
        //dateTime.setTime(Time * 1000);
        return reDateTime;
    }


    /**
     * <p>时间减去固定分钟数。</p>
     *
     * @param dateTime 时间
     * @param minutes  分钟
     * @return 相减后的结果
     */
    public static Date dateTimeSubtractMinutes(Date dateTime, int minutes) {

        final int minutesTemp = -minutes;
        final Date reDateTime = dateTimeAddMinutes(dateTime, minutesTemp);
        //long Time = (dateTime.getTime() / 1000) - 60 * minutes;
        //dateTime.setTime(Time * 1000);
        return reDateTime;
    }


    /**
     * <p>时间加上固定分钟数。</p>
     *
     * @param dateTime 时间
     * @param minutes  分钟
     * @return 相加后的结果
     */
    public static Date dateTimeAddMinutes(Date dateTime, int minutes) {

        if (dateTime == null) {
            return null;
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.MINUTE, minutes);
        //long Time = (dateTime.getTime() / 1000) + 60 * minutes;
        //dateTime.setTime(Time * 1000);
        final Date reDateTime = calendar.getTime();

        return dateTimeToDateSecondTime(reDateTime);
    }


    /**
     * <p>时间加上固定分钟数(计算制作时间和准备时间)。</p>
     *
     * @param dateTime 时间
     * @param minutes  分钟
     * @return 相加后的结果
     */
    public static Date dateTimeAddMinutes(Date dateTime, BigDecimal minutes) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.MINUTE, minutes.setScale(0, BigDecimal.ROUND_UP).intValue());
        //long Time = (dateTime.getTime() / 1000) + 60 * minutes;
        //dateTime.setTime(Time * 1000);
        final Date reDateTime = calendar.getTime();

        return dateTimeToDateSecondTime(reDateTime);
    }


    /**
     * <p>时间减去固定小时数。</p>
     *
     * @param dateTime 时间
     * @param hours    小时
     * @return 相减后的结果
     */
    public static Date dateTimeSubtractHours(Date dateTime, int hours) {

        final int hoursTemp = -hours;
        final Date reDateTime = dateTimeAddHours(dateTime, hoursTemp);
        return reDateTime;
    }


    /**
     * <p>时间加上固定小时数。</p>
     *
     * @param dateTime 时间
     * @param hours    小时
     * @return 相加后的结果
     */
    public static Date dateTimeAddHours(Date dateTime, int hours) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        //long Time = (dateTime.getTime() / 1000) + 60 * minutes;
        //dateTime.setTime(Time * 1000);
        final Date reDateTime = calendar.getTime();
        return reDateTime;
    }


    /**
     * <p>将时间往后推N天。</p>
     *
     * @param dateTime 时间
     * @param dayNum   往后推的天数
     * @return 计算结果
     */
    public static Date dateTimeAddDay(Date dateTime, int dayNum) {

        if (dateTime == null) {
            return null;
        }

        final Calendar c = new GregorianCalendar();
        c.setTime(dateTime);

        //Modify By LiuHui Start
        //      c.add(c.get(Calendar.MINUTE), dayNum); //把日期往后增加N天.整数往后推
        c.add(Calendar.DATE, dayNum); //把日期往后增加N天.整数往后推
        //Modify By LiuHui End

        final Date addAfterDateTime = c.getTime();
        return addAfterDateTime;
    }


    /**
     * <p>将时间往后推N月。</p>
     *
     * @param dateTime 时间
     * @param dayNum   往后推的月数
     * @return 计算结果
     */
    public static Date dateTimeAddMonth(Date dateTime, int monthNum) {

        if (dateTime == null) {
            return null;
        }

        final Calendar c = new GregorianCalendar();
        c.setTime(dateTime);
        c.add(Calendar.MONTH, monthNum); //把日期往后增加N月.整数往后推
        final Date addAfterDateTime = c.getTime();
        return addAfterDateTime;
    }


    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday(Date birthday) {

        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth 
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth 
                age--;
            }
        }
        return age;
    }


    public static long getIntervalSeconds(Date beginDate, Date endDate) {

        long interval = (beginDate.getTime() - endDate.getTime()) / 1000;

        return interval;

    }


    public static int getIntervalMinutes(Date beginDate, Date endDate) {

        long interval = (beginDate.getTime() - endDate.getTime()) / (1000 * 60);

        return (int) interval;

    }


    public static int getIntervalHours(Date beginDate, Date endDate) {

        long interval = (beginDate.getTime() - endDate.getTime()) / (1000 * 60 * 60);

        return (int) interval;

    }


    public static int getIntervalDays(Date beginDate, Date endDate) {

        long interval = (beginDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24);

        return (int) interval;

    }
    //判断时间和之前是否超过7天
    public static boolean isLatestWeek(Date addtime){
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -15);  //设置为7天前
        Date before7days = calendar.getTime();     //得到7天前的时间
        if(before7days.getTime() > addtime.getTime()){
            return true;
        }else{
            return false;
        }
    }


}
