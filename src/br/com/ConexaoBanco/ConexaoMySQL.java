/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Nome do nosso pacote //                
package br.com.ConexaoBanco;

//Classes necessárias para uso de Banco de dados //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
//Início da classe de conexão//
public class ConexaoMySQL 
{
    public static String status = "Não conectou...";
 
//Método Construtor da Classe//
    public ConexaoMySQL() {
 
    }
 
//Método de Conexão//
    public static java.sql.Connection getConexaoMySQL() {
 
        Connection connection = null;          //atributo do tipo Connection

        try {

        // Carregando o JDBC Driver padrão
        String driverName = "org.postgresql.Driver";                        
        Class.forName(driverName);

        // Configurando a nossa conexão com um banco de dados//
 
            String serverName = "localhost:5432";    //caminho do servidor do BD
            String mydatabase = "AlunosCompUFSC";        //nome do seu banco de dados
            String url = "jdbc:postgresql://" + serverName + "/" + mydatabase + "?useTimezone=true&serverTimezone=UTC";
            String username = "postgres";        //nome de um usuário de seu BD      
            String password = "postgres";      //sua senha de acesso
 
            connection = DriverManager.getConnection(url, username, password);
            
            //Testa sua conexão//  
            if (connection != null) {
 
                status = ("STATUS--->Conectado com sucesso!");
 
            } else {
 
                status = ("STATUS--->Não foi possivel realizar conexão");
 
            }
            
            return connection;
        /*
        } catch (ClassNotFoundException e) {  //Driver não encontrado
 
            System.out.println("O driver expecificado nao foi encontrado.");
 
            return null;
 
        } catch (SQLException e) {
 
        //Não conseguindo se conectar ao banco
 
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
 
            return null;
 
        }
        */
        } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Erro: " + e.toString());
        }
        return null;      
    }
    
    //Método que retorna o status da sua conexão//
    public static String statusConection() {
 
        return status;
 
    }

   //Método que fecha sua conexão//
    public static boolean FecharConexao() {
 
        try {
 
            ConexaoMySQL.getConexaoMySQL().close();
 
            return true;
 
        } catch (SQLException e) {
 
            return false;
 
        }
    }

   //Método que reinicia sua conexão//
    public static java.sql.Connection ReiniciarConexao() {
 
        FecharConexao();
        return ConexaoMySQL.getConexaoMySQL();
 
    }
 
}
