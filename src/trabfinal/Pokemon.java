package trabfinal;

public class Pokemon {
    private int id;
    private String name;
    private String description;
    private float height;
    private float weight;
    private int generation;
    private int masterId;

    public Pokemon(int id, String name, String description, float height, float weight, int generation, int masterId) {
        this(name, description, height, weight, generation);
        this.id = id;
        this.masterId = masterId;
    }
    
    public Pokemon(String name, String description, float height, float weight, int generation) {
        this.name = name;
        this.description = description;
        this.height = height;
        this.weight = weight;
        this.generation = generation;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }
    
    @Override
    public String toString() {
        String text = "Código: " + this.getId() + "\n";
        text += "Nome: " + this.getName() + "\n";
        text += "Descrição: " + this.getDescription() + "\n";
        text += "Altura: " + this.getHeight() + "\n";
        text += "Peso: " + this.getWeight() + "\n";
        text += "Geração: " + this.getGeneration() + "\n";
        
        return text;
    }
}
