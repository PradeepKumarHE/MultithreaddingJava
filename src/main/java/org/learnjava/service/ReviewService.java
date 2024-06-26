package org.learnjava.service;

import org.learnjava.domain.Review;
import org.learnjava.domain.Review;

import static org.learnjava.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
