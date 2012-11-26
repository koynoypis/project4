
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;
import java.text.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Web application lifecycle listener.
 *
 * @author koynoypis
 */
public class SessionCounter implements HttpSessionListener {

    
    Date now = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("E");
    
    
    private static int activeSessions = 0 ;
    
    public static int daily_session1 = 0;
    public static int daily_session2 = 0;
    public static int daily_session3 = 0;
    public static int daily_session4 = 0;
    public static int daily_session5 = 0;
    public static int daily_session6 = 0;
    public static int daily_session7 = 0;
    
    public synchronized static int getSessionNum(){
        return activeSessions;
        
    }
    public synchronized static int getTotalSessionNum(int x){
    
        if(x==1){
            return daily_session1;
        }
        if(x==2){
            return daily_session2;
        }
        if(x==3){
            return daily_session3;
        }
        if(x==4){
            return daily_session4;
        }
        if(x==5){
            return daily_session5;
        }
        if(x==6){
            return daily_session6;
        }
        else{
            return daily_session7;
        }
    
    }
    
    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        Date new_date= new Date();
        if(!ft.format(new_date).equals(ft.format(now))){
            daily_session1=daily_session2;
            daily_session2=daily_session3;
            daily_session3=daily_session4;
            daily_session4=daily_session5;
            daily_session5=daily_session6;
            daily_session6=daily_session7;
            daily_session7=0;
            daily_session7++;
        }
        else{
            daily_session7++;
        }
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
        if(activeSessions>0){
            activeSessions--;
        }
        
    }
    
   
    
}
