package uiip.quartz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Start implements ServletContextListener{

    public void contextDestroyed(ServletContextEvent arg0)
    {
        System.out.println("Stopping Application successfully");
    }

    public void contextInitialized(ServletContextEvent arg0)
    {
       System.out.println("Initializing Application successfully");

       try{
         Scheluder objPlugin = new Scheluder();
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
     }
}
