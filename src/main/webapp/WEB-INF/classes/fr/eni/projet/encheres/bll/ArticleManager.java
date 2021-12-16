/**
 * 
 */
package fr.eni.projet.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Categorie;
import fr.eni.projet.encheres.bo.user.Vendeur;
import fr.eni.projet.encheres.dal.DALException;
import fr.eni.projet.encheres.dal.DAO;
import fr.eni.projet.encheres.dal.DAOArticle;
import fr.eni.projet.encheres.dal.DAOFactory;

/**
 * @author William
 *
 */
public class ArticleManager implements AbstractManager<Article> {

	private DAO<Article> daoArticle;
	private List<Article> catalogue = new ArrayList<Article>();

	public ArticleManager() throws BLLException {
		this.daoArticle = DAOFactory.getArticleDAO();
	}

	public List<Article> getCatalogue() throws BLLException {
		try {
			this.catalogue = daoArticle.selectAll();
		} catch (DALException e) {
			throw new BLLException("échec accès catalogue", e);
		}
		return catalogue;
	}

	public void addItem(Article a) throws BLLException {
		validerItem(a);
		try {
			this.daoArticle.insert(a);
		} catch (DALException e) {
			throw new BLLException("échec insertion Article", e);
		}
	}

	public void updateItem(Article a) throws BLLException {
		validerItem(a);
		try {
			this.daoArticle.update(a);
		} catch (DALException e) {
			throw new BLLException("échec mise à jour Article", e);
		}
	}

	public void removeItem(Article a) throws BLLException {
		validerItem(a);
		try {
			this.daoArticle.delete(a);
		} catch (DALException e) {
			System.err.println("erreur suppression Article");
		}
	}

	public void validerItem(Article a) throws BLLException {
		// A definir
	}

	public Article getItem(Integer id) throws BLLException {
		Article a = null;

		try {
			a = this.daoArticle.selectByID(id);
		} catch (DALException e) {
			System.err.println("échec récupération Article");
			throw new BLLException("échec récupération Article", e);
		}
		return a;
	}

	@Override
	public Article getItem(int index) throws BLLException {
		try {
			this.daoArticle.selectByID(index);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Article> getListeEnCours() throws BLLException {

		List<Article> liste = null;
		try {
			liste = ((DAOArticle) this.daoArticle).selectBy();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;

	}

	public List<Article> getListeEnCours(String mot) throws BLLException {

		List<Article> liste = null;
		try {
			liste = ((DAOArticle) this.daoArticle).selectBy(mot);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;

	}

	public List<Article> getListeEnCours(Categorie cat) throws BLLException {

		List<Article> liste = null;
		try {
			liste = ((DAOArticle) this.daoArticle).selectBy(cat);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;

	}

	public List<Article> getListeEnCours(Categorie cat, String mot) throws BLLException {

		List<Article> liste = null;
		try {
			liste = ((DAOArticle) this.daoArticle).selectBy(cat, mot);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;

	}

	public List<Article> getListUtilisateur(Vendeur ven) throws BLLException {
		try {
			this.catalogue = ((DAOArticle) daoArticle).selectByUtilisateur(ven);
		} catch (DALException e) {
			throw new BLLException("échec accès catalogue Utilisateur", e);
		}
		return catalogue;
	}

	public List<Article> getArticlesFuturEnc(Vendeur ven) throws BLLException {
		try {
			this.catalogue = ((DAOArticle) daoArticle).selectByFuturEnc(ven);
		} catch (DALException e) {
			throw new BLLException("échec accès catalogue Utilisateur", e);
		}
		return catalogue;
	}

	public List<Article> getArticlesPasseEnc(Vendeur ven) throws BLLException {
		try {
			this.catalogue = ((DAOArticle) daoArticle).selectByPasseEnc(ven);
		} catch (DALException e) {
			throw new BLLException("échec accès catalogue Utilisateur", e);
		}
		return catalogue;
	}

	public List<Article> getArticlesEncheresEnCours(Vendeur ach) throws BLLException {
		try {
			this.catalogue = ((DAOArticle) daoArticle).selectByAcheteur(ach);
		} catch (DALException e) {
			throw new BLLException("échec accès catalogue Utilisateur", e);
		}
		return catalogue;
	}

	public List<Article> getArticlesAcquis(Vendeur ach) throws BLLException {
		try {
			this.catalogue = ((DAOArticle) daoArticle).selectAcquis(ach);
		} catch (DALException e) {
			throw new BLLException("échec accès catalogue Utilisateur", e);
		}
		return catalogue;
	}

	public void nettoyerBDD() throws BLLException {
		try {
			this.daoArticle.deleteAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
