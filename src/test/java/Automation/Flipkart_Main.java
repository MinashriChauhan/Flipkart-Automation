package Automation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart_Main {

	public static void main(String[] args) throws InterruptedException {

		ArrayList<String> productLink = new ArrayList<String>();
		ArrayList<String> productName = new ArrayList<String>();
		ArrayList<String> productPrice = new ArrayList<String>();
		
		// System Property for Chrome Driver   
		//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//WebDriver driver=new ChromeDriver();
		WebDriver driver;
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup(); // It instantiates the Selenium WebDriver instance with the ChromeDriver.
		driver = new ChromeDriver();
		// Launch Website  
		driver.navigate().to("https://www.flipkart.com/");  
		  
		//Maximize the browser  
		driver.manage().window().maximize();
		
		// Close the login pop up
		driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/button[1]")).click();
		
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys("Samsung Galaxy S10");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Mobiles']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='Price']/ancestor::section/following-sibling::section[1]/label/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='SAMSUNG']/ancestor::label/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(),'Price -- High to Low')]")).click();
		Thread.sleep(2000);
		
		List<WebElement> rows = driver.findElements(By.xpath("//span[contains(text(),'Sort By')]/ancestor::div[5]/div"));
		System.out.println("Number of product displayed: " + (rows.size()-3));
		
		driver.findElements(By.xpath("//span[contains(text(),'Sort By')]/ancestor::div[5]/div[2]/div/div/div/a"));
		
		for(int i = 2; i <= (rows.size()-2); i++) {
			
			String linkPath = "//span[contains(text(),'Sort By')]/ancestor::div[5]/div[" + i + "]" + "/div/div/div/a";
			String productPath = "//span[contains(text(),'Sort By')]/ancestor::div[5]/div[" + i + "]" + "/div/div/div/a/div[2]/div[1]/div[1]";
			String pricePath = "//span[contains(text(),'Sort By')]/ancestor::div[5]/div[" + i + "]" + "/div/div/div/a/div[2]/div[2]/div/div/div";
			
			WebElement a = driver.findElement(By.xpath(linkPath));
			productLink.add(a.getAttribute("href"));
			productName.add(driver.findElement(By.xpath(productPath)).getText());
			productPrice.add(driver.findElement(By.xpath(pricePath)).getText());
			
		}
		
		for(int i = 0; i < (rows.size()-3); i++) {
			System.out.println(productName.get(i) + "\t" + productPrice.get(i));
			System.out.println(productLink.get(i));
		}

	}

}
