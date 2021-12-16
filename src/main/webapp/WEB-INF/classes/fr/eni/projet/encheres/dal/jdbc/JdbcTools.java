package fr.eni.projet.encheres.dal.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author William "Gaspode" Freyer
 *
 */
public class JdbcTools {

	public static Integer recupID(PreparedStatement stmt, ResultSet rs) throws SQLException {
		rs = stmt.getGeneratedKeys();
		rs.next();
		Integer id = rs.getInt(1);
		return id;
	}

}
