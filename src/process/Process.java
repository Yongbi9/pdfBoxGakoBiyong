/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.util.ArrayList;
import parts.Chapitre;
import parts.Notion;
import parts.Paragraphe;
import parts.Partie;
import pdfbox.SentenceAndSize;

/**
 *
 * @author Lionel
 */
public class Process {
    private int k = 0;
    private ArrayList<String> imagePaths = null;
    private ArrayList<Partie> parts = new ArrayList<Partie>();
    private ArrayList<Chapitre> chapters = new ArrayList<Chapitre>();
    private ArrayList<Paragraphe> paragraphs = new ArrayList<Paragraphe>();
    private ArrayList<SentenceAndSize> sentencesAndSizes = null;
    
    public Process(ArrayList<SentenceAndSize> s, ArrayList<String> imagePaths){
        this.sentencesAndSizes = s;
        this.imagePaths = imagePaths;
    }
    public void processParts(){
        int indexPart = 0;
        for(int i =0; i< sentencesAndSizes.size();i++){
            if(sentencesAndSizes.get(i).getSize() >= 40){
                parts.add(new Partie(sentencesAndSizes.get(i).getSentence(),i,sentencesAndSizes.get(i).getFontName(), sentencesAndSizes.get(i).getSize()));
                indexPart++;
            }
        }
    }
    public void processChapters(){
        for(int i =0; i< parts.size()-1;i++){
            if(parts.size()>0){
                for(int j = parts.get(i).getIndex();j <parts.get(i+1).getIndex();j++){
                    if(sentencesAndSizes.get(j).getSize() <= 38 && sentencesAndSizes.get(j).getSize() >= 30){
                        Chapitre c= new Chapitre(j,sentencesAndSizes.get(j).getSentence(), sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                        parts.get(i).getChapitres().add(c);
                        chapters.add(c);
                    }
                }
            }
            for(int j= parts.get(parts.size()-1).getIndex(); j<sentencesAndSizes.size();j++){
                if(sentencesAndSizes.get(j).getSize() <= 38 && sentencesAndSizes.get(j).getSize() >= 30){
                    Chapitre c = new Chapitre(j,sentencesAndSizes.get(j).getSentence(), sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                    parts.get(parts.size()-1).getChapitres().add(c);
                    chapters.add(c);
                }
            }
        }
    }
    
    public void processParagraphs(){
        if(chapters.size() >0){
        for(int i =0; i< chapters.size()-1;i++){
            for(int j = chapters.get(i).getIndex();j <chapters.get(i+1).getIndex();j++){
                if(sentencesAndSizes.get(j).getSize() <= 28 && sentencesAndSizes.get(j).getSize() >= 20){
                    Paragraphe p = new Paragraphe(j,sentencesAndSizes.get(j).getSentence(), sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                    chapters.get(i).getParagraphes().add(p);
                    paragraphs.add(p);
                }
            }
        }
        for(int j= chapters.get(chapters.size()-1).getIndex(); j<sentencesAndSizes.size();j++){
            if(sentencesAndSizes.get(j).getSize() <= 28 && sentencesAndSizes.get(j).getSize() >= 20){
                Paragraphe p = new Paragraphe(j,sentencesAndSizes.get(j).getSentence(), sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                chapters.get(chapters.size()-1).getParagraphes().add(p);
                paragraphs.add(p);
            }
        }
        }
    }
    public void processNotions(){
        if(paragraphs.size()>0){
            for(int i =0; i< paragraphs.size()-1;i++){
                for(int j = paragraphs.get(i).getIndex();j <paragraphs.get(i+1).getIndex();j++){
                    if(sentencesAndSizes.get(j).getSize() <= 18 && sentencesAndSizes.get(j).getSize()>=10){
                        Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"texte", sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                        paragraphs.get(i).getNotions().add(n);

                    }
                    else if(sentencesAndSizes.get(j).getSize() <10){
                        Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"image",imagePaths.get(k), sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                        paragraphs.get(i).getNotions().add(n);
                        k++;
                    }
                }
            }
            for(int j= paragraphs.get(paragraphs.size()-1).getIndex(); j<sentencesAndSizes.size();j++){
                if(sentencesAndSizes.get(j).getSize() <= 18 && sentencesAndSizes.get(j).getSize()>=10){
                    Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"texte", sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                    paragraphs.get(paragraphs.size()-1).getNotions().add(n);

                }
                else if(sentencesAndSizes.get(j).getSize() <10){
                    Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"image",imagePaths.get(k), sentencesAndSizes.get(j).getFontName(), sentencesAndSizes.get(j).getSize());
                    paragraphs.get(paragraphs.size()-1).getNotions().add(n);
                    k++;
                }
            }
        }
    }
    public void launch(){
        processParts();
        processChapters();
        processParagraphs();
        processNotions();
        int i = 0;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public ArrayList<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(ArrayList<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public ArrayList<Partie> getParts() {
        return parts;
    }

    public void setParts(ArrayList<Partie> parts) {
        this.parts = parts;
    }

    public ArrayList<Chapitre> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapitre> chapters) {
        this.chapters = chapters;
    }

    public ArrayList<Paragraphe> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(ArrayList<Paragraphe> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public ArrayList<SentenceAndSize> getSentencesAndSizes() {
        return sentencesAndSizes;
    }

    public void setSentencesAndSizes(ArrayList<SentenceAndSize> sentencesAndSizes) {
        this.sentencesAndSizes = sentencesAndSizes;
    }
}
