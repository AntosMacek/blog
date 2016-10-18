import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

/**
 * Created by antoha on 10/17/16.
 */
public class Global extends GlobalSettings {

    public void onStart(Application app) {
        if (User.find.findRowCount() == 0) {
            Ebean.save((List) Yaml.load("initial-data.yml"));
        }
    }

}
