package com.example.digitalcampuscanteen;

public class OrderModel {

    String orderID ;
    String uid;
    String order;
    String orderStatus;
    String totalModel;

    public OrderModel() {
    }

    public OrderModel(String orderID, String uid, String order, String orderStatus, String totalModel) {
        this.orderID = orderID;
        this.uid = uid;
        this.order = order;
        this.orderStatus = orderStatus;
        this.totalModel = totalModel;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTotalModel() {
        return totalModel;
    }

    public void setTotalModel(String totalModel) {
        this.totalModel = totalModel;
    }
}
