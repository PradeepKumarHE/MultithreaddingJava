package org.learnjava.service;

import org.learnjava.domain.ProductInfo;
import org.learnjava.domain.ProductOption;
import org.learnjava.domain.ProductInfo;

import java.util.List;

import static org.learnjava.util.CommonUtil.delay;

public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {
        delay(1000);
        List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99),
                new ProductOption(2, "128GB", "Black", 749.99));
        return ProductInfo.builder().productId(productId)
                .productOptions(productOptions)
                .build();
    }
}
