package com.Grizzly.appium;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;
import java.net.URL;

import com.Grizzly.constants.Constants;
import com.Grizzly.utils.Configuration;
import com.Grizzly.utils.TestUtils;

@SuppressWarnings("unused")
public class DriverManagerAppium {
    @SuppressWarnings("rawtypes")
	private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    TestUtils utils = new TestUtils();

    @SuppressWarnings("rawtypes")
	public AppiumDriver getDriver(){
        return driver.get();
    }

    
    public void setDriver(@SuppressWarnings("rawtypes") AppiumDriver driver2){
        driver.set(driver2);
    }

    
    @SuppressWarnings({ "rawtypes", "static-access" })
	public void initializeDriver() throws Exception {
        AppiumDriver driver = null;
        GlobalParamsAppium params = new GlobalParamsAppium();
        URL url; 
        
       /* if(Constants.isServer) {
        	url = new ServerManager().getServer().getUrl();
        }else {*/
        	url = new URL(Constants.appiumServerURL);
      /*  }
*/        
        if(driver == null){
            try{
              utils.log().info("initializing Appium driver");
                switch(params.getPlatformName()){
                    case "Android":
                        driver = new AndroidDriver(url, new CapabilitiesManagerAppium().getCaps());
                        break;
                    case "iOS":
                        driver = new IOSDriver(url, new CapabilitiesManagerAppium().getCaps());
                        break;
                }
                if(driver == null){
                    throw new Exception("driver is null. ABORT!!!");
                }
                utils.log().info("Driver is initialized");
                this.driver.set(driver);
            } catch (IOException e) {
                e.printStackTrace();
                utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
                throw e;
            }
        }

    }

}
