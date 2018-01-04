/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfbox;

/**
 *
 * @author Lionel
 */
public class SentenceAndSize {
    private String sentence ="";
    private float size = 0;
    private String fontName;
    public SentenceAndSize(float size,String sentence, String fontName){
        this.size = size;
        this.sentence = sentence;
        this.fontName = fontName;
    }
    public SentenceAndSize(){
        this.size = 0;
        this.sentence = "";
        this.fontName = "";
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }
    
    
}
