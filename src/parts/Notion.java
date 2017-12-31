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
    public Notion(String contenu, String type){
        this.contenu = contenu;
        this.type = type;
    }
    public Notion(String contenu, String type, String path){
        this.contenu = contenu;
        this.type = type;
        this.path = path;
    }
}
