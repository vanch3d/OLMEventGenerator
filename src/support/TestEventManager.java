/**
 * Created on 12-Aug-2005
 * @author FTYK9
 * @version $Revision: 1.1 $
 */
package support;

import java.util.Hashtable;

import org.activemath.events.ActivemathEvent;
import org.activemath.events.impl.EventFactory;

/**
 * @author FTYK9
 *
 */
public class TestEventManager {

    public boolean publishEvent(Hashtable props)
    {
        ActivemathEvent event = EventFactory.createEvent(props);
        if (event==null)
        {
            System.err.println("Cannot create the event!");
            return false;
        }
        
        System.out.println(" --> event : " + event.toString());
        return true;
    }

}
