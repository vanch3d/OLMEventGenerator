package org.scre.evtgenerator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import org.apache.xmlrpc.applet.SimpleXmlRpcClient;
import org.apache.xmlrpc.applet.XmlRpcException;
import org.scre.evtgenerator.utils.ServerConfig;

public class Population 
{

    private SimpleXmlRpcClient client=null;


    
    /**
     * 
     */
    public Population() 
    {
        super();
        //initLeAM();
    }

    public ArrayList logisticGrowth()
    {
        double K = 10;
        double r = 0.4;
        double N0 = 0.1;
        int N = 30;
        ArrayList data = new ArrayList();
        
        Random rand = new Random();
        
        
        for (int i=0;i<N;i++)
        {
            double b = K / (1+ (K/N0-1)*Math.exp(-r*i)) ;
            data.add(new Double(b/K));
        }
        
//        int n = rand.nextInt(3);
//        for (int i=0;i<n;i++)
//        {
//            int j = rand.nextInt(N-2);
//            Double f= (Double)data.get(j+1);
//            
//            data.set(j+1,new Double(1-f.doubleValue()));
//        }  
        return data;
    }
    
//    public void initLeAM()
//    {
//        System.setProperty("mbaseRef.class","org.activemath.omdocjdom.mbase.OJXmlRpcMBaseRef");
//        System.setProperty("mbaseRef.detach","true");
//        System.setProperty("mbaseRef.url","http://127.0.0.1:27000/");
//
//        // @todo Call these method just to initialise the singleton now
//        MBaseRef mbaseRef= Manager.getOfficialMBase();
//        ContentManager contentManager = ContentManager.getInstance();
//        XlmModelImpl    impl = XlmModelImpl.getInstance();
//        
//        Properties prop= new Properties();
//        prop.setProperty("log4j.appender.SysOut","org.apache.log4j.ConsoleAppender");
//        prop.setProperty("log4j.appender.SysOut.layout","org.apache.log4j.PatternLayout");
//        prop.setProperty("log4j.rootLogger","ERROR,SysOut");
//        prop.setProperty("log4j.logger.org.activemath.omdocjdom.lucene","ERROR");
//        prop.setProperty("log4j.logger.org.activemath.presentation","ERROR");
//        prop.setProperty("log4j.logger.org.activemath.webapp","ERROR");
//        PropertyConfigurator.configure(prop);
//    }
    
    public void sendEvents(ArrayList data)
    {
        if (client==null)
        {
            try {
                client = new SimpleXmlRpcClient("http://" + ServerConfig.server + ":"  + ServerConfig.serverPort + "/" + ServerConfig.serverUri);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                System.out.println("URL malformed : " + "http://" + ServerConfig.server + ":"  + ServerConfig.serverPort + "/" + ServerConfig.serverUri);
                e.printStackTrace();
                return;
            }
        }
        System.out.println("connecting to : " + "http://" + ServerConfig.server + ":"  + ServerConfig.serverPort + "/" + ServerConfig.serverUri);
     
        Hashtable event = new Hashtable();
        event.put("type","ExerciseFinished");
        event.put("Timestamp","ExerciseFinished");
        event.put("userId","toto");
        //event.put("itemId","mbase://LeAM_calculus/diffquot/scq1_diff_quot");
        //event.put("itemId","mbase://LeAM_calculus/deriv/fib_model2_diff_hiking");
        
        
        //event.put("itemId","mbase://LeAM_calculus/exercisesDerivs/open_const_lin_derivs"); // E,SC,ARGUE,DIFF QUOT (NORMAL)
        //event.put("itemId","mbase://LeAM_calculus/exercisesDerivs/mcq_derivchoice"); // E,SC,THINK,DIFF QUOT (NOISE)
        //event.put("itemId","mbase://LeAM_calculus/deriv_maps/fib_diff_const"); // E,SC,SOLVE,DERIVATIVE (REVERSE)

        Vector params = new Vector();
        params.addElement(event);

        
        try {
            for (int i=0;i<data.size();i++)
            {
                Double dd = (Double)data.get(i);
                event.put("itemId","mbase://LeAM_calculus/exercisesDerivs/open_const_lin_derivs"); // E,SC,ARGUE,DIFF QUOT (NORMAL)
                event.put("successRate",new Double(dd.doubleValue()));
                client.execute(ServerConfig.serverHandler, params);
                //event.put("itemId","mbase://LeAM_calculus/exercisesDerivs/mcq_derivchoice"); // E,SC,THINK,DIFF QUOT (NOISE)
                //event.put("successRate",new Double(0.1));
                //client.execute(ServerConfig.serverHandler, params);
            }
        } catch (XmlRpcException e) {
            // TODO Auto-generated catch block
            System.err.println("Cannot execute XML-RPC command : " + e.getMessage());
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Cannot execute XML-RPC command : " + e.getMessage());
            //e.printStackTrace();
        }


        
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        Population pop = new Population();
        
        ArrayList aa = pop.logisticGrowth();

        for (int i=0;i<aa.size();i++)
        {
            System.out.println(i + "\t" + aa.get(i));
        }
        pop.sendEvents(aa);


    }

}
