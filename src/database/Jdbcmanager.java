package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbcmanager {
Connection conn=null ;
private static final String database= "syssgbanking"; // Database name
private static final String usr = "root"; // MySQL username
private static final String pwd = "";  // MySQL password
private static final String url = "jdbc:mysql://127.0.0.1/"; //url server 
private static final String certifcates = "?verifyServerCertificate=false&useSSL=true"; //certificate

	public static Connection ConnexionDb(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn =DriverManager.getConnection(url+""+database+""+certifcates,usr,pwd);
			System.out.println("Connexion reussie to database "+database+"...");

			return conn;
		}catch(SQLException e){
			if(e.getErrorCode()==1049){
//				System.out.println("No database creation tables");
				onCreateDataBase();
				return null;
			}
			else if(e.getErrorCode()==1045){
				System.out.println("Acces interdit, verifiez mot de passe et nom d'utilisateur de votre base de donnees...");
				return null;
			}
			else{
            //check connexion to create database and other tables		
				System.err.println("Connexion echouee to database:"+database+",verifiez que vous avez MYQSL v.8 minimum installe sur votre poste...");	
				return null;
			}
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC driver not found...");
			return null;
		}
	}
	
	public static void onCreateDataBase(){
		System.out.println("Creating database...");
		try{		    	  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn =DriverManager.getConnection(url+""+certifcates,usr,pwd);
			Statement stmt = conn.createStatement();
				System.out.println("Must create database...");
				 stmt.executeUpdate("CREATE DATABASE "+database+";");
		         System.out.println("Database created...");
		         conn.close();
		         stmt.close();
		         //create tables//
		         oncreateClient();
	      } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	      }
	}
	
	
	
	public static void oncreateCompte(){
		  try{		    	  
	    	  Connection cc= ConnexionDb();
	    	  Statement stmt = cc.createStatement();
	    	  
	          String sqlx = String.join(" ","CREATE TABLE IF NOT EXISTS comptes (",
	            "ID int NOT NULL AUTO_INCREMENT,",
	            "noCompte varchar(255) NOT NULL UNIQUE,",
	            "type varchar(60) NOT NULL,",
	            "creele date NOT NULL,",
	            "solde float NOT NULL,",
	            "monnaie varchar(65) NOT NULL,",
	            "etat varchar(60) NOT NULL,",
	            "idclient int NOT NULL,",
	            "FOREIGN KEY(idclient) references CLIENTS(ID),",
	           "PRIMARY KEY (ID)",
	          ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");
	            stmt.executeUpdate(sqlx);
//	            | NoCompte   | Type  |  Cree le |  Solde  | Monnaie  |    Etat    |    Client       |
//	         System.out.println(sqlx); 
	         cc.close();
	         stmt.close();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
	
	
	public static void oncreateClient(){
		  try{		    	  
	    	  Connection cc= ConnexionDb();
	    	  Statement stmt = cc.createStatement();
	          String sqlx = String.join(" ","CREATE TABLE IF NOT EXISTS clients (",
	            "ID int NOT NULL AUTO_INCREMENT,",
	            "Client varchar(255) NOT NULL,",
	            "creele date NOT NULL,",
	            "adresse varchar(255) NOT NULL,",
	            "telephone varchar(65) NOT NULL,",
	            "succursale int NOT NULL,",
	            "statusMatrimonial varchar(50) NOT NULL,",
	            "enfants int NOT NULL,",
	           "PRIMARY KEY (ID)",
	          ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");
	            stmt.executeUpdate(sqlx);
//	         System.out.println(sqlx); 
	         cc.close();
	         stmt.close();
	         oncreateCompte();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	}
}
//drop database syssgbanking;
