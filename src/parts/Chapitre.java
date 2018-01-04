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
    private String name = "";
    private String font;
    private float size;
    private ArrayList<Paragraphe> paragraphes = new ArrayList<Paragraphe>();
    public Chapitre(int index,String name, String font, float size){
        this.index = index;
        this.name = name;
        this.font = font;
        this.size = size;
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
