<%@page import="io.opentelemetry.exporter.logging.SystemOutLogRecordExporter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="classes.Product" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.Iterator" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Price Checker</title>
</head>
<body>

<h1>Price Checker</h1>
<p>Enter a product to search and compare the price from three online grocery retailers; Voila, Loblaws, and Longo's.</p>

<form action="results" method="post">
	<input name="search" placeholder="Enter a product" required>
	<br />
	<input type="submit" value="Search for product">
</form>

<h3>Results</h3>

<table>
	<tr>
		<td><b>Product Name</b></td>
		<td><b>Product Price</b></td>
		<td><b>Product Size</b></td>
		<td><b>Product Image</b></td>
	</tr>

<% 

@SuppressWarnings("unchecked")
ArrayList<Product> longosProduct = (ArrayList<Product>) request.getAttribute("longosProduct");
			
if (longosProduct != null ) {
	for (Product p : longosProduct) { %>
	
	<tr><td>
	<a href=<%= p.getProductUrl() %>><%= p.getProductName() %></a>
	</td><td>
	<%= p.getProductPrice() %>
	</td><td>
	<%= p.getProductSize() %>
	</td><td>
	<img src=<%= p.getProductImage() %>></img>
	</td></tr>	
	<% }
} %>

</table>

</body>
</html>