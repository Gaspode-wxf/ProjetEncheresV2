package fr.eni.projet.encheres.servlets.tests;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.encheres.bll.ArticleManager;
import fr.eni.projet.encheres.bll.BLLException;
import fr.eni.projet.encheres.bo.Article;

/**
 * Servlet implementation class ServletTestDBArticle
 */
@WebServlet("/ServletTestDBArticle")
public class ServletTestDBArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTestDBArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ouverture Servlet");
		try {

			ArticleManager articleManager = new ArticleManager();
			System.out.println("Manager Chargé");

			List<Article> catalogueArticle = null;

			catalogueArticle = articleManager.getCatalogue();
			System.out.println("catalogue chargé");
			System.out.println("nbr d'entrée au catalogue : " + catalogueArticle.size());

			request.setAttribute("catalogueArticle", catalogueArticle);
		} catch (BLLException e) {
			System.err.println(e);
		}

		// Transfert de l'affichage à la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Views/article.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
