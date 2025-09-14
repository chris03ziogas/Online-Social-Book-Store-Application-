package myy803.socialbookstore.datamodel.recomstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.UserProfileMapper;
import myy803.socialbookstore.datamodel.UserProfile;

public abstract class BaseRecommendationStrategy implements RecommendationsStrategy {
    @Autowired
    private UserProfileMapper userProfileMapper;
    
    public List<BookDto> recommend(String username)
    {
        UserProfile userProfile = userProfileMapper.findByUsername(username);
        return retrieveRecommendedBooks(userProfile);

    }
    protected abstract List<BookDto> retrieveRecommendedBooks(UserProfile userProfile); 


}
