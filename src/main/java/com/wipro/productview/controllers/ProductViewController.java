package com.wipro.productview.controllers;

import com.wipro.productview.model.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@EnableDiscoveryClient
@EnableEurekaClient
@RestController
@RequestMapping("/products")
public class ProductViewController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/view/swagger-ui")
    public ModelAndView redirectToSwagger() {
        return new ModelAndView("redirect:/swagger-ui.html");
    }


    @ApiOperation(value = "Display complete product information", response = ProductInfo.class)
    @GetMapping("/view/{productId}")
    public ProductInfo getProductInformation(@PathVariable Long productId) throws RestClientException, IOException {
        List<ServiceInstance> instaces = discoveryClient.getInstances("zuul-gateway");
        ServiceInstance serviceInstance = instaces.get(0);

        String baseUrl = serviceInstance.getUri().toString();
        System.out.println(baseUrl);
        baseUrl = baseUrl + "/products/";

        RestTemplate restTemplate = new RestTemplate();
        ProductInfo productInfo = new ProductInfo();

//Calling Price Service


        ResponseEntity<ProductPrice> priceResponse = null;
        String priceUrl = baseUrl + "price/" + productId + "/list";
        priceResponse = restTemplate.exchange(priceUrl, HttpMethod.GET, getHeaders(), ProductPrice.class);
        productInfo.setPriceId(priceResponse.getBody().getPriceId());
        productInfo.setPrice(priceResponse.getBody().getPrice());
        productInfo.setCurrency(priceResponse.getBody().getCurrency());

//Calling Product Service

        ResponseEntity<Product> productResponse = null;
        String productUrl = baseUrl + "info/" + productId;
        productResponse = restTemplate.exchange(productUrl, HttpMethod.GET, getHeaders(), Product.class);
        productInfo.setProductId(productResponse.getBody().getId());
        productInfo.setProductDescription(productResponse.getBody().getProductDescription());
        productInfo.setProductName(productResponse.getBody().getProductName());


//Calling Promotion Service

        ResponseEntity<Promotion> promotionResponse = null;
        String promotionUrl = baseUrl + productId + "/promotions";
        promotionResponse = restTemplate.exchange(promotionUrl, HttpMethod.GET, getHeaders(), Promotion.class);
        productInfo.setPromotionId(promotionResponse.getBody().getPromotionId());
        productInfo.setPromoCode(promotionResponse.getBody().getPromoCode());
        productInfo.setPromoDiscount(promotionResponse.getBody().getPromoDiscount());
        productInfo.setPromoDescription(promotionResponse.getBody().getPromoDescription());

//Calling Inventory Service

        ResponseEntity<Inventory> inventoryResponse = null;
        String inventoryUrl = baseUrl + "inventory/" + productId + "/list";
        inventoryResponse = restTemplate.exchange(inventoryUrl, HttpMethod.GET, getHeaders(), Inventory.class);
        productInfo.setInventoryId(inventoryResponse.getBody().getInventoryId());
        productInfo.setQty(inventoryResponse.getBody().getQty());
        productInfo.setPackZone(inventoryResponse.getBody().getPackZone());
        productInfo.setPickZone(inventoryResponse.getBody().getPickZone());
        productInfo.setShippingArea(inventoryResponse.getBody().getShippingArea());
        return productInfo;
    }

    private static HttpEntity<?> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }


}
