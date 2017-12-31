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
public class Paragraphe {
       int index = 0;
       String name = "";

    public Paragraphe(int index, String name) {
        this.index = index;
        this.name = name;
    }
       private ArrayList<Notion> notions = new ArrayList<Notion>();
       public Paragraphe(ArrayList<Notion> notions){
           this.notions = notions;
       }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Notion> getNotions() {
        return notions;
    }

    public void setNotions(ArrayList<Notion> notions) {
        this.notions = notions;
    }
}
