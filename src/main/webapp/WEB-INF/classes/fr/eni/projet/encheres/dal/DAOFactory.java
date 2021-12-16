package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.bo.Enchere;
import fr.eni.projet.encheres.bo.Retrait;
import fr.eni.projet.encheres.bo.user.Utilisateur;
import fr.eni.projet.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.projet.encheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.projet.encheres.dal.jdbc.EnchereJdbcImpl;
import fr.eni.projet.encheres.dal.jdbc.RetraitDAOJdbcImpl;
import fr.eni.projet.encheres.dal.jdbc.UtilisateurJdbcImpl;
import fr.eni.projet.encheres.dal.jdbc.exempleobjet.ExempleObjetDAOJdbcImpl;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class DAOFactory {

	public static DAOExempleObjet getExempleObjetDAO() {
		DAOExempleObjet exempleObjetDAO = new ExempleObjetDAOJdbcImpl();
		return exempleObjetDAO;
	}

	public static DAO<Utilisateur> getUtilisateurDAO() {
		DAOUtilisateur utilisateurDAO = new UtilisateurJdbcImpl();
		return utilisateurDAO;
	}

	public static DAO<Article> getArticleDAO() {
		DAOArticle articleDAO = new ArticleDAOJdbcImpl();
		return articleDAO;
	}

	public static DAO<Categorie> getCategorieDAO() {
		System.out.println("init getCategorieDAO");
		DAOCategorie categorieDAO = new CategorieDAOJdbcImpl();
		return categorieDAO;
	}
	
	public static DAO<Enchere> getEnchereDAO(){
		DAOEnchere enchereDAO = new EnchereJdbcImpl();
		return enchereDAO;
		
	}
	
	public static DAO<Retrait> getRetraitDAO(){
		DAORetrait retraitDAO = new RetraitDAOJdbcImpl();
		return retraitDAO;
	}
}
