package fr.eni.projet.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projet.encheres.bo.Adresse;
import fr.eni.projet.encheres.bo.user.Administrateur;
import fr.eni.projet.encheres.bo.user.Utilisateur;
import fr.eni.projet.encheres.bo.user.Vendeur;
import fr.eni.projet.encheres.dal.ConnectionProvider;
import fr.eni.projet.encheres.dal.DALException;
import fr.eni.projet.encheres.dal.DAOUtilisateur;

/**
 * @author William "Gaspode" Freyer
 *
 */

public class UtilisateurJdbcImpl extends DAOJdbcImpl<Utilisateur> implements DAOUtilisateur {

	String sqlDeleteByID = "delete from UTILISATEURS where id=?";
	String sqlInsert = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur) values (?,?,?,?,?,?,?,?,?,?,?)";
	String sqlSelectByID = "select id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur from UTILISATEURS where id=?";
	String sqlSelectAll = "select id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur from UTILISATEURS";
	String sqlUpdate = "update UTILISATEURS set pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, codePostal=?, ville=?, motDePasse=?, credit=?, administrateur=? where id=? ";
	String sqlTruncate = "truncate table UTILISATEURS";
	String sqlSelectByPseudo = "select id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur from UTILISATEURS where pseudo like ?";


	public UtilisateurJdbcImpl() {
		setSqlDeleteByID(sqlDeleteByID);
		setSqlSelectAll(sqlSelectAll);
		setSqlSelectByID(sqlSelectByID);
		setSqlTruncate(sqlTruncate);
		setSqlUpdate(sqlUpdate);
	}
	
	@Override
	public Vendeur SelectByPseudo(String pseudo) throws DALException {
		String sql = sqlSelectByPseudo;
		Vendeur ven = null;
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			System.out.println(pseudo);
			stmt.setString(1, pseudo);
			try (ResultSet rs = stmt.executeQuery();) {

				
				if(rs.next()) {
				ven = (Vendeur) createFromRS(rs);
				System.out.println(ven);
				}
				else {
					System.err.println("vendeur non trouvé");
				}
			}

		} catch (SQLException e) {

			throw new DALException("erreur de requete Select by Pseudo", e);
		}

		return ven;
	}



//	@Override
//	public List<Utilisateur> selectAll() throws DALException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Utilisateur selectByID(Integer id) throws DALException {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public void update(Utilisateur t) throws DALException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteByID(Integer id) throws DALException {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void delete(Utilisateur t) throws DALException {
		this.deleteByID(t.getId());

	}

	@Override
	public void insert(Utilisateur t) throws DALException {
		String sql = sqlInsert;
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				ResultSet rs = null;) {
			completeStmt(t, stmt);
			stmt.execute();
			t.setId(JdbcTools.recupID(stmt, rs));
		} catch (SQLException e) {
			throw new DALException("erreur de requete Insert", e);

		}
	}

//	@Override
//	public void deleteAll() throws DALException {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public void completeStmt(Utilisateur t, PreparedStatement stmt) throws SQLException {
		/*
		 * ordre des attributs pseudo, nom, prenom, email, telephone, rue, codePostal,
		 * ville, motDePasse, credit, administrateur
		 */
		int index = 1;
		stmt.setString(index++, t.getPseudo());
		stmt.setString(index++, t.getNom());
		stmt.setString(index++, t.getPrenom());
		stmt.setString(index++, t.getEmail());
		if (t.getTelephone() != null) {
			stmt.setString(index++, t.getTelephone());
		} else {
			stmt.setString(index++, "");
		}
		stmt.setString(index++, t.getAdresse().getRue());
		stmt.setString(index++, t.getAdresse().getCodePostal());
		stmt.setString(index++, t.getAdresse().getVille());
		stmt.setString(index++, t.getMdp());
		stmt.setInt(index++, ((Vendeur) t).getCredit());
		stmt.setBoolean(index++, t.isAdministrateur());

		if (t.getId() != null) {
			stmt.setInt(index++, t.getId());
		}

	}

	@Override
	public Utilisateur createFromRS(ResultSet rs) throws SQLException {

		Utilisateur user;

		Adresse adresse = new Adresse(rs.getString("rue"), rs.getString("codePostal"), rs.getString("ville"));

		if (rs.getBoolean("administrateur") == true) {
			user = new Administrateur();
			((Administrateur) user).donnerDroitsAdmin(user);
		} else {
			user = new Vendeur();
		}
		user.setId(rs.getInt("id"));
		user.setNom(rs.getString("nom"));
		user.setPrenom(rs.getString("prenom"));
		user.setPseudo(rs.getString("pseudo"));

		if (rs.getString("telephone") == null) {
			user.setTelephone("");
		} else {
			user.setTelephone(rs.getString("telephone"));
		}

		user.setEmail(rs.getString("email"));
		user.setAdresse(adresse);
		((Vendeur) user).setCredit(rs.getInt("credit"));
		user.setMdp(rs.getString("motDePasse"));

		return user;
	}

	@Override
	public Adresse recupAdresse(Integer id) throws DALException {
		Utilisateur user = selectByID(id);
		
		return user.getAdresse();
	}

}
