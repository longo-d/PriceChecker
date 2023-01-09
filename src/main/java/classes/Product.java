package classes;

public class Product {
	
	private String productName;
	private String productPrice;
	private String productSize;
	private String productUrl;
	private String productImage;

	public Product(ProductBuilder productBuilder) {
		this.productName = productBuilder.productName;
		this.productPrice = productBuilder.productPrice;
		this.productSize = productBuilder.productSize;
		this.productUrl = productBuilder.productUrl;
		this.productImage = productBuilder.productImage;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getProductPrice() {
		return productPrice;
	}
	
	public String getProductSize() {
		return productSize;
	}
	
	public String getProductUrl() {
		return productUrl;
	}
	
	public String getProductImage() {
		return productImage;
	}
 	
}
