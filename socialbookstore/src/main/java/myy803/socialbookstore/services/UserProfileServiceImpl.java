package myy803.socialbookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    

    @Autowired
	private UserProfileMapper userProfileMapper;

    @Autowired
	private BookAuthorMapper bookAuthorMapper;
	
	@Autowired
	private BookCategoryMapper bookCategoryMapper;

    @Override
    public List<BookCategory> getAllCategories()
    {
        List<BookCategory> categories = bookCategoryMapper.findAll();
        
        return categories;
    }

    @Override
    public UserProfileDto retrieveProfile(String username)
    {

        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
		UserProfile userProfile = null; 
		UserProfileDto userProfileDto = null;
		if(optUserProfile.isPresent()) {
			userProfile = optUserProfile.get();
			userProfileDto = userProfile.buildProfileDto();
		} else {
			userProfileDto = new UserProfileDto();
			userProfileDto.setUsername(username);
		}
        return userProfileDto;
    }

    @Override
    public void saveProfile(UserProfileDto userProfileDto) {
        
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(userProfileDto.getUsername());
		UserProfile userProfile = null;
		if(optUserProfile.isPresent())	
			userProfile = optUserProfile.get();
		else
			userProfile = new UserProfile();
		
		userProfileDto.buildUserProfile(userProfile, bookAuthorMapper, bookCategoryMapper);
		
		userProfileMapper.save(userProfile);

    }

}
