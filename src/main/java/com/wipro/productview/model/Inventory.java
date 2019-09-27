package com.wipro.productview.model;

public class Inventory {

    private int inventoryId;
    private int productId;
    private int qty;
    private String pickZone;
    private String packZone;
    private String ShippingArea;

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getPickZone() {
        return pickZone;
    }

    public void setPickZone(String pickZone) {
        this.pickZone = pickZone;
    }

    public String getPackZone() {
        return packZone;
    }

    public void setPackZone(String packZone) {
        this.packZone = packZone;
    }

    public String getShippingArea() {
        return ShippingArea;
    }

    public void setShippingArea(String shippingArea) {
        ShippingArea = shippingArea;
    }
}
