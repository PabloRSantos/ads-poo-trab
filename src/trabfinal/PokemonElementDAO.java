package trabfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonElementDAO {

    public int insert(int pokemonId, int elementId) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO pokemons_elements(pokemon_id, element_id) "
                    + "VALUES(?, ?)");
            ps.setInt(1, pokemonId);
            ps.setInt(2, elementId);

            int rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }

    public int delete(int pokemonId, int elementId) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "DELETE FROM pokemons_elements "
                    + "WHERE pokemon_id = ? AND element_id = ?");
            ps.setInt(1, pokemonId);
            ps.setInt(2, elementId);

            int rowCount = ps.executeUpdate();
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }

    public ArrayList<Pokemon> listPokemonsByElement(int elementId) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT pokemons.* FROM pokemons_elements "
                    + "INNER JOIN pokemons ON pokemons.id = pokemons_elements.pokemon_id "
                    + "WHERE pokemons_elements.element_id = ?");
            ps.setInt(1, elementId);
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
    
    public ArrayList<Element> listElementsByPokemon(int pokemonId) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT elements.* FROM pokemons_elements "
                    + "INNER JOIN elements ON elements.id = pokemons_elements.element_id "
                    + "WHERE pokemons_elements.pokemon_id = ?");
            ps.setInt(1, pokemonId);
            ResultSet rs = ps.executeQuery();

            ArrayList<Element> elements = new ArrayList<Element>();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Element element = new Element(id, name);
                
                elements.add(element);
            }

            return elements;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }
}
