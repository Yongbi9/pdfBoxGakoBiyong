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
    String sentence ="";
    float size = 0;
    String fontName = "";
    public SentenceAndSize(float size,String sentence){
        this.size = size;
        this.sentence = sentence;
    }
    public SentenceAndSize(){
        this.size = 0;
        this.sentence = "";
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
