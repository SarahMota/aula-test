package com.example.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class postgres {
    /**
     * @param args
     */
    public static void main( String [] args) {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ConectaES", "postgres", "root");
            if (conexao != null) {
                System.out.println("Banco de dados conectado com sucesso!");
                Statement stm = conexao.createStatement();
                insereDados(stm);
                consultaDados(stm);
                conexao.close();
            }else{
                System.out.println("Conexão falhou!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void consultaDados(Statement stm) {
        String sql = "select id, nome from dados_basicos";
        try {
            ResultSet result = stm.executeQuery(sql);
            while (result.next()) {
                System.out.println("id: " + result.getInt("id") + ", Nome: " + result.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   

    static void insereDados(Statement stm) {
        String sql = "insert into dados_basicos (nome) values ('Sarah')";
        try {
             stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

