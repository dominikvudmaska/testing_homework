package newpackage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.testng.annotations.*;

public class MyClass {

	WebDriver driver = new EdgeDriver();
	Actions act = new Actions(driver);

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "C:\\Akademia\\Testing\\msedgedriver.exe");

		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
	}

	@Test
	public void fillTheForm() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id=\"frame-one1434677811\"]")));
		driver.findElement(By.id("RESULT_TextField-1")).sendKeys("Dominik");
		driver.findElement(By.id("RESULT_TextField-2")).sendKeys("Vudmaska");
		driver.findElement(By.id("RESULT_TextField-3")).sendKeys("0123456789");
		driver.findElement(By.id("RESULT_TextField-4")).sendKeys("Slovakia");
		driver.findElement(By.id("RESULT_TextField-5")).sendKeys("Kosice");
		driver.findElement(By.id("RESULT_TextField-6")).sendKeys("mail@site.com");

		driver.findElement(By.xpath("//*[@id=\"q26\"]/table/tbody/tr[1]/td/label")).click();
		driver.findElement(By.xpath("//*[@id=\"q15\"]/table/tbody/tr[1]/td/label")).click();
		driver.findElement(By.xpath("//*[@id=\"q15\"]/table/tbody/tr[3]/td/label")).click();
		driver.findElement(By.xpath("//*[@id=\"q15\"]/table/tbody/tr[4]/td/label")).click();

		Select dropdown = new Select(driver.findElement(By.id("RESULT_RadioButton-9")));
		dropdown.selectByVisibleText("Afternoon");

		// FILE UPLOAD

		// WebElement uploadElement = driver.findElement(By.id("RESULT_FileUpload-10"));
		// uploadElement.sendKeys(System.getProperty("user.dir")+"\\src\\newpackage\\upload.txt");

		// FORM SUBMIT

		// driver.findElement(By.id("FSsubmit")).click();
		
		driver.switchTo().defaultContent();
	}

	@Test
	public void chooseValues() {
		
		// ACCEPT ALERT

		driver.findElement(By.xpath("//*[@id=\"HTML9\"]/div[1]/button")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

		// CHOOSE CURRENT DATE IN DATE PICKER

		driver.findElement(By.id("datepicker")).click();
		driver.findElement(By.className("ui-state-highlight")).click();

		// CHOOSE VALUES IN THE LEFT DROPDOWN

		Select speed = new Select(driver.findElement(By.id("speed")));
		speed.selectByVisibleText("Medium");

		Select files = new Select(driver.findElement(By.id("files")));
		files.selectByVisibleText("PDF file");

		Select number = new Select(driver.findElement(By.id("number")));
		number.selectByVisibleText("5");

		Select products = new Select(driver.findElement(By.id("products")));
		products.selectByVisibleText("Bing");

		Select animals = new Select(driver.findElement(By.id("animals")));
		animals.selectByVisibleText("Avatar");
		
	}

	@Test
	public void doubleClickTest() {
		
		// DOUBLE CLICK AND COMPARE FIELDS

		Actions actions = new Actions(driver);
		WebElement copyTextButton = driver.findElement(By.xpath("//*[@id=\"HTML10\"]/div[1]/button"));
		actions.doubleClick(copyTextButton).perform();

		WebElement field1 = driver.findElement(By.id("field1"));
		WebElement field2 = driver.findElement(By.id("field2"));

		String val = field1.getAttribute("value");
		String val2 = field2.getAttribute("value");
		Assert.assertEquals(val, val2);

		// DRAG AND DROP

		WebElement from = driver.findElement(By.id("draggable"));
		WebElement to = driver.findElement(By.id("droppable"));
		act.dragAndDrop(from, to).build().perform();
	}

	@Test
	public void dragAndDropTest() {
		
		// DRAG AND DROP TO TRASH

		WebElement gallery = driver.findElement(By.xpath("//*[@id=\"gallery\"]"));
		WebElement trash = driver.findElement(By.xpath("//*[@id=\"trash\"]"));

		List<WebElement> listOfImages = gallery.findElements(By.tagName("li"));
		for (int i = 0; i < listOfImages.size(); i++) {
			act.dragAndDrop(listOfImages.get(i), trash).build().perform();
		}
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"trash\"]/ul")));
		
		WebElement trashList = driver.findElement(By.xpath("//*[@id=\"trash\"]/ul"));
		List<WebElement> listOfTrashItems = trashList.findElements(By.tagName("li"));

		Assert.assertEquals(listOfTrashItems.size(), 2);
		
		// REMOVE FROM TRASH

		for (int i = 0; i < listOfTrashItems.size(); i++) {
			act.dragAndDrop(listOfTrashItems.get(i), gallery).build().perform();
		}
		
		// MOVE SLIDER TO THE MIDDLE
		
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"slider\"]"));
		int sliderWidth = slider.getSize().getWidth();
		WebElement sliderHandle = driver.findElement(By.xpath("//*[@id=\"slider\"]/span"));
        	Actions move = new Actions(driver);
        	Action action = (Action) move.dragAndDropBy(sliderHandle, sliderWidth/2, 0).build();
        	action.perform();

	}

	@AfterTest
	public void tearDown() {
		 driver.quit();
	}
}
