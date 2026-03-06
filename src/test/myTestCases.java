package test;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import org.openqa.selenium.support.ui.Select;

public class myTestCases {
	WebDriver driver = new ChromeDriver();
	String Website ="https://codenboxautomationlab.com/practice/";
	Random rand = new Random();
	
	@BeforeTest
	public void mySetUp() {
		
		driver.get(Website);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
	}
	

	@Test (enabled = false )
	public void RadioButton() {
		WebElement RadioButtonDiv = driver.findElement(By.id("radio-btn-example"));  
		RadioButtonDiv.findElements(By.tagName("input")).get(0).click();   
		
		Boolean ActualResult = RadioButtonDiv.findElements(By.tagName("input")).get(0).isSelected();
		Boolean ExpectedResult = true;
		Assert.assertEquals( ActualResult, ExpectedResult);
	}
	
	
	@Test (enabled = false)
	public void DynamicDropdown() throws InterruptedException {
		String [] Countries = {"jo" ,"sy" ,"ir"};
		int randomIndex = rand.nextInt(Countries.length);
		String Country = Countries[randomIndex];
		
		driver.findElement(By.id("autocomplete")).sendKeys(Country);
		Thread.sleep(1000);
		driver.findElement(By.id("autocomplete")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		
		System.out.println(driver.findElement(By.id("autocomplete")).getText());
	}
	
	
	@Test(enabled = true)
	public void StaticDropdown() {
		WebElement SelectTag = driver.findElement(By.id("dropdown-class-example"));
		Select mySelector = new Select(SelectTag);
		//mySelector.selectByIndex(0);  
		//mySelector.selectByIndex(1);   
		//mySelector.selectByVisibleText("Selenium");
		mySelector.selectByValue("option2"); 
	
}
	
	@Test (enabled = false)
	public void Checkbox() {
		WebElement CheckBox = driver.findElement(By.id("checkbox-example"));
		List<WebElement>AllCheckBoxes = CheckBox.findElements(By.tagName("input"));
		for (int i =0 ; i<AllCheckBoxes.size();i++) {
			AllCheckBoxes.get(i).click();
			Assert.assertEquals(AllCheckBoxes.get(i).isSelected() , true);
		}
		
	}	
	
	@Test (enabled = false)
	public void switchWindow () throws InterruptedException {
		 WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));
		    OpenWindowButton.click();
		    
		Set<String> handles = driver.getWindowHandles();   // Set ما فيها ((index))
		List<String>allTabs = new ArrayList<>(handles);    // سطرين دائما نستخدمهم لما بدي انتقل من صقحه لصفحه
	    driver.switchTo().window(allTabs.get(1));
	    Thread.sleep(3000);
	    System.out.println(driver.getTitle());            // عنوان الصفحه الجديده(1)
	    
        driver.switchTo().window(allTabs.get(0));
	    Thread.sleep(3000);
	    System.out.println(driver.getTitle());           // (0)عنوان الصفحه القديمه
	}
	

	@Test (enabled= false)
	public void switchTab() throws InterruptedException {
		WebElement OpenTabButton = driver.findElement(By.id("opentab"));
		OpenTabButton.click();
		
		Set<String> handles = driver.getWindowHandles();   
		List<String>allTabs = new ArrayList<>(handles);   
	    driver.switchTo().window(allTabs.get(1));
	    
	    Thread.sleep(3000);
	    System.out.println(driver.getTitle()); 
	    
	    driver.switchTo().window(allTabs.get(0));
	    Thread.sleep(3000);
	    System.out.println(driver.getTitle()); 
	}
	
	@Test (enabled = false)
	public void AlertAndConfirm () throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;    
		js.executeScript("window.scrollTo(0,500)");
		
		//driver.findElement(By.id("name")).sendKeys("anfal");
		String name = "anfal";
		driver.findElement(By.id("name")).sendKeys(name);
		
		//     Alert         //
		//driver.findElement(By.id("alertbtn")).click();    
		//driver.switchTo().alert().accept();
		
		//       Confirm       //
		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(3000);
	   //System.out.println(driver.switchTo().alert().getText());              
		System.out.println(driver.switchTo().alert().getText().contains(name));  
		boolean ActualValue = driver.switchTo().alert().getText().contains(name);
		Assert.assertEquals(ActualValue, true);
		
		driver.switchTo().alert().dismiss();              
	}
   
	@Test ( enabled= false)
	public void TheTable() {
		WebElement theTable = driver.findElement(By.id("product"));
		List <WebElement> AllData =theTable.findElements(By.tagName("td"));
		for (int i=0 ; i<AllData.size() ; i++) {
			System.out.println(AllData.get(i).getText());
			
		/* List <WebElement> AllData =theTable.findElements(By.tagName("tr"));
		for (int i=0 ; i<AllData.size() ; i++) {
			System.out.println(AllData.get(i).getText()); */
			
		/* List <WebElement> AllData =theTable.findElements(By.tagName("th"));
		for (int i=0 ; i<AllData.size() ; i++) {
			System.out.println(AllData.get(i).getText()); */
		}
			
		/* List <WebElement> AllData =theTable.findElements(By.tagName("td"));
				System.out.println(AllData.get(1).getText()); */
		
	}
	
	@Test (enabled = false)
	public void HideAndShow (){
		WebElement HideButton =driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton =driver.findElement(By.id("show-textbox"));
		WebElement TextBox =driver.findElement(By.id("displayed-text"));
		 HideButton.click();
		 
		 boolean ActualResult = TextBox.isDisplayed();
		 boolean ExpectedResult = false;
		 Assert.assertEquals(ActualResult, ExpectedResult);
		
	}
	
	@Test (enabled = false)
	public void EnabledAndDisabled (){
		WebElement DisabledButton =driver.findElement(By.id("disabled-button"));
		DisabledButton.click();
	
		WebElement TextBox =driver.findElement(By.id("enabled-example-input"));
		boolean ActualResult =TextBox.isEnabled();
		boolean ExpectedResult = false;
		Assert.assertEquals( ActualResult , ExpectedResult);
	
	}
	
	@Test (enabled = false) 
	public void MouseHover() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;   
		js.executeScript("window.scrollTo(0,1700)");
		Thread.sleep(2000);
		
		WebElement MouseHover =driver.findElement(By.id("mousehover"));
		Actions action = new Actions(driver);              
		action.moveToElement(MouseHover).build().perform();         
		
		driver.findElement(By.linkText("Reload")).click();          
	
	}
	
	@Test (enabled = false) 
	public void Calender () throws IOException, InterruptedException {
		Date myDate = new Date();
		String fileName = myDate.toString().replace(":", "-");
		System.out.println();
		
		WebElement Calender = driver.findElement(By.linkText("Booking Calendar"));
		Calender.click();
		
		Set<String> handles2 = driver.getWindowHandles();   
		List<String>allTabs2 = new ArrayList<>(handles2);   
	    driver.switchTo().window(allTabs2.get(1));
        
	    Thread.sleep(3000);
	    
	    TakesScreenshot ts = (TakesScreenshot)driver;      
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("./screenshot/"+fileName+".jpg")); 
	}
	
	@Test (enabled = false)
	public void Iframe() {
		//driver.findElement(By.xpath("//*[@id=\"slider-9-slide-18-layer-1\"]")).click();
		//driver.switchTo().frame("iframe-name");
		driver.findElement(By.cssSelector(".ct-mobile-meta-item.btn-nav-mobile.open-menu")).click();

	}
	
}