package koszolko.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


/**
 * Example of user based recommender
 */
public class RecommendationService {

    private static final double THRESHOLD = 0.1;

    public UserBasedRecommender buildRecommender(DataModel data) throws TasteException {
        UserSimilarity similarity = new PearsonCorrelationSimilarity(data);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(THRESHOLD, similarity, data);
        return new GenericUserBasedRecommender(data, neighborhood, similarity);
    }
}
