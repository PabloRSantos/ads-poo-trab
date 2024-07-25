package trabfinal;

public class Element {
    private int id;
    private String name;

    public Element(int id, String name) {
        this(name);
        this.id = id;
    }
    
    public Element(String name) {
        this.name = name;
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
    
    @Override
    public String toString() {
        String text = "Código: " + this.getId() + "\n";
        text += "Nome: " + this.getName() + "\n";
        
        return text;
    }
}
