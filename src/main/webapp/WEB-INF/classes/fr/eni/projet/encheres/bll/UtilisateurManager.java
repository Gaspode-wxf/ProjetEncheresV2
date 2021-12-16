package fr.eni.projet.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.encheres.bo.user.Utilisateur;
import fr.eni.projet.encheres.bo.user.Vendeur;
import fr.eni.projet.encheres.dal.DALException;
import fr.eni.projet.encheres.dal.DAO;
import fr.eni.projet.encheres.dal.DAOFactory;
import fr.eni.projet.encheres.dal.DAOUtilisateur;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class UtilisateurManager implements AbstractManager<Utilisateur> {

	private DAO<Utilisateur> daoUtilisateur;
	private List<Utilisateur> catalogue = new ArrayList<Utilisateur>();

	public UtilisateurManager() throws BLLException {

		this.daoUtilisateur = DAOFactory.getUtilisateurDAO();
	}

	public List<Utilisateur> getCatalogue() throws BLLException {

		try {
			this.catalogue = daoUtilisateur.selectAll();
		} catch (DALException e) {
			throw new BLLException("echec accès catalogue", e);
		}
		return catalogue;

	}

	public void addItem(Utilisateur user) throws BLLException {

		if (user.getId() != null) {
			throw new BLLException("Utilisateur déjà catalogué");
		}
		validerItem(user);
		try {
			this.daoUtilisateur.insert(user);
		} catch (DALException e) {
			throw new BLLException("echec insertion Utilisateur", e);
		}

	}

	public void updateItem(Utilisateur user) throws BLLException {

		validerItem(user);
		try {

			this.daoUtilisateur.update(user);
		} catch (DALException e) {
			throw new BLLException("echec mise à jour Utilisateur", e);
		}

	}

	public void removeItem(Utilisateur user) throws BLLException {

		try {
			this.daoUtilisateur.delete(user);
		} catch (DALException e) {
			System.err.println("erreur suppression Utilisateur");

		}

	}

	public void validerItem(Utilisateur user) throws BLLException {
		// à définir en fonction de l'objet sécurité supplémentaire aux Check SQL
	}

	public Utilisateur getItem(int index) throws BLLException {
		Utilisateur user = null;

		try {
			user = this.daoUtilisateur.selectByID(index);
		} catch (DALException e) {
			System.err.println("echec récupération utilisateur");
			throw new BLLException("echec récupération utilisateur", e);
		}

		return user;

	}
	
	public Vendeur getVendeurViaPseudo(String pseudo) throws BLLException {
		Vendeur user = null;

		try {
			user = ((DAOUtilisateur) this.daoUtilisateur).SelectByPseudo(pseudo);
		} catch (DALException e) {
			System.err.println("echec récupération utilisateur");
			throw new BLLException("echec récupération utilisateur", e);
		}

		return user;

	}

	public void nettoyerBDD() throws BLLException {

		try {
			this.daoUtilisateur.deleteAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
