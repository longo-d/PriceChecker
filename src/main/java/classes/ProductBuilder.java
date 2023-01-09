package classes;

public class ProductBuilder implements ProductBuilderInterface {

	String productName;
	String productPrice;
	String productSize;
	String productUrl;
	String productImage;
	
	public Product build() {
		return new Product(this);
	}
	
	public ProductBuilder() { }
	
	@Override
	public ProductBuilder buildProductName(String productName) {
		this.productName = productName;
		return this;
	}

	@Override
	public ProductBuilder buildProductPrice(String productPrice) {
		this.productPrice = productPrice;
		return this;
	}

	@Override
	public ProductBuilder buildProductSize(String productSize) {
		this.productSize = productSize;
		return this;
	}

	@Override
	public ProductBuilder buildProductUrl(String productUrl) {
		this.productUrl = productUrl;
		return this;
	}
	
	@Override
	public ProductBuilder buildProductImage(String productImage) {
		this.productImage= productImage;
		return this;
	}

}
