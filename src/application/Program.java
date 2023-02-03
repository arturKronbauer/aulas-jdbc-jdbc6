package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentID = 1");
			
		/*	int x = 1;
			if (x < 2) {
				throw new SQLException("Erro Falso");
			} */
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentID = 2");
			
			conn.commit();
			
			System.out.println("rows1 "+rows1);
			System.out.println("rows2 "+rows2);
		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transação retornada ao original: "+e.getMessage());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new DbException("Erro ao ser retornada a transação: "+e.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
