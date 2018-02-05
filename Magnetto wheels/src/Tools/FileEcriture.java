/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Object.Article;
import Object.Rayon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author moi
 */
public  class FileEcriture {
    public static void generateFile(ArrayList<Rayon> listRayon){
        
        for(Rayon rayon : listRayon){
            int compteur = 0;
            PrintWriter out = null;
            try {
                File fichier = new File("fichierText\\" + rayon.getCodeRayon()+".txt");
                if(!fichier.getParentFile().exists()){
                    fichier.getParentFile().mkdir();
                }
                out = new PrintWriter(fichier);
                out.println("Emp                  Code MP2                                        Code SAP                                        Unité                                Qte");
                
                for(Article article : rayon.getListArticle()){
                    out.println(article.getEmplacement()+"                  "+article.getAncienCodeArticle()+"                                        "+article.getCodeArticle()+"                                        "+article.getUnite()+"                                "+compteur);
                    compteur++;
                    out.println();
                    
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                
                out.close();
            }
            
        }
    }

    public static void remplirFichierExcel(File selectedFile, ArrayList<Rayon> list) {
        try {
            Workbook wb = WorkbookFactory.create(selectedFile);
            Sheet sheet  =wb.getSheetAt(0);
                int i = 0;
                for(Rayon rayon : list){
                    for(Article article : rayon.getListArticle()){
                    int compteurs = 0;
                    
                 sheet.getRow(i).getCell(6).setCellFormula(null);
                sheet.getRow(i).getCell(6).setCellType(CellType.STRING);
                
                sheet.getRow(i).getCell(6).setCellValue(rayon.getListArticle().get(compteurs).getStockTrouve());
                                          
                i++;
                    }
                }
                
            wb.close();
        } catch (IOException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
