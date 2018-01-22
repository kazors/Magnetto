/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Enum.UniteEnum;

/**
 *
 * @author moi
 */
public class Article {
    private String ancienCodeArticle;
    private String codeArticle;
    private String designationArticle;
    private int stockDisponible;
    private String unité;
    private String emplacement;

    public Article(String ancienCodeArticle ,String codeArticle, String designationArticle, int stockDisponible, String unité, String emplacement) {
        this.ancienCodeArticle=ancienCodeArticle;
        this.codeArticle = codeArticle;
        this.designationArticle = designationArticle;
        this.stockDisponible = stockDisponible;
        this.unité = unité;
        this.emplacement = emplacement;
    }

    
    
    

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getDesignationArticle() {
        return designationArticle;
    }

    public void setDesignationArticle(String designationArticle) {
        this.designationArticle = designationArticle;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getAncienCodeArticle() {
        return ancienCodeArticle;
    }

    public void setAncienCodeArticle(String ancienCodeArticle) {
        this.ancienCodeArticle = ancienCodeArticle;
    }

    public String getUnité() {
        return unité;
    }

    public void setUnité(String unité) {
        this.unité = unité;
    }

   

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }
    
    
}
