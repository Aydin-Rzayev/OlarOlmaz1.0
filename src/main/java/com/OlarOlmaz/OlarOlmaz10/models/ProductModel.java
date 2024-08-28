package com.OlarOlmaz.OlarOlmaz10.models;



import org.hibernate.*;

import jakarta.persistence.*;


@Entity
@Table(name = "OlarOlmaz")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Boolean permissionOfUsage;

    @Column(nullable = false)
    private String ingredients;

    public void setProductName(String name){
        this.productName = name;
    }

    public void setPermissionOfUsage(Boolean permission){
        this.permissionOfUsage = permission;
    } 

    public void setIngredient(String ingr){
        
        this.ingredients = ingr;
    }

    public String getProductName(){
        return this.productName;
    }

    public Boolean isPermissible(){
        return this.permissionOfUsage;
    }

    public String getIngredients(){
       return ingredients;
    }

    
}
