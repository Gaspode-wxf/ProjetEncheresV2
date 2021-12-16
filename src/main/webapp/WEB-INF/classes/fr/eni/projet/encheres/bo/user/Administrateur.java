/**
 * 
 */
package fr.eni.projet.encheres.bo.user;

import fr.eni.projet.encheres.bo.Adresse;

/**
 * @author William Freyer
 *
 */
public class Administrateur extends Vendeur {

	public Administrateur() {
		this.setAdministrateur(true);
	}

	public Administrateur(String nom, String prenom, String pseudo, String mdp, String email, Adresse adresse) {
		super(nom, prenom, pseudo, mdp, email, adresse);
		this.setAdministrateur(true);
	}

	public Administrateur(Integer id, String nom, String prenom, String pseudo, String mdp, String email, Adresse adresse) {
		super(id, nom, prenom, pseudo, mdp, email, adresse);
		this.setAdministrateur(true);
	}

	public void donnerDroitsAdmin(Utilisateur user) {
		user.setAdministrateur(true);
	}

}
