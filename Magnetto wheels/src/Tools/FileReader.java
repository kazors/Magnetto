/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Object.Article;
import Object.Rayon;
import Windows.MainWindow;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author moi
 */
public class FileReader {
    
    
    

    public static void extractData(MainWindow context, File selectedFile, ArrayList<Rayon> listRayon) {
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(selectedFile);
            System.out.println("bite");
            Workbook excelFile = WorkbookFactory.create(selectedFile);
            
            Sheet dataSheet = excelFile.getSheetAt(1);
            createAllObject(dataSheet, listRayon);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Le fichier n\'éxiste pas", "Attention",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Prolème pendant la lecture du fichier", "Erreur",JOptionPane.ERROR_MESSAGE);
        } catch (InvalidFormatException ex) {
            JOptionPane.showMessageDialog(null,"Verifiez le type de fichier","Erreur",JOptionPane.ERROR_MESSAGE);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fis!=null)
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private static void createAllObject(Sheet dataSheet, ArrayList<Rayon> listRayon) {
        boolean endOfFile = false;
        int compteur = 1;
        while(!endOfFile){
            
            Row currentLine = dataSheet.getRow(compteur);
            String nomRayon="";
            if(currentLine != null && currentLine.getCell(3)!=null &&  currentLine.getCell(3).getStringCellValue()!=null && currentLine.getCell(3).getStringCellValue().compareTo("")!=0){
            int indexOfEndOfRayon = 0;
            boolean stop= false;
            for(char c :  currentLine.getCell(3).getStringCellValue().toCharArray() ){
                
                    if(!stop){
                    nomRayon+=c;
                    if(Character.isDigit(c)){
                        stop=true;
                    }
                    }
            }
            
                
            }else{
                
                    endOfFile=true;
                    
                
            }
            if(!listRayon.contains(new Rayon(nomRayon, new ArrayList<Article>()))){
            listRayon.add(new Rayon(nomRayon, new ArrayList<Article>()));
            }
            
            compteur++;
        }
        createArticle(listRayon, dataSheet);
        
    }

    private static void createArticle(ArrayList<Rayon> listRayon, Sheet dataSheet) {
        int compteur = 1;
        for(Rayon rayon : listRayon){
             String nomRayon = rayon.getCodeRayon();
             boolean endOfFile = false;
        Row currentline = dataSheet.getRow(compteur);
        while(!endOfFile && currentline != null && currentline.getCell(3)!=null &&  currentline.getCell(3).getStringCellValue()!=null && currentline.getCell(3).getStringCellValue().compareTo("")!=0 && dataSheet.getRow(compteur).getCell(3).getStringCellValue().startsWith(nomRayon)){
                
            
           
                System.out.println("emplacement : "+ currentline.getCell(3).getStringCellValue()+" rayon : "+nomRayon+" designation : "+currentline.getCell(2).getStringCellValue());
                rayon.getListArticle().add(new  Article(currentline.getCell(0).getStringCellValue(), currentline.getCell(1).getStringCellValue(), currentline.getCell(2).getStringCellValue(), (int)(currentline.getCell(4).getNumericCellValue()), currentline.getCell(5).getStringCellValue(), currentline.getCell(3).getStringCellValue()));
                compteur++;
                currentline=dataSheet.getRow(compteur);
            
        }
    }

    
    
    
}
}
