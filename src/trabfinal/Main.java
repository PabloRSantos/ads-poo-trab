package trabfinal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    static int menuControl = 0;
    static int subMenuControl = 0;
    
    public static void main(String[] args) {
        do {
            System.out.println("---------- Menu ----------");
            System.out.println("0. Sair");
            System.out.println("1. Pokemons");
            System.out.println("2. Mestres");
            System.out.println("3. Elementos");
            menuControl = sc.nextInt();
            
            do {
                switch(menuControl) {
                    case 1:
                        pokemonsMenu();
                        break;
                    case 2:
                        mastersMenu();
                        break;
                    case 3:
                        elementsMenu();
                        break;
                }
            } while(menuControl != 0 && subMenuControl != 0);
        } while(menuControl != 0);        
    }
    
    public static void pokemonsMenu() {
        PokemonDAO pokemonDAO = new PokemonDAO();
        PokemonElementDAO pokemonElementDAO = new PokemonElementDAO();
        MasterDAO masterDAO = new MasterDAO();
        ElementDAO elementDAO = new ElementDAO();
        
        System.out.println("\n---------- Submenu ----------");
        System.out.println("0. Sair");
        System.out.println("1. Listar pokemons");
        System.out.println("2. Buscar pokemon");
        System.out.println("3. Inserir pokemon");
        System.out.println("4. Atualizar pokemon");
        System.out.println("5. Deletar pokemon");
        subMenuControl = sc.nextInt();
        
        switch(subMenuControl) {
            case 1: { 
                ArrayList<Pokemon> pokemons = pokemonDAO.list();
                
                for(int i = 0; i < pokemons.size(); i++) {
                    System.out.println(pokemons.get(i).toString());
                }
                break;
            }
            
            case 2: {
                System.out.println("Digite o código do pokemon que você deseja buscar");
                int id = sc.nextInt();
                
                Pokemon pokemon = pokemonDAO.read(id);
                if(pokemon != null) {
                    System.out.println("Pokemon encontrado!");
                    System.out.println(pokemon.toString());
                    
                    int masterId = pokemon.getMasterId();
                    if(masterId != 0) {
                        Master master = masterDAO.read(masterId);
                        
                        System.out.println("\nMestre do pokemon:");
                        System.out.println(master.toString());
                    }
                    
                    ArrayList<Element> elements = pokemonElementDAO.listElementsByPokemon(pokemon.getId());
                    for(int i = 0; i < elements.size(); i++) {
                        if(i == 0) {
                            System.out.println("\nElementos do pokemon:");
                        }
                        
                        Element element = elements.get(i);
                        System.out.println(element.toString());
                    }
                } else {
                    System.out.println("Pokemon não encontrado");
                }
                break;
            }
            
            case 3: {
                System.out.println("Digite o nome do pokemon:");
                String name = sc.next();
                
                System.out.println("Digite a descrição do pokemon:");
                String description = sc.next();
                
                
                System.out.println("Digite a altura do pokemon:");
                float height = sc.nextFloat();
                
                System.out.println("Digite o peso do pokemon:");
                float weight = sc.nextFloat();
                
                System.out.println("Digite a geração do pokemon:");
                int generation = sc.nextInt();
                
                Pokemon pokemon = new Pokemon(name, description, height, weight, generation);

                System.out.println("O pokemon tem um mestre?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                int hasMaster = sc.nextInt();
                if(hasMaster == 1) {
                    System.out.println("Digite o código do mestre: ");
                    int masterId = sc.nextInt();
                    
                    if(masterDAO.read(masterId) == null) {
                        System.out.println("Mestre não existe");
                        break;
                    }
                    pokemon.setMasterId(masterId);
                }
                
                int pokemonId = pokemonDAO.insert(pokemon);
                if (pokemonId > 0) {
                    System.out.println("Quantos elementos tem o pokemon? Mínimo de 1 elemento");
                    int elementsLength = sc.nextInt();
                    
                    if(elementsLength < 1) {
                        System.out.println("O pokemon deve ter pelo menos 1 elemento");
                        elementsLength = 1;
                    }
                    
                    for(int i = 0; i < elementsLength; i++) {
                        System.out.println("Digite o código do " + (i + 1) +  " elemento:");
                        int elementId = sc.nextInt();
                        
                        if(elementDAO.read(elementId) == null) {
                            System.out.println("Elemento não existe");
                            continue;
                        }
                        pokemonElementDAO.insert(pokemonId, elementId);
                    }
                    
                    System.out.println("Registro criado");
                } else {
                    System.out.println("Registro não criado");
                }
                break;
            }
            
            case 4: {
                System.out.println("Digite o código do pokemon que você deseja atualizar");
                int id = sc.nextInt();
                
                Pokemon pokemon = pokemonDAO.read(id);
                if(pokemon == null) {
                    System.out.println("Pokemon não encontrado");
                    break;
                }
                
                System.out.println("Você deseja atualizar o nome do pokemon?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                int control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite o nome");
                    pokemon.setName(sc.next());
                }
                
                System.out.println("Você deseja atualizar a descrição do pokemon?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite a descrição");
                    pokemon.setDescription(sc.next());
                }
                
                System.out.println("Você deseja atualizar a altura do pokemon?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite a altura");
                    pokemon.setHeight(sc.nextFloat());
                }
                
                System.out.println("Você deseja atualizar o peso do pokemon?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite o peso");
                    pokemon.setWeight(sc.nextFloat());
                }
                
                System.out.println("Você deseja atualizar a geração do pokemon?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite a geração");
                    pokemon.setGeneration(sc.nextInt());
                }

                System.out.println("Você deseja atualizar o mestre do pokemon?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite o código do mestre: ");
                    pokemon.setMasterId(sc.nextInt());
                }
                
                int result = pokemonDAO.update(pokemon);
                if (result > 0) {
                    System.out.println("Registro atualizado");
                } else {
                    System.out.println("Registro não atualizado");
                }
                break;
            }
            
            case 5: {
                System.out.println("Digite o código do pokemon que você deseja deletar");
                int id = sc.nextInt();
                
                Pokemon pokemon = pokemonDAO.read(id);
                if(pokemon == null) {
                    System.out.println("Pokemon não existe");
                    break;
                }
                
                int result = pokemonDAO.delete(id);
                if(result > 0) {
                    System.out.println("Pokemon deletado com sucesso!");
                } else {
                    System.out.println("Erro ao deletar pokemon");
                }
                break;
            }
        }
    }
    
    public static void mastersMenu() {
        PokemonDAO pokemonDAO = new PokemonDAO();
        MasterDAO masterDAO = new MasterDAO();
        
        System.out.println("\n---------- Submenu ----------");
        System.out.println("0. Sair");
        System.out.println("1. Listar mestres");
        System.out.println("2. Buscar mestre");
        System.out.println("3. Inserir mestre");
        System.out.println("4. Atualizar mestre");
        System.out.println("5. Deletar mestre");
        subMenuControl = sc.nextInt();
        
        switch(subMenuControl) {
            case 1: { 
                ArrayList<Master> masters = masterDAO.list();
                
                for(int i = 0; i < masters.size(); i++) {
                    System.out.println(masters.get(i).toString());
                }
                break;
            }
            
            case 2: {
                System.out.println("Digite o código do mestre que você deseja buscar");
                int id = sc.nextInt();
                
                Master master = masterDAO.read(id);
                if(master != null) {
                    System.out.println("Mestre encontrado!");
                    System.out.println(master.toString());
                    
                    ArrayList<Pokemon> pokemons = pokemonDAO.listByMaster(id);
                    for(int i = 0; i < pokemons.size(); i++) {
                        if(i == 0) {
                            System.out.println("\nPokemons do mestre:");
                        }
                        
                        Pokemon pokemon = pokemons.get(i);
                        System.out.println(pokemon.toString());
                    }
                } else {
                    System.out.println("Mestre não encontrado");
                }
                break;
            }
            
            case 3: {
                System.out.println("Digite o nome do mestre:");
                String name = sc.next();
                
                System.out.println("Digite o time do mestre:");
                String team = sc.next();
                
                System.out.println("Digite a data de nascimento do mestre: (dd/mm/yyyy)");
                Date birthdayDate;
                try {
                    birthdayDate = new SimpleDateFormat("dd/MM/yyyy").parse(sc.next());
                } catch (ParseException ex) {
                    System.out.println("Data inválida");
                    break;
                }
                
                System.out.println("Digite o nivel do mestre:");
                int level = sc.nextInt();
                
                Master master = new Master(name, team, birthdayDate, level);
                int result = masterDAO.insert(master);
                if (result > 0) {
                    System.out.println("Registro criado");
                } else {
                    System.out.println("Registro não criado");
                }
                break;
            }
   
            case 4: {
                System.out.println("Digite o código do mestre que você deseja atualizar");
                int id = sc.nextInt();
                
                Master master = masterDAO.read(id);
                if(master == null) {
                    System.out.println("Mestre não encontrado");
                    break;
                }
                
                System.out.println("Você deseja atualizar o nome do mestre?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                int control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite o nome");
                    master.setName(sc.next());
                }
                
                System.out.println("Você deseja atualizar o time do mestre?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite o time");
                    master.setTeam(sc.next());
                }
                
                System.out.println("Você deseja atualizar o nivel do mestre?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                control = sc.nextInt();
                if(control == 1) {
                    System.out.println("Digite o nivel");
                    master.setLevel(sc.nextInt());
                }
                
                int result = masterDAO.update(master);
                if (result > 0) {
                    System.out.println("Registro atualizado");
                } else {
                    System.out.println("Registro não atualizado");
                }
                break;
            }
            
            case 5: {
                System.out.println("Digite o código do mestre que você deseja deletar");
                int id = sc.nextInt();
                
                Master master = masterDAO.read(id);
                if(master == null) {
                    System.out.println("Mestre não existe");
                    break;
                }
                
                ArrayList<Pokemon> pokemons = pokemonDAO.listByMaster(id);
                if(!pokemons.isEmpty()) {
                    System.out.println("Existem pokemons que pertencem a esse mestre, ao prosseguir eles ficarão sem mestre, prosseguir?");
                    System.out.println("1- Sim");
                    System.out.println("2- Não");
                    int control = sc.nextInt();
                    
                    if(control != 1) {
                        System.out.println("Deleção cancelada");
                        break;
                    }
                }
                
                int result = masterDAO.delete(id);
                if(result > 0) {
                    System.out.println("Mestre deletado com sucesso!");
                } else {
                    System.out.println("Erro ao deletar mestre");
                }
                break;
            }
        }
    }
    
    public static void elementsMenu() {
        ElementDAO elementDAO = new ElementDAO();
        PokemonDAO pokemonDAO = new PokemonDAO();
        PokemonElementDAO pokemonElementDAO = new PokemonElementDAO();
        
        System.out.println("\n---------- Submenu ----------");
        System.out.println("0. Sair");
        System.out.println("1. Listar elementos");
        System.out.println("2. Buscar elemento");
        System.out.println("3. Inserir elemento");
        System.out.println("4. Atualizar elemento");
        System.out.println("5. Deletar elemento");
        subMenuControl = sc.nextInt();
        
        switch(subMenuControl) {
            case 1: { 
                ArrayList<Element> elements = elementDAO.list();
                
                for(int i = 0; i < elements.size(); i++) {
                    System.out.println(elements.get(i).toString());
                }
                break;
            }
            
            case 2: {
                System.out.println("Digite o código do elemento que você deseja buscar");
                int id = sc.nextInt();
                
                Element element = elementDAO.read(id);
                if(element != null) {
                    System.out.println("Elemento encontrado!");
                    System.out.println(element.toString());
                    
                    ArrayList<Pokemon> pokemons = pokemonElementDAO.listPokemonsByElement(element.getId());
                    for(int i = 0; i < pokemons.size(); i++) {
                        if(i == 0) {
                            System.out.println("\nPokemons do elemento:");
                        }
                        
                        Pokemon pokemon = pokemons.get(i);
                        System.out.println(pokemon.toString());
                    }
                } else {
                    System.out.println("Elemento não encontrado");
                }
                break;
            }
            
            case 3: {
                System.out.println("Digite o nome do elemento:");
                String name = sc.next();
                
                Element element = new Element(name);

                int elementId = elementDAO.insert(element);
                if (elementId > 0) {
                    System.out.println("Quantos pokemons tem o elemento?");
                    int pokemonsLength = sc.nextInt();
                    
                    for(int i = 0; i < pokemonsLength; i++) {
                        System.out.println("Digite o código do " + (i + 1) +  " pokemon:");
                        int pokemonId = sc.nextInt();
                        
                        if(pokemonDAO.read(pokemonId) == null) {
                            System.out.println("Pokemon não existe");
                            continue;
                        }
                        pokemonElementDAO.insert(pokemonId, elementId);
                    }
                    
                    System.out.println("Registro criado");
                } else {
                    System.out.println("Registro não criado");
                }
                break;
            }
            
            case 4: {
                System.out.println("Digite o código do elemento que você deseja atualizar");
                int id = sc.nextInt();
                
                Element element = elementDAO.read(id);
                if(element == null) {
                    System.out.println("Elemento não encontrado");
                    break;
                }
                
                System.out.println("Digite novo nome do elemento");
                element.setName(sc.next());
                
                int result = elementDAO.update(element);
                if (result > 0) {
                    System.out.println("Registro atualizado");
                } else {
                    System.out.println("Registro não atualizado");
                }
                break;
            }
            
            case 5: {
                System.out.println("Digite o código do elemento que você deseja deletar");
                int id = sc.nextInt();
                
                Element element = elementDAO.read(id);
                if(element == null) {
                    System.out.println("Elemento não existe");
                    break;
                }
                
                ArrayList<Pokemon> pokemons = pokemonElementDAO.listPokemonsByElement(id);
                if(!pokemons.isEmpty()) {
                    System.out.println("Existem pokemons com esse elemento, ao prosseguir eles perderão o elemento, prosseguir?");
                    System.out.println("1- Sim");
                    System.out.println("2- Não");
                    int control = sc.nextInt();
                    
                    if(control != 1) {
                        System.out.println("Deleção cancelada");
                        break;                    
                    }
                }
                
                int result = elementDAO.delete(id);
                if(result > 0) {
                    System.out.println("Elemento deletado com sucesso!");
                } else {
                    System.out.println("Erro ao deletar elemento");
                }
                break;
            }
        }
    }
}
