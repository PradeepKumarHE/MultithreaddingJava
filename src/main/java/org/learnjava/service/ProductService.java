package org.learnjava.service;

import lombok.Getter;
import org.learnjava.domain.Product;
import org.learnjava.domain.ProductInfo;
import org.learnjava.domain.Review;

import static org.learnjava.util.CommonUtil.stopWatch;
import static org.learnjava.util.LoggerUtil.log;

public class ProductService {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) throws InterruptedException {
        stopWatch.start();

        ProductInfoRunnable productInfoRunnable = new ProductInfoRunnable(productId);
        Thread productInfoThread = new Thread(productInfoRunnable);

        ReviewRunnable reviewInfoRunnable = new ReviewRunnable(productId);
        Thread reviewThread = new Thread(reviewInfoRunnable);

        productInfoThread.start();
        reviewThread.start();

        productInfoThread.join();
        reviewThread.join();

        ProductInfo productInfo=productInfoRunnable.getProductInfo();
        Review review=reviewInfoRunnable.getReview();

        stopWatch.stop();
        log("Total Time Taken : " + stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) throws InterruptedException {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductService productService = new ProductService(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }


    private class ProductInfoRunnable implements Runnable {
        @Getter
        private ProductInfo productInfo;
        private String productId;

        public ProductInfoRunnable(String productId) {
            this.productId = productId;
        }

        @Override
        public void run() {
            productInfo = productInfoService.retrieveProductInfo(productId);
        }

    }

    private class ReviewRunnable implements Runnable {
        private String productId;
        @Getter
        private Review review;

        public ReviewRunnable(String productId) {
            this.productId = productId;
        }

        @Override
        public void run() {
            review = reviewService.retrieveReviews(productId);
        }

    }
}
