package br.com.bravox.eval;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SentimentAnalysisTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    void testPositiveSentiment() {
       var positiveReview = "I absolutely love the hotel, it was amazing";
       var sentiment = reviewService.classifySentiment(positiveReview);
       assertEquals(Sentiment.POSITIVE, sentiment, "the sentiment should be classified as positive.");
    }

    @Test
    void testNegativeSentiment() {
        var negativeReview = "This is the worst experience I've ever had. The product is terrible and broke immediately.";
        var result = reviewService.classifySentiment(negativeReview);
        assertEquals(Sentiment.NEGATIVE, result, "The sentiment should be classified as NEGATIVE.");
    }

    @Test
    void testNeutralSentiment() {
        var neutralReview = "The product is okay. It does what it says but nothing more.";
        var result = reviewService.classifySentiment(neutralReview);
        assertEquals(Sentiment.NEUTRAL, result, "The sentiment should be classified as NEUTRAL.");
    }
}
