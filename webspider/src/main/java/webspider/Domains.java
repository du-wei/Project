package webspider;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import jodd.http.HttpRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Domains {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.firefox.bin", "D:\\Program\\Mozilla Firefox\\firefox.exe");
		System.setProperty("webdriver.firefox.bin", "C:\\Programs\\Mozilla Firefox\\firefox.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://www.chinaz.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testHee() throws Exception {
		String[] dates = new String[]{
//			"2015-08-01",
			"2015-08-02",
			"2015-08-03"
		};
		String[] key = new String[]{"top", "5"};
		boolean flag = false;
		
		driver.get(baseUrl);
		
		driver.findElement(By.cssSelector("#chinaz_topbar a")).click();
		WebElement username = driver.findElement(By.id("username"));
		username.clear();
		username.sendKeys("googlecosplay@163.com");
	
		WebElement pwd = driver.findElement(By.id("password"));
		pwd.clear();
		pwd.sendKeys("329852101xin");
		driver.findElement(By.id("submit_button")).click();
		
		while(!driver.getCurrentUrl().startsWith("http://www.chinaz.com")){
			Thread.sleep(2000);
		}
		driver.navigate().to("http://mytool.chinaz.com/");
		driver.findElement(By.linkText("过期域名查询")).click();
		
		
		if(flag){
			WebElement wd = driver.findElement(By.id("kw"));
			wd.clear();
			wd.sendKeys(key[0]);
			driver.findElement(By.id("p_3")).click();
			WebElement length = driver.findElement(By.name("el"));
			length.clear();
			length.sendKeys(key[1]);
			start();
		}else {
			for(String dd : dates){
				System.out.println(dd);
				
				Select date = new Select(driver.findElement(By.id("date")));
				date.selectByValue(dd);
				start();
			}
		}
		
		
		
	}

	private void start() throws InterruptedException {
	    driver.findElement(By.cssSelector(".btn-wrap .button")).click();
	    List<WebElement> domains = driver.findElements(By.cssSelector("#tb_domains > tbody td.domainname a"));
	    for(WebElement ele : domains){
	    	String dom = ele.getText();
	    	String[] split = dom.split("\\.");
	    	
	    	String body = HttpRequest.get("http://www.yumingco.com/api/").
	    			query("domain", split[0]).query("suffix", split[1]).send().body();
	    	JSONObject result = JSON.parseObject(body);
	    	System.out.println(dom + " --> " + (result.getBooleanValue("available") ? "可注册" : "已注册"));
	    }
	    Thread.sleep(2000);
    }

	@After
	public void tearDown() throws Exception {
		 driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
