package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"/Users/muhtar/Documents/selenium dependencies/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester"); // userName
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test"); // password
		driver.findElement(By.name("ctl00$MainContent$login_button")).click(); // click login

		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click(); // click order

		Random r = new Random();
		int result = r.nextInt(100) + 1;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys("" + result); // enter random
																										// quantity

		int middleNameLength = r.nextInt(15);

		int a = 97; // letter 'a'
		int z = 122; // letter 'z'
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < middleNameLength; i++) {
			int randomLetterInt = r.nextInt(z - a + 1) + a;
			sb.append((char) randomLetterInt);
		}
		String middleName = sb.toString(); // get a random middleName
		// System.out.println(middleName);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John " + middleName + " Smith"); // enter
																														// random
																														// middleName

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st"); // street
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown"); // city
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia"); // state

		int zip = r.nextInt(100000 - 10000) + 10000;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys("" + zip);

		int target = r.nextInt(3) + 1; // get a random number from 1 to 3 (inclusive)

		if (target == 1) {
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_0\"]")).click();
			StringBuilder cardNumber = new StringBuilder("4");
			for (int i = 0; i < 15; i++) {
				cardNumber.append(r.nextInt(10));
			}
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
		} else if (target == 2) {
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_1\"]")).click();
			StringBuilder cardNumber = new StringBuilder("5");
			for (int i = 0; i < 15; i++) {
				cardNumber.append(r.nextInt(10));
			}
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
		} else {
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_cardList_2\"]")).click();
			StringBuilder cardNumber = new StringBuilder("3");
			for (int i = 0; i < 15; i++) {
				cardNumber.append(r.nextInt(10));
			}
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
		}

		int m = r.nextInt(12)+1;  // get month for expiration date
		String month = "" + m;
		if (m < 10) {
			month = 0 + month;
		}
		int y = r.nextInt(100);// get year for expiration date
		String year = "" + y;
		if (y < 10) {
			year = 0 + year;
		}
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox1\"]")).sendKeys(month + "/" + year);// input expiration date

		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_InsertButton\"]")).click();
		
		String expected="New order has been successfully added";
		
		String actual= driver.getPageSource();
		if(actual.contains(expected)) {
			System.out.println("pass");
		}else {
			System.out.println("fail");
			System.out.println("Expected:\t"+expected);
			System.out.println("Actual:\t"+actual);
		}
	}

}
