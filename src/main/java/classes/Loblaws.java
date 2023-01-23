package classes;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Loblaws {

	private Product product;
	private String url;
	private WebDriver driver;
	private ArrayList<Product> products = new ArrayList<Product>();
	private String loblawsUrl = "https://www.loblaws.ca";
	
	private Document connect(String search) {
		
		url = "https://www.loblaws.ca/search?search-bar="+search;

		// connect and retrieve webpage
		System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);
		driver.get(url);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String pageSource= driver.getPageSource();
		Document page = Jsoup.parse(pageSource);
					
		return page;
			
	}
	
		public Elements pageContainer(String search) {
			Elements container = connect(search).select("div.product-tracking");			
			return container;		
		}
	
		public ArrayList<Product> information(Elements container) {
			int i = 0;
			for (Element e : container) {
			
					// sizing is not being picked up
					// add price per 100g
				
				Element productName = e.select("span.product-name__item.product-name__item--name").first();
				Element productPrice = e.select("span.price__value.selling-price-list__item__price.selling-price-list__item__price--now-price__value").first();
				Element productUrl = e.selectFirst("a");
				Element productSize = e.select("span.product-name__item.product-name__item--package-size").first();
				Element productImage = e.select("img").first();
				Element productPer = e.select("span.price.comparison-price-list__item__price").first();
				
				product = new ProductBuilder()
						.buildProductName(productName.text())
						.buildProductPrice(productPrice.text())
						.buildProductImage(productImage.attr("src"))
						.buildProductSize(checkSize(productSize))
						.buildProductUrl(loblawsUrl + productUrl.attr("href"))
						.buildProductPer(productPer.text())
						.build();
				
				i++;
				System.out.println(i);
				
				System.out.println(product.getProductName());
				System.out.println(product.getProductPer()+"\n");
				
				products.add(product);
								
				if (i==3) return products;
			}
			return null;
		}	
		
		protected void closeDriver() {
			driver.quit();
		}
		
//		check size if null
		private String checkSize(Element productSize) {
			if (productSize == null) {
				return "n/a";
			}
			return productSize.text();
		}
}
