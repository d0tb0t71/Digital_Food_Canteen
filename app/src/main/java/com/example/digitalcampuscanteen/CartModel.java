package com.example.digitalcampuscanteen;

public class CartModel {

    private String item_id;
    private String item_name;
    private String item_price;
    private String item_quantity;


    public CartModel() {
    }

    public CartModel(String item_id, String item_name, String item_price, String item_quantity) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
    }

    public String getitem_id() {
        return item_id;
    }

    public void setitem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }
}
