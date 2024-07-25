package trabfinal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MasterDAO {
    public int insert(Master master) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "INSERT INTO masters(name, team, birthday_date, level) "
                    + "VALUES(?, ?, ?, ?)"
            );
            ps.setString(1, master.getName());
            ps.setString(2, master.getTeam());
            ps.setDate(3, new Date(master.getBirthdayDate().getTime()));
            ps.setInt(4, master.getLevel());

            int rowCount = ps.executeUpdate();
            
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
    
    public Master read(int id) {
         try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM masters WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                String team = rs.getString("team");
                Date birthdayDate = rs.getDate("birthday_date");
                int level = rs.getInt("level");
                
                return new Master(id, name, team, birthdayDate, level);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return null;
    }
    
    public ArrayList<Master> list() {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM masters");
            ResultSet rs = ps.executeQuery();
            
            ArrayList<Master> masters = new ArrayList<Master>();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String team = rs.getString("team");
                Date birthdayDate = rs.getDate("birthday_date");
                int level = rs.getInt("level");
                Master master = new Master(id, name, team, birthdayDate, level);
                
                masters.add(master);
            }
            
            return masters;
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
        
        return null;
    }
    
    public int update(Master master) {
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(""
                    + "UPDATE masters "
                    + "SET name = ?, team = ?, level = ? "
                    + "WHERE id = ?");
            ps.setString(1, master.getName());
            ps.setString(2, master.getTeam());
            ps.setInt(3, master.getLevel());
            ps.setInt(4, master.getId());

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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM masters WHERE id = ?");
            ps.setInt(1, id);

            int rowCount = ps.executeUpdate();
            
            return rowCount;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
}
