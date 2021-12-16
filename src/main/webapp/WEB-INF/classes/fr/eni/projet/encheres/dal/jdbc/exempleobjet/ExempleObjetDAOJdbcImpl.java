package fr.eni.projet.encheres.dal.jdbc.exempleobjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projet.encheres.bo.ExempleObjet;
import fr.eni.projet.encheres.dal.ConnectionProvider;
import fr.eni.projet.encheres.dal.DALException;
import fr.eni.projet.encheres.dal.DAOExempleObjet;
import fr.eni.projet.encheres.dal.jdbc.DAOJdbcImpl;
import fr.eni.projet.encheres.dal.jdbc.JdbcTools;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class ExempleObjetDAOJdbcImpl extends DAOJdbcImpl<ExempleObjet> implements DAOExempleObjet {

	String sqlDeleteByID = "delete from XXXXX where id=?";
	String sqlInsert = "insert into XXXXXX(XXXXX, XXXXXXX) values (?,?)";
	String sqlSelectByID = "select XXXX, XXXXX, XXXXX from XXXXXXX where id=?";
	String sqlSelectAll = "select XXXX, XXXXX, XXXXXX from XXXXX";
	String sqlUpdate = "update XXXXX set XXXXX=?, XXXXXX=?, where XXXXXX=? ";
	String sqlTruncate = "truncate table XXXXXXX";

	public ExempleObjetDAOJdbcImpl() {
		setSqlDeleteByID(sqlDeleteByID);
		setSqlSelectAll(sqlSelectAll);
		setSqlSelectByID(sqlSelectByID);
		setSqlTruncate(sqlTruncate);
		setSqlUpdate(sqlUpdate);
	}

	@Override
	public void completeStmt(ExempleObjet exOb, PreparedStatement stmt) throws SQLException {

//		int index = 1;
//		stmt.setString(index++, t.getNom());
//		stmt.setInt(index++, t.getIdRepas());
		
		// ajouter l'id pour update
//		if(exOb.getId()!=null) {
//			stmt.setInt(index++, exOb.getId());
//		}
	}

	@Override
	public ExempleObjet createFromRS(ResultSet rs) throws SQLException {
		ExempleObjet exOb = new ExempleObjet();
		exOb.setId(rs.getInt(1));
//		al.setNom(rs.getString(2));
//		al.setIdRepas(3);
		return exOb;
	}

	@Override
	public void delete(ExempleObjet exOb) throws DALException {
		this.deleteByID(exOb.getId());

	}

	@Override
	public void insert(ExempleObjet exOb) throws DALException {
		String sql = sqlInsert;
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				ResultSet rs = null;) {
			completeStmt(exOb, stmt);
			stmt.execute();
			exOb.setId(JdbcTools.recupID(stmt, rs));
		} catch (SQLException e) {
			throw new DALException("erreur de requete Insert", e);

		}
	}

}
