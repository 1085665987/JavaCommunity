package com.firday.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DisplayDateUtil {

    public static String timeDifference(long paramTime){
	Calendar calendar = Calendar.getInstance();

        Date paramDate=new Date(paramTime);
	calendar.setTime(paramDate);
	int paramYear = calendar.get(Calendar.YEAR);		//��ȡ���
	int paramMonth = calendar.get(Calendar.MONTH)+1;	//��ȡ�·�
	int paramDay = calendar.get(Calendar.DATE);		//��ȡ��
	int hour = calendar.get(Calendar.HOUR_OF_DAY);	//ʱ��24Сʱ�ƣ�
	int minute = calendar.get(Calendar.MINUTE);		//��
	//int second = calendar.get(Calendar.SECOND);		//��

//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate));
        Date nowDate=new Date();
        long nowLong=nowDate.getTime();

        int minutes=(int)((nowLong-paramTime)/(1000*60));
        if (minutes<60) {
            return minutes+"����";
        }

        int hours=(int)((nowLong-paramTime)/(1000 * 60 * 60));
        if (hours<24){
            return hours+"Сʱ";
        }else if(hours>24&&hours<48){
            //���� HH:mm
            return "����"+hour+":"+minute;
        }

       calendar.setTime(nowDate);
       int nowYear = calendar.get(Calendar.YEAR);		//��ȡ���
       //int nowMonth = calendar.get(Calendar.MONTH);	//��ȡ�·�
       //int nowDay = calendar.get(Calendar.DATE);		//��ȡ��

        if (paramYear==nowYear){
            //MM-dd HH:mm
            return paramMonth+"-"+paramDay+" "+hour+":"+minute;
        }

        return paramYear+"-"+paramMonth+"-"+paramDay+" "+hour+":"+minute;
    }

    /**.
     * ���ظ�ʽΪ�����ո�ʽ���ַ���
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
     * ��ȡδ�� �� past �������
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
        /*������:Calendar.SUNDAY=1
         *����һ:Calendar.MONDAY=2
         *���ڶ�:Calendar.TUESDAY=3
         *������:Calendar.WEDNESDAY=4
         *������:Calendar.THURSDAY=5
         *������:Calendar.FRIDAY=6
         *������:Calendar.SATURDAY=7 */
        switch (week) {
            case 1:
                weekStr = "����";
                break;
            case 2:
                weekStr = "��һ";
                break;
            case 3:
                weekStr = "�ܶ�";
                break;
            case 4:
                weekStr = "����";
                break;
            case 5:
                weekStr = "����";
                break;
            case 6:
                weekStr = "����";
                break;
            case 7:
                weekStr = "����";
                break;
            default:
                break;
        }
    }
}
