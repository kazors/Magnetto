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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moi
 */
public  class FileWriter {
    public static void generateFile(ArrayList<Rayon> listRayon){
        System.out.println("priut");
        for(Rayon rayon : listRayon){
            PrintWriter out = null;
            try {
                File fichier = new File(rayon.getCodeRayon()+".txt");
                out = new PrintWriter(fichier);
                out.print("Emp                  Code MP2                                        Code SAP                                        Unité                                Qte");
                ;
                for(Article article : rayon.getListArticle()){
                    out.print(article.getEmplacement()+"                  "+article.getAncienCodeArticle()+"                                        "+article.getCodeArticle()+"                                        "+article.getUnité()+"                                ");
                    out.println();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                out.close();
            }
            System.out.println("j'ai fini mdr");
        }
    }
}
