package trabfinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ElementDAO {
    public int insert(Element element) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO elements(name) "
                    + "VALUES(?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, element.getName());

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
    
    public Element read(int id) {
         try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM elements WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                
                return new Element(id, name);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return null;
    }
    
    public ArrayList<Element> list() {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM elements");
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
    
    public int update(Element element) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "UPDATE elements "
                    + "SET name = ? "
                    + "WHERE id = ?");
            ps.setString(1, element.getName());
            ps.setInt(2, element.getId());

            int rowCount = ps.executeUpdate();
            
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
    
    public int delete(int id) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM elements WHERE id = ?");
            ps.setInt(1, id);

            int rowCount = ps.executeUpdate();
            
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
}
