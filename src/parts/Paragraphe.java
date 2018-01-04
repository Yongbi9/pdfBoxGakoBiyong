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
    private String name = "";
    private String font;
    private float size;
    public Paragraphe(int index, String name, String font, float size) {
        this.index = index;
        this.name = name;
        this.font = font;
        this.size = size;
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
