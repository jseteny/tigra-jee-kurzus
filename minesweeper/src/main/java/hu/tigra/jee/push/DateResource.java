package hu.tigra.jee.push;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@PushEndpoint("/date")
public class DateResource {

    @OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
        return dateFormat.format(date);
    }
/*    public Date onMessage(Date date) {
        return date;
    }
*/
}
