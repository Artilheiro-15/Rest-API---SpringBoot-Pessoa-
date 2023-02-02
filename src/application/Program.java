package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		System.out.println("=====================================================");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();

			st = conn.createStatement();

			rs = st.executeQuery("select * from tabela_pessoas");

			while (rs.next()) {
				System.out.println(rs.getInt("Id_Pessoa") + ", " + rs.getString("Nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
			System.out.println("=================================================");
		}
	}
}