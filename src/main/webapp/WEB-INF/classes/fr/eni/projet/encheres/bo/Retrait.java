package fr.eni.projet.encheres.bo;

/**
 * @author Alexandre Mchich
 * 
 * 
 * @update by William :  Ajout
 *         d'un constructeur vide Modification nomemclature
 */

public class Retrait {

	private Article article;
	private Adresse adresse;

	public Retrait() {

	}

	public Retrait(Adresse adresse, Article article) {
		this.article = article;
		this.adresse = adresse;
	}

	public Integer getIdArticle() {

		return this.article.getId();
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	public Article getArticle() {
		return this.article;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}		

	
	@Override
		public String toString() {
			StringBuffer retrait = new StringBuffer("Retrait Article ");
			retrait.append(this.article.getNomArticle()+ " ");
			retrait.append("Adresse : " + this.adresse);
			return retrait.toString();
	}



}