package fr.eni.projet.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.encheres.dal.ConnectionProvider;
import fr.eni.projet.encheres.dal.DALException;

/**
 * @author William "Gaspode" Freyer
 *
 */
public abstract class DAOJdbcImpl<T> implements DAOJdbc<T> {

	public DAOJdbcImpl() {
	}

	private String sqlSelectAll;
	private String sqlSelectByID;
	private String sqlUpdate;
	private String sqlDeleteByID;
	private String sqlTruncate;

	public String getSqlSelectAll() {
		return this.sqlSelectAll;
	}

	public void setSqlSelectAll(String sqlSelectAll) {
		this.sqlSelectAll = sqlSelectAll;
	}

	public String getSqlSelectByID() {
		return this.sqlSelectByID;
	}

	public void setSqlSelectByID(String sqlSelectByID) {
		this.sqlSelectByID = sqlSelectByID;
	}

	public String getSqlUpdate() {
		return this.sqlUpdate;
	}

	public void setSqlUpdate(String sqlUpdate) {
		this.sqlUpdate = sqlUpdate;
	}

	public String getSqlDeleteByID() {
		return this.sqlDeleteByID;
	}

	public void setSqlDeleteByID(String sqlDeleteByID) {
		this.sqlDeleteByID = sqlDeleteByID;
	}

	public String getSqlTruncate() {
		return this.sqlTruncate;
	}

	public void setSqlTruncate(String sqlTruncate) {
		this.sqlTruncate = sqlTruncate;
	}

	@Override
	public List<T> selectAll() throws DALException {

		String sql = sqlSelectAll;
		List<T> liste = new ArrayList<T>();
		T t = null;
		try (Connection con = ConnectionProvider.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {

				t = createFromRS(rs);
				liste.add(t);
			}
		} catch (SQLException e) {
			throw new DALException("erreur de requete Select All", e);
		}
		return liste;
	}

	@Override
	public T selectByID(Integer id) throws DALException {
		String sql = sqlSelectByID;
		T a = null;
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {

			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery();) {

				rs.next();

				a = createFromRS(rs);
			}

		} catch (SQLException e) {

			throw new DALException("erreur de requete Select by ID", e);
		}

		return a;
	}

	@Override
	public void update(T a) throws DALException {
		String sql = sqlUpdate;

		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			completeStmt(a, stmt);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("erreur de mise à jour", e);
		}

	}

	@Override
	public void deleteByID(Integer id) throws DALException {
		String sql = sqlDeleteByID;

		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("erreur de suppression", e);
		}
	}

	@Override
	public void deleteAll() throws DALException {
		String sql = sqlTruncate;
		try (Connection con = ConnectionProvider.getConnection(); Statement stmt = con.createStatement();) {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DALException("erreur de suppression globale", e);
		}

	}

}