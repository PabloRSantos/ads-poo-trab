package trabfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PokemonDAO {
    public int insert(Pokemon pokemon) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO pokemons(name, description, height, weight, generation, master_id) "
                    + "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, pokemon.getName());
            ps.setString(2, pokemon.getDescription());
            ps.setFloat(3, pokemon.getHeight());
            ps.setFloat(4, pokemon.getWeight());
            ps.setInt(5, pokemon.getGeneration());
            ps.setInt(6, pokemon.getMasterId());

            int rowCount = ps.executeUpdate();
            if(rowCount == 0) {
                return rowCount;
            }
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
    
    public Pokemon read(int id) {
         try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pokemons WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                int generation = rs.getInt("generation");
                int masterId = rs.getInt("master_id");
                
                return new Pokemon(id, name, description, height, weight, generation, masterId);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
        return null;
    }
    
    public ArrayList<Pokemon> list() {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pokemons");
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                int generation = rs.getInt("generation");
                int masterId = rs.getInt("master_id");
                Pokemon pokemon = new Pokemon(id, name, description, height, weight, generation, masterId);
                
                pokemons.add(pokemon);
            }
            
            return pokemons;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return null;
    }
    
    public ArrayList<Pokemon> listByMaster(int masterId) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pokemons WHERE master_id = ?");
            ps.setInt(1, masterId);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float height = rs.getFloat("height");
                float weight = rs.getFloat("weight");
                int generation = rs.getInt("generation");
                Pokemon pokemon = new Pokemon(id, name, description, height, weight, generation, masterId);
                
                pokemons.add(pokemon);
            }
            
            return pokemons;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return null;
    }
    
    public int update(Pokemon pokemon) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "UPDATE pokemons "
                    + "SET name = ?, description = ?, height = ?, weight = ?, generation = ?, master_id = ? "
                    + "WHERE id = ?");
            ps.setString(1, pokemon.getName());
            ps.setString(2, pokemon.getDescription());
            ps.setFloat(3, pokemon.getHeight());
            ps.setFloat(4, pokemon.getWeight());
            ps.setInt(5, pokemon.getGeneration());
            ps.setInt(6, pokemon.getMasterId());
            ps.setInt(7, pokemon.getId());

            int rowCount = ps.executeUpdate();
            
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        }
        
        return 0;
    }
    
    public int delete(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM pokemons WHERE id = ?");
            ps.setInt(1, id);

            int rowCount = ps.executeUpdate();
            
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
}
