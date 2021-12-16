package fr.eni.projet.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.encheres.bo.Retrait;
import fr.eni.projet.encheres.dal.DALException;
import fr.eni.projet.encheres.dal.DAO;
import fr.eni.projet.encheres.dal.DAOFactory;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class RetraitManager implements AbstractManager<Retrait> {

	private DAO<Retrait> daoRetrait;
	private List<Retrait> catalogue = new ArrayList<Retrait>();

	public RetraitManager() throws BLLException {

		this.daoRetrait = DAOFactory.getRetraitDAO();
	}

	public List<Retrait> getCatalogue() throws BLLException {

		try {
			this.catalogue = daoRetrait.selectAll();
		} catch (DALException e) {
			throw new BLLException("echec accès catalogue", e);
		}
		return catalogue;

	}

	public void addItem(Retrait ret) throws BLLException {

		if (ret.getIdArticle() != null) {
			throw new BLLException("Retrait déjà catalogué");
		}
		validerItem(ret);
		try {
			this.daoRetrait.insert(ret);
		} catch (DALException e) {
			throw new BLLException("echec insertion Article", e);
		}

	}

	public void updateItem(Retrait ret) throws BLLException {

		validerItem(ret);
		try {

			this.daoRetrait.update(ret);
		} catch (DALException e) {
			throw new BLLException("echec mise à jour Aliment", e);
		}

	}

	public void removeItem(Retrait ret) throws BLLException {

		try {
			this.daoRetrait.delete(ret);
		} catch (DALException e) {
			e.printStackTrace();
		}

	}

	public void validerItem(Retrait ret) throws BLLException {
		// à définir en fonction de l'objet sécurité supplémentaire aux Check SQL
	}

	public Retrait getItem(int index) throws BLLException {
		Retrait ret = null;

		try {
			ret = this.daoRetrait.selectByID(index);
		} catch (DALException e) {
			throw new BLLException("echec récupération I", e);
		}

		return ret;

	}

	public void nettoyerBDD() throws BLLException {
		try {
			this.daoRetrait.deleteAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
