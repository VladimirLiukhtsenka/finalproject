import com.liukhtenko.ticket.locale.RecourseManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManagerTest {
    public static void main(String[] args) {
        RecourseManager recourseManager =RecourseManager.INSTANCE;
        System.out.println(  recourseManager.get("message.title"));
//        Locale locale = new Locale("en","US");
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("locale.locale",locale);
//        System.out.println( resourceBundle.getString("message.title"));
    }
}
