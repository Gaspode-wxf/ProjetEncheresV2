package fr.eni.projet.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.encheres.bo.Article;
import fr.eni.projet.encheres.bo.Enchere;
import fr.eni.projet.encheres.dal.DALException;
import fr.eni.projet.encheres.dal.DAO;
import fr.eni.projet.encheres.dal.DAOEnchere;
import fr.eni.projet.encheres.dal.DAOFactory;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class EnchereManager implements AbstractManager<Enchere> {

	private DAO<Enchere> daoEnchere;
	private List<Enchere> catalogue = new ArrayList<Enchere>();

	public EnchereManager() throws BLLException {

		this.daoEnchere = DAOFactory.getEnchereDAO();
	}

	public List<Enchere> getCatalogue() throws BLLException {

		try {
			this.catalogue = daoEnchere.selectAll();
		} catch (DALException e) {
			throw new BLLException("echec accès catalogue", e);
		}
		return catalogue;

	}

	public void addItem(Enchere enc) throws BLLException {

		
		validerItem(enc);
		try {
			this.daoEnchere.insert(enc);
		} catch (DALException e) {
			throw new BLLException("echec insertion Enchere", e);
		}

	}

	public void updateItem(Enchere enc) throws BLLException {

		validerItem(enc);
		try {

			this.daoEnchere.update(enc);
		} catch (DALException e) {
			throw new BLLException("echec mise à jour Utilisateur", e);
		}

	}

	public void removeItem(Enchere enc) throws BLLException {

		try {
			this.daoEnchere.delete(enc);
		} catch (DALException e) {
			System.err.println("erreur suppression Utilisateur");

		}

	}

	public void validerItem(Enchere enc) throws BLLException {
		// à définir en fonction de l'objet sécurité supplémentaire aux Check SQL
	}

//	public Enchere getItem(Integer idArticle, Integer idUtilisateur) throws BLLException {
//		Enchere enc = null;
//
//		try {
//			
//			enc = ((DAOEnchere)this.daoEnchere).selectByID(idArticle, idUtilisateur);
//		} catch (DALException e) {
//			System.err.println("echec récupération utilisateur");
//			throw new BLLException("echec récupération utilisateur", e);
//		}
//
//		return enc;
//
//	}
	
	
	// TODO on aura surement besoin de voir la liste des encheres sur un article A completer si nécessaire
	public List<Enchere> getEncheresArticle(Article art){
		List<Enchere> liste = new ArrayList<Enchere>();
		
		try {
			liste = ((DAOEnchere) this.daoEnchere).selectByArticle(art);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
		return liste;
		
	}

	public void nettoyerBDD() throws BLLException {

		try {
			this.daoEnchere.deleteAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Enchere getItem(int index) throws BLLException {
		try {
			this.daoEnchere.selectByID(index);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
