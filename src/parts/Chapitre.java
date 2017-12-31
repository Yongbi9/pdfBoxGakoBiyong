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
public class Chapitre {
    int index = 0;
    String name = "";
    private ArrayList<Paragraphe> paragraphes = new ArrayList<Paragraphe>();
    public Chapitre(int index,String name){
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<Paragraphe> getParagraphes() {
        return paragraphes;
    }

    public void setParagraphes(ArrayList<Paragraphe> paragraphes) {
        this.paragraphes = paragraphes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
