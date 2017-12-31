/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfbox;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Lionel
 */
public class ImageExtractor extends PDFStreamEngine{
    ArrayList<String> paths = new ArrayList<String>();
    int imageNumber = 0;
    File srcFile = null;
    String destPath = "";
    public ImageExtractor(File srcFile, String destPath) {
        this.srcFile = srcFile;
        this.destPath = destPath;
    }

    @Override
    protected void processOperator(Operator operator, List<COSBase> operands) throws IOException {
         //To change body of generated methods, choose Tools | Templates.
         
         String operation = operator.getName();
        if( "Do".equals(operation) )
        {
            COSName objectName = (COSName) operands.get( 0 );
            PDXObject xobject = getResources().getXObject( objectName );
            if( xobject instanceof PDImageXObject)
            {
                PDImageXObject image = (PDImageXObject)xobject;
                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();
 
                // same image to local
                BufferedImage bImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_ARGB);
                bImage = image.getImage();
                ImageIO.write(bImage,"PNG",new File(destPath+"\\image_"+imageNumber+".png"));
                System.out.println("Image saved."+imageNumber);
                paths.add(destPath+"\\image_"+imageNumber);
                imageNumber++;
                
            }
            else if(xobject instanceof PDFormXObject)
            {
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        else
        {
            super.processOperator( operator, operands);
        }
    }
    public ArrayList<String> extract(){
        PDDocument document = null;
        try {
            document = PDDocument.load(srcFile);
            int pageNum = 0;
            for(PDPage page : document.getPages() )
            {
                pageNum++;
                System.out.println( "Processing page: " + pageNum );
                this.processPage(page);
            }
        } catch (Exception ex) {
            
        }finally
        {
            if( document != null )
            {
                try {
                    document.close();
                } catch (IOException ex) {
                    
                }
            }
        }
        return paths;
    }
}
    
    
    

