package fr.eni.projet.encheres.dal;

import fr.eni.projet.encheres.bo.Adresse;
import fr.eni.projet.encheres.bo.user.Utilisateur;
import fr.eni.projet.encheres.bo.user.Vendeur;

public  interface DAOUtilisateur extends DAO<Utilisateur> {

	/**
	 * @author William "Gaspode" Freyer
	 *
	 */
	
	public Adresse recupAdresse(Integer id) throws DALException;
	public Vendeur SelectByPseudo(String pseudo) throws DALException;
}
