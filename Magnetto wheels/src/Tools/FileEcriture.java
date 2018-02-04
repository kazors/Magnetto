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

    public static void remplirFichierExcel(File selectedFile) {
        
    }
}
