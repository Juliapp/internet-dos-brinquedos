package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipe implements Serializable {
    private static final long serialVersionUID = 4L;
    private String nome;  
    private ArrayList<Carro> carros;;

    public Equipe(String nome) {
        this.nome = nome;
        carros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(ArrayList<Carro> carros) {
        this.carros = carros;
    }
    
    public void addCarro(Carro c){
        carros.add(c);
    }



    @Override
    public boolean equals(Object obj) {
        
        if(obj instanceof String){
           String aux  = (String) obj;
           return this.nome.equals(aux);  
        }
 
        if(obj instanceof Equipe){
           Equipe aux  = (Equipe) obj;
           return this.nome.equals(aux.getNome());  
        }
        
        return false;
    }

    @Override
    public String toString() {
        return "Equipe{" + getNome() + '}';
    }
    
    
}
