/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.io.File;
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
    int k = 0;
    ArrayList<String> imagePaths = null;
    ArrayList<Partie> parts = new ArrayList<Partie>();
    ArrayList<Chapitre> chapters = new ArrayList<Chapitre>();
    ArrayList<Paragraphe> paragraphs = new ArrayList<Paragraphe>();
    ArrayList<SentenceAndSize> sentencesAndSizes = null;
    public Process(ArrayList<SentenceAndSize> s, ArrayList<String> imagePaths){
        this.sentencesAndSizes = s;
        this.imagePaths = imagePaths;
    }
public void processParts(){
    for(int i =0; i< sentencesAndSizes.size();i++){
        if(sentencesAndSizes.get(i).getSize() >= 40){
            parts.add(new Partie(sentencesAndSizes.get(i).getSentence(),i));
        }
    }
}
public void processChapters(){
    for(int i =0; i< parts.size()-1;i++){
    if(parts.size()>0){
       
        for(int j = parts.get(i).getIndex();j <parts.get(i+1).getIndex();j++){
            if(sentencesAndSizes.get(j).getSize() <= 38 && sentencesAndSizes.get(j).getSize() >= 30){
                Chapitre c= new Chapitre(j,sentencesAndSizes.get(j).getSentence());
                parts.get(i).getChapitres().add(c);
                chapters.add(c);
            }
        
    }
}
    for(int j= parts.get(parts.size()-1).getIndex(); j<sentencesAndSizes.size();j++){
        if(sentencesAndSizes.get(j).getSize() <= 38 && sentencesAndSizes.get(j).getSize() >= 30){
                Chapitre c = new Chapitre(j,sentencesAndSizes.get(j).getSentence());
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
                Paragraphe p = new Paragraphe(j,sentencesAndSizes.get(j).getSentence());
                chapters.get(i).getParagraphes().add(p);
                paragraphs.add(p);
            }
        
    }
}
    for(int j= chapters.get(chapters.size()-1).getIndex(); j<sentencesAndSizes.size();j++){
        if(sentencesAndSizes.get(j).getSize() <= 28 && sentencesAndSizes.get(j).getSize() >= 20){
            Paragraphe p = new Paragraphe(j,sentencesAndSizes.get(j).getSentence());
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
                Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"texte");
                paragraphs.get(i).getNotions().add(n);
               
            }else if(sentencesAndSizes.get(j).getSize() <10){
                Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"image",imagePaths.get(k));
                paragraphs.get(i).getNotions().add(n);
                k++;
            }
        
    }
}
    for(int j= paragraphs.get(paragraphs.size()-1).getIndex(); j<sentencesAndSizes.size();j++){
        if(sentencesAndSizes.get(j).getSize() <= 18 && sentencesAndSizes.get(j).getSize()>=10){
            Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"texte");
                paragraphs.get(paragraphs.size()-1).getNotions().add(n);
               
    }else if(sentencesAndSizes.get(j).getSize() <10){
                Notion n = new Notion(sentencesAndSizes.get(j).getSentence(),"image",imagePaths.get(k));
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
}
