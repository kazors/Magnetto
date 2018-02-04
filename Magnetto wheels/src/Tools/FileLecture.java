/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Object.Article;
import Object.Rayon;
import Windows.MainWindow;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class FileLecture {
    
    
    

    public static ArrayList<Rayon> extractData(MainWindow context, File selectedFile, ArrayList<Rayon> listRayon) {
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(selectedFile);
            
            Workbook excelFile = WorkbookFactory.create(selectedFile);
            
            Sheet dataSheet = excelFile.getSheetAt(1);
            createAllObject(dataSheet, listRayon);
            return listRayon;
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Le fichier n\'éxiste pas", "Attention",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Prolème pendant la lecture du fichier", "Erreur",JOptionPane.ERROR_MESSAGE);
        } catch (InvalidFormatException ex) {
            JOptionPane.showMessageDialog(null,"Verifiez le type de fichier","Erreur",JOptionPane.ERROR_MESSAGE);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(FileLecture.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(fis!=null)
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileLecture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    private static void createAllObject(Sheet dataSheet, ArrayList<Rayon> listRayon) {
       for(int i=2;i<=dataSheet.getLastRowNum();i++){
           
         Row currentLine = dataSheet.getRow(i);
         if(currentLine.getCell(3)!= null && !"".equals(currentLine.getCell(3).getStringCellValue())){
         String currentRayon = getEmplacement(currentLine);
           //System.out.println(" Rayon : "+ currentRayon);
             
         Article currentArticle = new Article(currentLine.getCell(0).getStringCellValue(), currentLine.getCell(1).getStringCellValue(),currentLine.getCell(2).getStringCellValue() ,(int)currentLine.getCell(4).getNumericCellValue(),currentLine.getCell(5).getStringCellValue(), currentLine.getCell(3).getStringCellValue());
       if(listRayon.isEmpty() || listRayon.get(listRayon.size()-1).getCodeRayon().compareTo(currentRayon)!=0){
           listRayon.add(new Rayon(currentRayon, new ArrayList<Article>()));
           
       }
       listRayon.get(listRayon.size()-1).getListArticle().add(currentArticle);
       }
       }
        
    }
    
    private static void readCompleteTxtFile(){
        
    }

    
    
    

    
    public static String getEmplacement(Row currentLine){
        
        boolean stop=false;
        String nomRayon="";
         for(char c :  currentLine.getCell(3).getStringCellValue().toCharArray() ){
                
                    if(!stop){
                    nomRayon+=c;
                    if(Character.isDigit(c)){
                        stop=true;
                    }
                    }
            }
         
         return nomRayon;
    }

    public static void ReadCompleteFile (List<File> selectedFiles , ArrayList<Rayon> listRayon) {
        ArrayList<Integer> listValeurSaisie = new ArrayList<Integer>();
        for(File currentFile : selectedFiles){
            
//           
           // System.out.println(currentFile.getName());
try {
                
                
              

                FileReader fileReader = new FileReader(currentFile);
                
                BufferedReader reader = new BufferedReader(fileReader);
                String line= reader.readLine() ;
                System.out.println(line);
                while((line = reader.readLine())!=null){  
                    reader.readLine();
                    System.out.println("La LIGNE : "+ line);
                    System.out.println("Le TABLEAU "+ line.split("  ").toString());
                    listValeurSaisie.add( !"".equals(line.split("  ")[4]) ?Integer.parseInt(line.split("  ")[4]):0);
                    
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileLecture.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileLecture.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        int compteur =0;
        for(Rayon rayon: listRayon){
            for(Article article : rayon.getListArticle()){
                article.setStockTrouve(listValeurSaisie.get(compteur));
                compteur++;
                System.out.println(article.getDesignationArticle()+" qte SAP : "+article.getStockDisponible()+" qte trouve : "+article.getStockTrouve());
            }
        }
        
    }
}
