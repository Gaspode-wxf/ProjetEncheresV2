package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Categorie;

/**
 * @author Alexandre Mchich
 *
 */
public interface DAOCategorie extends DAO<Categorie> {

	public Categorie selectByNom(String libelle) throws DALException;
	
}
