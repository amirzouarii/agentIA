package com.example.agentia.tools;


import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTools {


    @Tool(description = "recupere un produit à partir de son id")
    public Produit getProduct(int id){
        return new Produit(id, "pc portable", 5000);
    }

    @Tool(description = "recupere la liste des produits")
    public List<Produit> getProducts(){
        return List.of(new Produit(1, "pc portable", 5000),
                new Produit(2,"clavier",90),
                new Produit(3,"ecran LED",159));
    }
    record Produit(int id,String nom,double prix){}
}
