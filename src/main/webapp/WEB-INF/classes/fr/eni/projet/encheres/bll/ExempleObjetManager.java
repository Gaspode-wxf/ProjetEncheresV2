package fr.eni.projet.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.encheres.bo.ExempleObjet;
import fr.eni.projet.encheres.dal.DALException;
import fr.eni.projet.encheres.dal.DAO;
import fr.eni.projet.encheres.dal.DAOFactory;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class ExempleObjetManager implements AbstractManager<ExempleObjet> {

	private DAO<ExempleObjet> daoExempleObjet;
	private List<ExempleObjet> catalogue = new ArrayList<ExempleObjet>();

	public ExempleObjetManager() throws BLLException {

		this.daoExempleObjet = DAOFactory.getExempleObjetDAO();
	}

	public List<ExempleObjet> getCatalogue() throws BLLException {

		try {
			this.catalogue = daoExempleObjet.selectAll();
		} catch (DALException e) {
			throw new BLLException("echec accès catalogue", e);
		}
		return catalogue;

	}

	public void addItem(ExempleObjet exOb) throws BLLException {

		if (exOb.getId() != null) {
			throw new BLLException("Aliment déjà catalogué");
		}
		validerItem(exOb);
		try {
			this.daoExempleObjet.insert(exOb);
		} catch (DALException e) {
			throw new BLLException("echec insertion Aliment", e);
		}

	}

	public void updateItem(ExempleObjet exOb) throws BLLException {

		validerItem(exOb);
		try {

			this.daoExempleObjet.update(exOb);
		} catch (DALException e) {
			throw new BLLException("echec mise à jour Aliment", e);
		}

	}

	public void removeItem(ExempleObjet exOb) throws BLLException {

		try {
			this.daoExempleObjet.delete(exOb);
		} catch (DALException e) {
			e.printStackTrace();
		}

	}

	public void validerItem(ExempleObjet exOb) throws BLLException {
		// à définir en fonction de l'objet sécurité supplémentaire aux Check SQL
	}

	public ExempleObjet getItem(int index) throws BLLException {
		ExempleObjet exOb = null;

		try {
			exOb = this.daoExempleObjet.selectByID(index);
		} catch (DALException e) {
			throw new BLLException("echec récupération I", e);
		}

		return exOb;

	}

	public void nettoyerBDD() throws BLLException {
		try {
			this.daoExempleObjet.deleteAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
