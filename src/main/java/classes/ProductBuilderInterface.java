package classes;

public interface ProductBuilderInterface {

	ProductBuilder buildProductName(String productName);
	ProductBuilder buildProductPrice(String productPrice);
	ProductBuilder buildProductSize(String productSize);
	ProductBuilder buildProductUrl(String productUrl);
	ProductBuilder buildProductImage(String productImage);
	
}
