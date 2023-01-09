package servlets;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.select.Elements;

import classes.Longos;
import classes.Product;

@WebServlet("/results")
public class Scrape extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
				
		final String destPage = "index.jsp";
		request.getRequestDispatcher(destPage).forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String search = request.getParameter("search");
				
		Longos longos = new Longos();
		Elements longosContainer = longos.pageContainer(search);
		ArrayList<Product> longosProduct = longos.information(longosContainer);
				
		request.setAttribute("longosProduct", longosProduct);

		doGet(request, response);

	}
		
}
