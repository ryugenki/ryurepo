package mymule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CheckUtils {
	public static Date dateAdd(String dt, int intervalType, int increment) throws Exception{
		Date d1 = null;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
	        d1 = sdf.parse(dt); //"2020-12-12"
	        c.setTime(d1);
	        c.add(intervalType, increment);
	        d1 = c.getTime();
		}catch(Exception e) {
			throw e;
		}
		return d1;
	}
	
	public static Date dateAdd(Date dt, int intervalType, int increment) throws Exception{
		Date d1 = null;
        Calendar c = Calendar.getInstance();
		try {
	        c.setTime(dt);
	        c.add(intervalType, increment);
	        d1 = c.getTime();
		}catch(Exception e) {
			throw e;
		}
		return d1;
	}
	
	public static Date strToDate(String dt) throws Exception{
		Date d1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
	        d1 = sdf.parse(dt); //"2020-12-12"
		}catch(Exception e) {
			throw e;
		}
		return d1;
	}
}
