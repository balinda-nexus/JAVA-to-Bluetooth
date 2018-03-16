package jav.bt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

public class JavBt {

//    StringBuffer value = new StringBuffer();
    String value="";
    boolean scanFinished = false;
    String hc05Url
            = "btspp://98D332309FFB:1;authenticate=false;encrypt=false;master=false"; //Replace this with your bluetooth URL
    /////   ="btspp://bluetooth address without colons:1;authenticate=false;encrypt=false;master=false"

    public static void main(String[] args) {

        try {
            new JavBt().reading_from_bluetooth(); //Calling the read data from bluetooth method
        } catch (Exception ex) {
            Logger.getLogger(JavBt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reading_from_bluetooth() {
        try {
            StreamConnection streamConnection = (StreamConnection) Connector.open(hc05Url);
            System.out.println("connected");
//        OutputStream os = streamConnection.openOutputStream();
            InputStream is = streamConnection.openInputStream();
//        os.write("1".getBytes()); //'1' means ON and '0' means OFF
//        os.close();
            
            char[] b = new char[200];   //Creating a 16-bit char array to hold string from arduino

            //Making the bluetooth connection live, this is optional
            while (true) {
                for (int y = 0; y < is.available(); y++) {
                    int i = is.read();
                    value += (char)i;
//                    System.out.println("Qualify");
                    if(value.length() == 7){
                        cool(value.replace("\\s" ,""));
                        try {Thread.sleep(500);} catch(InterruptedException e){}// Thread.sleep(200);
                    } else if(value.length() > 7) {
                        StringBuffer val = new StringBuffer();
                        val.delete(0, val.length());
                        value = val.toString();
                    }
                }

            }
             
        } catch (Exception e) {
        }
    }

    public void cool (String h){
        
        System.out.println(h.substring(0,2));
        System.err.println(h.substring(3,7));
        try {Thread.sleep(500);} catch(InterruptedException e){}
        /////logic
               
    }
    
}

