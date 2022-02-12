package com.example.digitalcampuscanteen;

public class ItemModel {

    String item_id;
    String item_name;
    String item_price;
    String item_type;
    String item_des;


    public ItemModel() {
    }

    public ItemModel(String item_id, String item_name, String item_price, String item_type, String item_des) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_type = item_type;
        this.item_des = item_des;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
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

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getItem_des() {
        return item_des;
    }

    public void setItem_des(String item_des) {
        this.item_des = item_des;
    }
}
