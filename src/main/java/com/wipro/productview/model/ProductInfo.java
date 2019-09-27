package com.wipro.productview.model;

import java.math.BigDecimal;


public class ProductInfo {

    private Long productId;
    private String productName;
    private String productDescription;
    private int priceId;
    private BigDecimal price;
    private String currency;
    private Long promotionId;
    private String promoCode;
    private String promoDiscount;
    private String promoDescription;
    private int inventoryId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoDiscount() {
        return promoDiscount;
    }

    public void setPromoDiscount(String promoDiscount) {
        this.promoDiscount = promoDiscount;
    }

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }
}
