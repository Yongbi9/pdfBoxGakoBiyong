/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import process.Process;


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
        File f = new File("C:\\Users\\Lionel\\Documents\\Devoir\\vraitestimage.pdf");
        paths = extractImagesFromPdf(f,"C:\\PdfBoxImages");
        ArrayList<SentenceAndSize> s = getFontLineByLineFromPdf(f);
        for(int i=0;i<s.size();i++){
            System.out.println(s.get(i).getSentence());
        }
        Process p = new Process(s, paths);
        p.launch();

    }
    public static ArrayList<SentenceAndSize> getFontLineByLineFromPdf(File fileName)throws IOException  
   {  
     ArrayList<SentenceAndSize> result = new ArrayList<SentenceAndSize>();
     PDDocument doc= PDDocument.load(fileName);  
     PDFTextStripper stripper = new PDFTextStripper() { 
        SentenceAndSize current = null;
       float prevBaseSize = 0;  
      
       protected void writeString(String text, List<TextPosition> textPositions) throws IOException  
       {  
         
         StringBuilder builder = new StringBuilder();  
        
         for (TextPosition position : textPositions)  
         {  
         
           float baseSize = position.getFontSizeInPt();  
           if (baseSize != 0 && baseSize != prevBaseSize)
           {  
             
             current = new SentenceAndSize();
             result.add(current);
             current.setSize(baseSize);
           
             prevBaseSize = baseSize;  
           
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
}
