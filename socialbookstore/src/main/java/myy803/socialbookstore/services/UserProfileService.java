package myy803.socialbookstore.services;

import java.util.List;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.UserProfileDto;

public interface UserProfileService {

    public List<BookCategory> getAllCategories(); 
    public UserProfileDto retrieveProfile(String username);
    public void saveProfile(UserProfileDto userProfileDto);

}
