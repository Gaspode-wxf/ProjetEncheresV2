/**
 * 
 */
package fr.eni.projet.encheres.bo.user;

import fr.eni.projet.encheres.bo.Adresse;

/**
 * @author William
 *
 */
public abstract class Utilisateur {

	private Integer id;
	private String nom;
	private String prenom;
	private String pseudo;
	private String telephone;
	private String email;
	private Adresse adresse;
	private String mdp;
	private boolean administrateur;

	public Utilisateur() {
		super();
		this.administrateur = false;
	}

	protected Utilisateur(boolean administrateur) {
		super();
		this.administrateur = administrateur;
	}

	public Utilisateur(String nom, String prenom, String pseudo,String mdp, String email, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.setMdp(mdp);
		this.email = email;
		this.adresse = adresse;
		this.administrateur = false;
	}

	public Utilisateur(Integer id, String nom, String prenom, String pseudo,String mdp, String email, Adresse adresse) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.setMdp(mdp);
		this.email = email;
		this.adresse = adresse;
		this.administrateur = false;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public boolean isAdministrateur() {
		return this.administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
@Override
public String toString() {
	StringBuffer user = new StringBuffer();
	user.append(this.pseudo).append(this.adresse);
	return user.toString();
}
}
