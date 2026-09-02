package com.E_commerce.product_service.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "product.pagination")
public class ProductPaginationProperties {
    private int maxPageSize;

    public int getMaxPageSize(){
        return maxPageSize;
    }

    public void setMaxPageSize(int maxPageSize){
        this.maxPageSize=maxPageSize;
    }
}
