/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import process.Process;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import parts.*;


/**
 *
 * @author Lionel
 */
public class PdfBox {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArrayList<String> paths = null;
        if(args.length == 4){
            File f = new File(args[0].trim());//"C:\\Users\\Alain\\Documents\\projet_4gi.pdf"
            paths = extractImagesFromPdf(f,args[1].trim());//"C:\\PdfBoxImages"
            ArrayList<SentenceAndSize> s = getFontLineByLineFromPdf(f);

            /*for(int i=0;i<s.size();i++){
                System.out.println(s.get(i).getSentence());
            }*/

            Process p = new Process(s, paths);
            p.launch();

            boolean b = outputXML(p, args[2], args[3]);
        }
        else{
            //System.out.println("------- "+args.length);
            return;
        }
    }
    
    public static ArrayList<SentenceAndSize> getFontLineByLineFromPdf(File fileName)throws IOException  
    {  
     ArrayList<SentenceAndSize> result = new ArrayList<SentenceAndSize>();
     PDDocument doc= PDDocument.load(fileName);  
     PDFTextStripper stripper = new PDFTextStripper() { 
        SentenceAndSize current = null;
       float prevBaseSize = 0;
       String prevBaseFont = "";
      
       protected void writeString(String text, List<TextPosition> textPositions) throws IOException  
       {  
         
         StringBuilder builder = new StringBuilder();  
        
         for (TextPosition position : textPositions)  
         {  
         
           float baseSize = position.getFontSizeInPt();
           String baseFont = position.getFont().getName();
           if (baseSize != 0 && baseSize != prevBaseSize)
           {  
             
             current = new SentenceAndSize();
             result.add(current);
             current.setSize(baseSize);
             current.setFontName(baseFont);
             
             prevBaseSize = baseSize;  
             prevBaseFont = baseFont;
           }  
           current.setSentence(current.getSentence()+position.getUnicode());  
         }
         String str= "";
         for(SentenceAndSize s : result){
             str += s.getSentence();
         }
          writeString(str);
       }  
     };  
     String content=stripper.getText(doc);  
     doc.close();  
     return result;
   
   }  
    public static ArrayList<String> extractImagesFromPdf(File oldFile, String destinationDir) {
     ImageExtractor ext = new ImageExtractor(oldFile, destinationDir);
    return(ext.extract());
}
    
    public static boolean outputXML(Process p, String name, String path){
        boolean res = false;
        
        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbfactory.newDocumentBuilder();
            Document doc = db.newDocument();
            Element rootElement = doc.createElement(name);
            doc.appendChild(rootElement);
            
            ArrayList<Partie> parties = p.getParts();
            for(int i = 0; i < parties.size(); i++){
                Element partie = doc.createElement("Partie");
                partie.setAttribute("index", ""+parties.get(i).getIndex());
                partie.setAttribute("name", parties.get(i).getName());
                partie.setAttribute("font", parties.get(i).getFont());
                partie.setAttribute("size", ""+parties.get(i).getSize());
                
                ArrayList<Chapitre> chapitres = parties.get(i).getChapitres();
                for(int j = 0; j < chapitres.size(); j++){
                    Element chapitre = doc.createElement("Chapitre");
                    chapitre.setAttribute("index", ""+chapitres.get(j).getIndex());
                    chapitre.setAttribute("name", chapitres.get(j).getName());
                    chapitre.setAttribute("font", chapitres.get(j).getFont());
                    chapitre.setAttribute("size", ""+chapitres.get(j).getSize());
                    partie.appendChild(chapitre);
                    
                    ArrayList<Paragraphe> paragraphes = chapitres.get(j).getParagraphes();
                    for(int k = 0; k < paragraphes.size(); k++){
                        Element paragraphe = doc.createElement("Paragraphe");
                        paragraphe.setAttribute("index", ""+paragraphes.get(k).getIndex());
                        paragraphe.setAttribute("name", paragraphes.get(k).getName());
                        paragraphe.setAttribute("font", paragraphes.get(k).getFont());
                        paragraphe.setAttribute("size", ""+paragraphes.get(k).getSize());
                        chapitre.appendChild(paragraphe);
                        
                        ArrayList<Notion> notions = paragraphes.get(k).getNotions();
                        for(int l = 0; l < notions.size(); l++){
                            Element notion = doc.createElement("Notion");
                            notion.setAttribute("type", notions.get(l).getType());
                            notion.setAttribute("path", notions.get(l).getPath());
                            notion.appendChild(doc.createTextNode(notions.get(l).getContenu()));
                            notion.setAttribute("font", notions.get(l).getFont());
                            notion.setAttribute("size", ""+notions.get(l).getSize());
                            paragraphe.appendChild(notion);
                        }
                    }
                }
                rootElement.appendChild(partie);
            }
            Out(doc, path);
            res = true;
        } catch (Exception e) {
            return false;
        }
        
        return res;
    }
    
    private static void Out(Document doc, String path){
        try {
            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            //for pretty print
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            //StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File(path));

            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
