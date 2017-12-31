/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.util.ArrayList;

/**
 *
 * @author Lionel
 */
public class Partie {
   int index = 0;
   private String name = "";
   private ArrayList<Chapitre> chapitres = new ArrayList<Chapitre>();
   public Partie(String name, int index){
       this.name = name;
       this.index = index;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(ArrayList<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
   public Partie(ArrayList<Chapitre> chapitres){
      
       this.chapitres = chapitres;
   }
}
