package hu.tigra.jee.data;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import java.util.Calendar;
import java.util.regex.Pattern;

@FacesConverter(value = "myDateTimeConverter")
public class MyDateTimeConverter extends DateTimeConverter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object result = null;
        try {
            result = super.getAsObject(context, component, value);
        } catch (Exception e) {
            Pattern hourMin = Pattern.compile("(\\d\\d?:\\d\\d?)");
            if (hourMin.matcher(value).matches()) {
                int h = Integer.valueOf(value.split(":")[0]);
                int m = Integer.valueOf(value.split(":")[1]);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, h);
                calendar.set(Calendar.MINUTE, m);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                return calendar.getTime();
            }
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return super.getAsString(context, component, value);
    }
}
