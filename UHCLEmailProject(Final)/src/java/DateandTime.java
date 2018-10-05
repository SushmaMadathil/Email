
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sonal
 */
public class DateandTime {
    public static final String Date_Format = "MMM dd yyyy";
    
    public static String DateandTime()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(Date_Format);
        return sdf.format(cal.getTime());
        
    }
}
