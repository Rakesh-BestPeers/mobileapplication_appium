package com.Grizzly.constants;

public interface FrameworkConstants {

    public String platform = "Windows";

    /*Set default browser (default = CHROME32_NOHEAD)
      CHROME32, CHROME32_NOHEAD, FF32, FF32_NOHEAD, FF64, FF64_NOHEAD,
      EDGE, IE32, IE64*/
    public String browser = "CHROME32";

    /*Enable chromedriver versioning (default = false)
      true - download specified chromedriver version
      false - download latest chromedriver version*/
    public boolean chromedriverVersionOn = false;

    /*Set chromedriver version (does nothing when chromedriver.version.on = false)*/
    public String chromedriverVersion = "74.0.3729.6";

    /*Wait for Angular MVC processes to finish (default = false)
      true - may improve reliability of tests for Angular applications
      false - for non-Angular applications*/
    public boolean waitForAngular = false;

    /*Wait for page load to complete before throwing an error (default = false)
      true - use only when explicit waits are failing
      false - prioritize explicit wait for page and element conditions*/
    public boolean waitForPageLoad = false;

    /*Set timeout for page load in seconds (default = 30)*/
    public long pageloadTimeout = 50;

    /*Set timeout for explicit wait in seconds (default = 30)*/
    public long explicitTimeout = 30;

    /*Turn screenshots on/off (default = false)
    true - disable screenshot capturing (overrides screenshot.on.fail)
    false - enable screenshot capturing*/
    public boolean screenshotOff = false;

    /*Set screenshot options (default = false)
    true - capture screenshot on failed scenario after last step
    false - capture screenshot after each step*/
    public boolean screenshotOnFail = false;

    /*Set wait time between keystrokes in seconds (default = 1)*/
    public int keyPressWait = 1;

}
