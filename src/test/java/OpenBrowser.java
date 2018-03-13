
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class OpenBrowser {
    private String sitename,username,password,panelname;

    @Before
    public void loadProperties() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);
            // get the property value and print it out
            sitename=prop.getProperty("sitename");
            username=prop.getProperty("username");
            password=prop.getProperty("password");
            panelname=prop.getProperty("panelname");
            System.out.println();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void openChrome() {
        WebDriver chrome = new ChromeDriver();
        chrome.get(sitename);
        WebElement element = chrome.findElement(By.id("username"));
        element.sendKeys(username);
        element = chrome.findElement(By.id("password"));
        element.sendKeys(password);
        element = chrome.findElement(By.id("login-submit"));
        element.click();
        assertEquals(panelname,chrome.getTitle());
        chrome.quit();
    }


    @Test
    public void openFirefox() {
        WebDriver firefox = new FirefoxDriver();
        firefox.get(sitename);
        WebElement element =  firefox.findElement(By.id("username"));
        element.sendKeys(username);
        element = firefox.findElement(By.id("password"));
        element.sendKeys(password);
        element = firefox.findElement(By.id("login-submit"));
        element.click();
        assertEquals(panelname,firefox.getTitle());
        firefox.quit();
    }

}
