package ohtu;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/eekuurne/chromedriver");
        
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(1);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(1);
        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("peksu");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();
        
        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("banjo");
        element = driver.findElement(By.name("password"));
        element.sendKeys("soitin");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();
        
        sleep(1);
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        
        sleep(1);
        element = driver.findElement(By.linkText("register new user"));
        element.click();
        
        sleep(1);
        testRegister(driver);
        
        sleep(1);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        sleep(3);
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static void testRegister(WebDriver driver) {
        WebElement element;
        while(true) {
            element = driver.findElement(By.name("username"));
            element.sendKeys(UUID.randomUUID().toString().substring(0, 10));
            element = driver.findElement(By.name("password"));
            element.sendKeys("yetu");
            element = driver.findElement(By.name("passwordConfirmation"));
            element.sendKeys("yetu");
            element = driver.findElement(By.name("signup"));
            element.submit();
            sleep(1);
            if (driver.getCurrentUrl().contains("welcome")) {
                break;
            }
        }
    }
}
