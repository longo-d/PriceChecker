package classes;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Voila {

	private Product product;	
	private ArrayList<Product> products = new ArrayList<Product>();
	private final String voilaUrl = "https://www.voila.ca";
	private Element productName, productPrice, productUrl, productSize;
	private int i;
	private String url;
	
//	connect and retrieve webpage based on search
	private Document connect(String search) {
		
//		voila search url
		url = "https://voila.ca/products/search?q="+search;
		
		// connect and retrieve webpage
		try {
			Document page = Jsoup.connect(url).get();
			return page;
		} catch (IOException e) {
			e.printStackTrace();
		}
//		won't be reached
		return null;
	}
	
//		identifies product container
//		misses the first product container for some reason
		public Elements pageContainer(String search) {
			Document page = connect(search);
			Elements container = page.select("div.base__Wrapper-sc-1mnb0pd-6.base__FixedHeightWrapper-sc-1mnb0pd-41.gMlFiL.jNtybn.viewports-enabled-standard-fop__ViewportsEnabledFop-sc-nz4zf7-0.iGEpfJ");
			return container;
		}
	
//		gathers details from each product container 
//		only does the top 3 product results
		public ArrayList<Product> information(Elements container) {
			
//			count for products
			i = 0;
			
//			loop through each container to gather details
			for (Element e : container) {			
				
//				details
				productName = e.select("h3").first();
				productPrice = e.select("strong").first();
				productSize = e.select("span.text__Text-sc-6l1yjp-0.base__SizeText-sc-1mnb0pd-38.bhymDA.iImbUZ").first();
				productUrl = e.select("a").first();
				
//				build the product object
				product = new ProductBuilder()
						.buildProductName(productName.text())
						.buildProductPrice(productPrice.text())
						.buildProductSize(checkSize(productSize))
						.buildProductUrl(voilaUrl + productUrl.attr("href"))
						.build();
				
//				add product object to array list
				products.add(product);
				
//				increment product counter by 1
				i++;

//				break container and return array list when product count is 3
				if (i==3) return products;
			}
//			won't be reached
			return null;
		}	
		
//		check size if null
		private String checkSize(Element productSize) {
			if (productSize == null) {
				return "n/a";
			}
			return productSize.text();
		}
		
}
