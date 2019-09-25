package com.wipro.productview.controllers;

import com.wipro.productview.model.ProductInfo;
import com.wipro.productview.model.ProductPrice;
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

    @GetMapping(value= "/view/swagger-ui")
    public ModelAndView redirectToSwagger(){
        return new ModelAndView("redirect:/swagger-ui.html");
    }


    @ApiOperation(value = "Display complete product information", response = ProductInfo.class)
    @GetMapping("/view/{productId}")
    public ProductInfo getProductInformation(@PathVariable Long productId) throws RestClientException, IOException{
        List<ServiceInstance> instaces = discoveryClient.getInstances("zuul-gateway");
        ServiceInstance serviceInstance = instaces.get(0);

        String baseUrl = serviceInstance.getUri().toString();
        baseUrl=baseUrl+"/products";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProductPrice> response = null;

        ProductInfo productInfo = new ProductInfo();

//Calling Price Service

        String priceUrl = baseUrl+"/price/"+productId+"/list";
        response = restTemplate.exchange(priceUrl, HttpMethod.GET,getHeaders(),ProductPrice.class);
        productInfo.setPriceId(response.getBody().getPriceId());
        productInfo.setPrice(response.getBody().getPrice());
        productInfo.setCurrency(response.getBody().getCurrency());

//Calling Product Service



//Calling Promotion Service



//Calling Inventory Service





        return productInfo;
    }

    private static HttpEntity<?> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }


}
