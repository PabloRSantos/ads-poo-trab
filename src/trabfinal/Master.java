package trabfinal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Master {
    private int id;
    private String name;
    private String team;
    private Date birthdayDate;
    private int level;

    public Master(int id, String name, String team, Date birthdayDate, int level) {
        this(name, team, birthdayDate, level);
        this.id = id;

    }
    
    public Master(String name, String team, Date birthdayDate, int level) {
        this.name = name;
        this.team = team;
        this.birthdayDate = birthdayDate;
        this.level = level;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        
        String text = "Código: " + this.getId() + "\n";
        text += "Nome: " + this.getName() + "\n";
        text += "Time: " + this.getTeam() + "\n";
        text += "Data de nascimento: " + dateFormatter.format(this.getBirthdayDate()) + "\n";
        text += "Nivel: " + this.getLevel() + "\n";
        
        return text;
    }
}
