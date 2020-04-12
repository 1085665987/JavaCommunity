package com.first.util;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DisplayDateUtil {

    public static String timeDifference(long paramTime){
	Calendar calendar = Calendar.getInstance();

        Date paramDate=new Date(paramTime);
	calendar.setTime(paramDate);
	int paramYear = calendar.get(Calendar.YEAR);		//获取年份
	int paramMonth = calendar.get(Calendar.MONTH)+1;	//获取月份
	int paramDay = calendar.get(Calendar.DATE);		//获取日
	int hour = calendar.get(Calendar.HOUR_OF_DAY);	//时（24小时制）
	int minute = calendar.get(Calendar.MINUTE);		//分
	//int second = calendar.get(Calendar.SECOND);		//秒

//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate));
        Date nowDate=new Date();
        long nowLong=nowDate.getTime();

        int minutes=(int)((nowLong-paramTime)/(1000*60));
        if (minutes<60) {
            return minutes+"分钟";
        }

        int hours=(int)((nowLong-paramTime)/(1000 * 60 * 60));
        if (hours<24){
            return hours+"小时";
        }else if(hours>24&&hours<48){
            //昨天 HH:mm
            return "昨天"+hour+":"+minute;
        }
       
       calendar.setTime(nowDate);
       int nowYear = calendar.get(Calendar.YEAR);		//获取年份
       //int nowMonth = calendar.get(Calendar.MONTH);	//获取月份
       //int nowDay = calendar.get(Calendar.DATE);		//获取日

        if (paramYear==nowYear){
            //MM-dd HH:mm
            return paramMonth+"-"+paramDay+" "+hour+":"+minute;
        }

        return paramYear+"-"+paramMonth+"-"+paramDay+" "+hour+":"+minute;
    }

    /**.
     * 返回格式为年月日格式的字符串
     * @param paramTime
     * @return
     */
    public static String longTimeToDateFormatYMD(long paramTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date paramDate = new Date(paramTime);
        String format = simpleDateFormat.format(paramDate);
	return format;
    }
    public static String longTimeToDateFormatYMDMd(long paramTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date paramDate = new Date(paramTime);
        String format = simpleDateFormat.format(paramDate);
        return format;
    }
    public static String longTimeToDate(long paramTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date paramDate = new Date(paramTime);
        String format = simpleDateFormat.format(paramDate);
	return format;
    }
    
    /** 
     * 获取未来 第 past 天的日期 
     * @param past 
     * @return 
     */  
    public static Calendar getFetureDate(int past) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
        return calendar;  
    } 
//    public static String getFetureDate(int past) {  
//        Calendar calendar = Calendar.getInstance();  
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
//        Date today = calendar.getTime();  
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
//        String result = format.format(today); 
//        return result;  
//    } 
    
    public static void main(String [] args){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
//        String result = format.format(today); 
	Calendar calendar = getFetureDate(1);
	System.out.println(calendar.get(calendar.YEAR));
	System.out.println(calendar.get(calendar.MONTH)+1);
	System.out.println(calendar.get(calendar.DATE));
	int week = calendar.get(Calendar.DAY_OF_WEEK);
        String weekStr = "";
        /*星期日:Calendar.SUNDAY=1
         *星期一:Calendar.MONDAY=2
         *星期二:Calendar.TUESDAY=3
         *星期三:Calendar.WEDNESDAY=4
         *星期四:Calendar.THURSDAY=5
         *星期五:Calendar.FRIDAY=6
         *星期六:Calendar.SATURDAY=7 */
        switch (week) {
            case 1:
                weekStr = "周日";
                break;
            case 2:
                weekStr = "周一";
                break;
            case 3:
                weekStr = "周二";
                break;
            case 4:
                weekStr = "周三";
                break;
            case 5:
                weekStr = "周四";
                break;
            case 6:
                weekStr = "周五";
                break;
            case 7:
                weekStr = "周六";
                break;
            default:
                break;
        }
    }
}
