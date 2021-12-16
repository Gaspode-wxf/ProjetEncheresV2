package fr.eni.projet.encheres.bo;

public class Adresse {

	/*
	 * @author William
	 */

	private String rue;
	private String codePostal;
	private String ville;

	public Adresse() {
	}

	public Adresse(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getRue() {
		return this.rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	@Override
	public String toString() {
		
		StringBuffer adresse = new StringBuffer();
		adresse.append("Adresse : ").append(this.rue + ", ").append(this.codePostal + ", ").append(this.ville);
		
		
		return adresse.toString();
	}
}
