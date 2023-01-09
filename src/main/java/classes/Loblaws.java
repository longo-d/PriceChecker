package classes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Loblaws {

	private Product product;
	private String search= null;
	private String url = "https://www.loblaws.ca/search?search-bar="+search;
	private WebDriver driver;
	
	private Document connect() {
		try {
			// connect and retrieve webpage
			System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			driver = new ChromeDriver(options);
			driver.get(url);
			Thread.sleep(5000);

			String pageSource= driver.getPageSource();
			Document page = Jsoup.parse(pageSource);
						
			return page;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
		public Elements pageContainer() {
			Elements container = connect().select("div.product-tracking");			
			return container;		
		}
	
		public void information(Elements container) {
			int i = 0;
			for (Element e : container) {
				
				Elements productName = e.select("span.product-name__item.product-name__item--name");
				Elements productPrice = e.select("span.price__value.selling-price-list__item__price.selling-price-list__item__price--now-price__value");

				product = new ProductBuilder()
						.buildProductName(productName.text())
						.buildProductPrice(productPrice.text())
						.build();
				
				i++;
				System.out.println(i);
				
				System.out.println(product.getProductName());
				System.out.println(product.getProductPrice()+"\n");
								
				if (i==3) break;
				
			}
		}	
		
		protected void closeDriver() {
			driver.quit();
		}
}
