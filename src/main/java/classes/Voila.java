package classes;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Voila {

	private Product product;
	private String search = null;
	private String url = "https://voila.ca/products/search?q="+search;
	
	private Document connect() {
		try {
			// connect and retrieve webpage
			Document page = Jsoup.connect(url).get();
			return page;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
		public Elements pageContainer() {
			Document page = connect();
			Elements container = page.select("div.base__Wrapper-sc-1mnb0pd-6.base__FixedHeightWrapper-sc-1mnb0pd-41.gMlFiL.gNSCsF.viewports-enabled-standard-fop__ViewportsEnabledFop-sc-nz4zf7-0.iGEpfJ");
			return container;
		}
	
		public void information(Elements container) {
			int i = 0;
			for (Element e : container) {
				
				Elements productName = e.select("a.link__Link-sc-14ymsi2-0.kXUtxJ.link__Link-sc-14ymsi2-0.base__Title-sc-1mnb0pd-27.base__FixedHeightTitle-sc-1mnb0pd-43.kXUtxJ.eFGEpl.cCRJZx");
				Elements productPrice = e.select("div.base__PriceWrapper-sc-1mnb0pd-28.dDLLyP");
				
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
}
