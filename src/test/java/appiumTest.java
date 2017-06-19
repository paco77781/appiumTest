import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;




/**
 * Created by appium on 13/3/17.
 */
public class appiumTest {

    //private RemoteWebDriver driver;

    //  public AppiumDriver<MobileElement> driver;

    private AppiumDriver<WebElement> driver;

    private List<Integer> values;

    public WebDriverWait wait ;
    @Before
    public void setup() throws MalformedURLException{
        DesiredCapabilities desiredCapabilities;

        desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability("deviceName","IPad Air2");
        //desiredCapabilities.setCapability("deviceName","iPhone 5s");
        desiredCapabilities.setCapability("udid","07436359a6f86dce57b77a0b941b92d6975e3480");
        //desiredCapabilities.setCapability("udid","6fadb40a2c706858513f78280f686692d9f1b625");

        //desiredCapabilities.setCapability("udid","2E611BE3-7495-4BFE-A288-EB24BFC828CC");

        desiredCapabilities.setCapability("platformVersion","10.2.1");
        //desiredCapabilities.setCapability("appiumVersion","v1.6.0");
        desiredCapabilities.setCapability("platformName","iOS");
        //desiredCapabilities.setCapability("orientation","LANDSCAPE");
        desiredCapabilities.setCapability("automationName","XCUITest");
        desiredCapabilities.setCapability("xcodeOrgId","DXKN9UUK5Q");
        desiredCapabilities.setCapability("xcodeSigningId","iPhone Developer");
        //desiredCapabilities.setCapability("automationName","UIautomation");
        //requiredcapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.iOS);
        //requiredcapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Ipad Air2 Device");
        desiredCapabilities.setCapability("app","/Users/appium/Documents/PruebaAppium.ipa");
        desiredCapabilities.setCapability("realDeviceLogger","/usr/local/lib/node_modules/deviceconsole/deviceconsole");

        //driver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"),desiredCapabilities);
        driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);

        //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities){};
        wait=new WebDriverWait(driver,60);
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }

    @Test
    public void selectLoginWithEmail() {
        String correo= "";
        String password="";
        String asercion="";
        int vueltas=-1;
        //for (vueltas= 0; vueltas <4; vueltas++) {
        do{
            vueltas ++ ;
            if(vueltas==0) {
                correo= "micorreo@ibermatica.com";
                password= "mipassword";
                asercion="Login OK";
            }
            else if(vueltas==1)
            {   correo= "tiuyhgyiu";
                password= "mipassword";
                asercion="Login KO";
            }
            else if(vueltas==2)
            {   correo= " ";
                password= "mipassword";
                asercion="Login KO";
            }
            else if(vueltas==3)
            {   correo= " ";
                password= " ";
                asercion="Login KO";
            }
            List<WebElement> elems = driver.findElements(By.className("UIATextField"));
            int contador = 0;
            for (WebElement elem : elems) {
                contador++;
                if (contador == 1) {
                    elem.clear();
                    elem.sendKeys(correo);
                } else {
                    elem.clear();
                    elem.sendKeys(password);
                }
            }

            //WebElement cajaCorreo = driver.findElement(By.name("Email"));
            WebElement cajaCorreo = driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]"));
            //cajaCorreo.sendKeys(password);

            WebElement button = driver.findElement(By.className("UIAButton"));
            button.click();


            WebElement texto = driver.findElement(By.className("UIAAlert"));
            //WebElement texto =driver.findElement(By.name("Lo2gin KO"));
            //WebElement texto = driver.findElement(By.className("XCUIElementTypeAlert"));
            String texto2 = texto.getAttribute("label");
            System.out.print(texto2);
            String codigo = driver.getPageSource();
            System.out.println("codigo es " + codigo);

            Assert.assertTrue("Correcto", texto2.equalsIgnoreCase(asercion));
            System.out.println("vueltas es "+vueltas);


            //try {
            //    Thread.sleep(5000);
           // } catch (InterruptedException e) {
           // }

            WebElement boton2 =driver.findElement(By.name("Aceptar"));
            boton2.click();

        } while (vueltas < 4);
    }

}