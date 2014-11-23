package betmo.com.betmo.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kdimla on 11/22/14.
 */
public class DateUtility {
    public static final String TIMESTAMP_DISPLAYABLE_DATE_FORMAT = "MM/dd/yyyy";
    public static final String SERVER_DATE_FORMAT="yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static String getDisplayableDate(String serverdate){
        String displayable = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(SERVER_DATE_FORMAT);
            Date date = format.parse(serverdate);
            SimpleDateFormat displayableFormat = new SimpleDateFormat(TIMESTAMP_DISPLAYABLE_DATE_FORMAT);
            displayable = displayableFormat.format(date);
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        return displayable;
    }
}
