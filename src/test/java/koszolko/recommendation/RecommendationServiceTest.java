package koszolko.recommendation;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Example of using {@link koszolko.recommendation.RecommendationService}
 */
public class RecommendationServiceTest {

    private static final String DATA_FILE = "data.csv";
    private static final long USER_ID = 2L;

    private final RecommendationService recommendationService = new RecommendationService();

    @Test
    public void testRecommendation() throws IOException, TasteException {
        //given
        DataModel dataModel = new FileDataModel(getData());
        //when
        UserBasedRecommender userBasedRecommender = recommendationService.buildRecommender(dataModel);
        //then
        assertThat(userBasedRecommender).isNotNull();
        assertThat(userBasedRecommender.recommend(USER_ID, 3)).isNotNull();
        assertThat(userBasedRecommender.recommend(USER_ID, 3)).describedAs("User 2 should have 3 recommended items").hasSize(3);
    }

    @SuppressWarnings("ConstantConditions")
    private File getData() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(DATA_FILE).getFile());
    }
}
