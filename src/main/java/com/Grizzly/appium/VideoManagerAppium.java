package com.Grizzly.appium;

import io.appium.java_client.screenrecording.CanRecordScreen;

import org.apache.commons.codec.binary.Base64;

import com.Grizzly.constants.PathConstants;
import com.Grizzly.utils.TestUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class VideoManagerAppium {
    TestUtils utils = new TestUtils();

    public void startRecording(){
        ((CanRecordScreen) new DriverManagerAppium().getDriver()).startRecordingScreen();
    }

    public void stopRecording(String scenarioName) throws IOException {
        GlobalParamsAppium params = new GlobalParamsAppium();
        String media = ((CanRecordScreen) new DriverManagerAppium().getDriver()).stopRecordingScreen();
        String dirPath = PathConstants.video + File.separator + params.getPlatformName() + "_"
                + params.getDeviceName() + File.separator +"Videos";

        File videoDir = new File(dirPath);

        synchronized(videoDir){
            if(!videoDir.exists()) {
                videoDir.mkdirs();
            }
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(videoDir + File.separator + scenarioName + ".mp4");
            stream.write(Base64.decodeBase64(media));
            stream.close();
            utils.log().info("video path: " + videoDir + File.separator + scenarioName + ".mp4");
        } catch (Exception e) {
            utils.log().error("error during video capture" + e.toString());
        } finally {
            if(stream != null) {
                stream.close();
            }
        }
    }
}
