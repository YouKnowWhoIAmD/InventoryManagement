package com.dsc.inventorymanagement.dataStorers;

public class Items {

    private final int id;
    private final String name;
    private final String price;
    private final int quantity;
    private final String image;

    public Items(int id,String name,String price,int quantity,String image){
        this.name=name;
        this.id=id;
        this.price=price;
        this.quantity=quantity;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
}
