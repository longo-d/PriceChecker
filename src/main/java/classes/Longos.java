package classes;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Longos {

	private Product product;	
	private ArrayList<Product> products = new ArrayList<Product>();
	private WebDriver driver;	
	private final String longosUrl = "https://www.longos.com";
	private Element productName, productDollar, productCents, productUrl, productSize;
	private String productPrice, url;
	private int i, dollarLength;
	
//	connect and retrieve webpage based on search
	private Document connect(String search) {

//		longos search url
		url = "https://www.longos.com/search/"+search+"?q="+search;
		
//		set webdriver properties and options
		System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);

//		connects to the url
		driver.get(url);

//		retrieves page source into string
		String pageSource= driver.getPageSource();

//		parses through page source
		Document page = Jsoup.parse(pageSource);

		return page;
	}
	
//		identifies product container
		public Elements pageContainer(String search) {
			Elements container = connect(search).select("div.card.product-list-item");
			return container;
		}
	
//		information details from container
//		only does the top 3 products results
		public ArrayList<Product> information(Elements container) {
			
//			count for products 
			i = 0;
						
//			loop through each container to gather details
			for (Element e : container) {
				
//				details
				productName = e.select("h5.card-title.mb-0").first();
				productDollar= e.select("strong.price").first();
				productCents = e.select("sup.cents").first();
				productUrl = e.select("a").first();
//				productImage = e.select("img").first();
				productSize = e.select("span.unit").first();
								
//				formatting for product price
//				concatenate dollars and cents
				dollarLength = productDollar.text().length();
				productPrice = productDollar.text().substring(0, dollarLength-2) +"."+ productCents.text();
				
//				build the product object
				product = new ProductBuilder()
						.buildProductName(productName.text())
						.buildProductPrice(productPrice)
//						.buildProductImage(productImage.attr("src"))
						.buildProductUrl(longosUrl + productUrl.attr("href"))
						.buildProductSize(productSize.text().substring(2))
						.build();
				
//				add product object to the array list
				products.add(product);
				
//				increment product count by 1
				i++;
				
//				break container and return array list when product count is 3
				if (i==3) return products;
			}
			
//			this will never be reached
			return null;
			
		}
		
//		close the web driver
		protected void closeDriver() {
			driver.quit();
		}
}
