package trabfinal;

import java.sql.Connection;
import java.sql.SQLException;

//CREATE DATABASE poo_pablo;
//
//USE poo_pablo;
//
//CREATE TABLE masters (
//	id INTEGER PRIMARY KEY AUTO_INCREMENT,
//	name VARCHAR(256) NOT NULL,
//	team VARCHAR(100),
//	birthday_date DATE,
//	level INTEGER
//);
//
//CREATE TABLE pokemons (
//	id INTEGER PRIMARY KEY AUTO_INCREMENT,
//	name VARCHAR(256) NOT NULL,
//	description TEXT,
//	height DECIMAL(5, 2),
//	weight DECIMAL(5, 2),
//	generation INTEGER NOT NULL,
//	master_id INTEGER,
//	FOREIGN KEY(master_id) REFERENCES masters(id) ON DELETE SET NULL
//);
//
//CREATE TABLE elements (
//	id INTEGER PRIMARY KEY AUTO_INCREMENT,
//	name VARCHAR(256) NOT NULL
//);
//
//CREATE TABLE pokemons_elements (
//	pokemon_id INTEGER,
//	element_id INTEGER,
//	PRIMARY KEY(pokemon_id, element_id),
//	FOREIGN KEY(pokemon_id) REFERENCES pokemons(id) ON DELETE CASCADE,
//	FOREIGN KEY(element_id) REFERENCES elements(id) ON DELETE CASCADE
//);

public class DbConnection {
    static Connection conn = null;

     public static Connection getConnection() {
        String serverName = "localhost";
        String database = "poo_pablo";
        String url = "jdbc:mysql://" + serverName + "/" + database;
        String username = "root";
        String password = "password";
        
        if(conn != null) {
            return conn;
        }
        
        try {
            conn = java.sql.DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
