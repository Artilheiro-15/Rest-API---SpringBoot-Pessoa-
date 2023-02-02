package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

  public static void main(String[] args) {
    System.out.println("=====================================================");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Connection conn = null;
    PreparedStatement st = null;
    try {
      conn = DB.getConnection();
       
      st =
        conn.prepareStatement(
          "INSERT INTO tabela_pessoas " +
          "(Nome, Sobrenome, Genero, Data_nascimento, Incricao, Created_at, Updated_at)" +
          "VALUES " +
          "(?, ?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS
        );
      st.setString(1, "ana");
      st.setString(2, "Vik");
      st.setString(3, "Feminino");
      st.setDate(4, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
      st.setInt(5, 1533171);
      st.setDate(6, new java.sql.Date(sdf.parse("01/12/2022").getTime()));
      st.setDate(7, new java.sql.Date(sdf.parse("02/01/2023").getTime()));


      int rowsAffected = st.executeUpdate();
      
      System.out.println("Inserido: " + rowsAffected);

      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        while (rs.next()) {
          int id = rs.getInt(1);
          System.out.println("pronto! id = " + id);
        }
      } else {
        System.out.println("No rown affected! ");
     }
    } catch (SQLException e) {
      e.printStackTrace();
      
      
    } catch (ParseException e) {
        e.printStackTrace();
     
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}