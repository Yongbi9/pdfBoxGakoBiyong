/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

/**
 *
 * @author Lionel
 */
public class Notion {
    private String contenu = null;
    private String type ="";
    private String path="";
    private String font;
    private float size;
    public Notion(String contenu, String type, String font, float size){
        this.contenu = contenu;
        this.type = type;
        this.font = font;
        this.size = size;
    }
    public Notion(String contenu, String type, String path, String font, float size){
        this.contenu = contenu;
        this.type = type;
        this.path = path;
        this.font = font;
        this.size = size;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
