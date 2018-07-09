package com.greglturnquist.learningspringboot.e2e;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

/**
 * @author au
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndToEndTests {

	@Autowired
	WebDriver driver;

	@LocalServerPort
	int port;

	@Test
	public void homePageShouldWork() throws IOException {

		driver.get("http://localhost:" + port);
		takeScreenshot("homePageShouldWork-1");

		assertThat(driver.getTitle())
			.isEqualTo("Learning Spring Boot: Spring-a-Gram");

		String pageContent = driver.getPageSource();

		assertThat(pageContent)
			.contains("<a href=\"/images/bazinga.png/raw\">");

		WebElement element = driver.findElement(By.cssSelector("a[href*=\"bazinga.png\"]"));
		Actions actions = new Actions(driver);
		actions
			.moveToElement(element)
			.click()
			.perform();

		takeScreenshot("homePageShouldWork-2");
		driver
			.navigate()
			.back();
	}

	private void takeScreenshot(String name) throws IOException {
		FileCopyUtils.copy(
						((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE),
						new File("build/test-results/TEST-" + name + ".png"));
	}


}
