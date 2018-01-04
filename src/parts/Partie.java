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
   private String font;
   private float size;
   private ArrayList<Chapitre> chapitres = new ArrayList<Chapitre>();
   public Partie(String name, int index, String font, float size){
       this.name = name;
       this.index = index;
       this.font = font;
       this.size = size;
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

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
