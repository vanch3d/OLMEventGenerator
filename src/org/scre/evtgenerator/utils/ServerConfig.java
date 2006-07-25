package org.scre.evtgenerator.utils;


/**
 * A very simple singleton class for sotring/manipulating the EventGenerator configuration.
 * Give access to the XML-RPC server URL. 
 * @author vanlabeke
 * @version $Revision: 1.2 $
 */
public class ServerConfig {

    private static ServerConfig _instance = new ServerConfig();

    /**
     * 
     */
    public static String server = "130.209.168.54",
                         serverPort = "8080",
                         serverUri = "/ActiveMath2/xmlrpc/events",
                         serverHandler = "publishEvent";
    
    public static synchronized ServerConfig getInstance()
    {
        return _instance;
    }
    
    public static String getURL()
    {
        return "http://" + ServerConfig.server + ":"  + 
                           ServerConfig.serverPort + "/" + 
                           ServerConfig.serverUri;
    }    
    
}
