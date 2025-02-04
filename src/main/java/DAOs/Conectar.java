package DAOs;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class Conectar {
    private static final String URL = "jdbc:mysql://localhost:3306/pews"; // Substitua "seuBanco" pelo nome do banco
    private static final String USER = "root"; // Substitua "root" pelo seu usuário
    private static final String PASSWORD = "root"; // Substitua "suaSenha" pela sua senha

    public static Connection getConnection() throws SQLException {
        // Adiciona a verificação do driver apenas para garantir
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC não encontrado. Adicione o MySQL Connector ao classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}