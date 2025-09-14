package myy803.socialbookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;

@Service
public class BookOfferServiceImpl implements BookOfferService {

    @Autowired
	private UserProfileMapper userProfileMapper;

    @Autowired
	private BookAuthorMapper bookAuthorMapper;
	
	@Autowired
	private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private BookMapper bookMapper;

    

    @Override
    public List<BookDto> listBookOffers(String username) {
            	
    	Optional<UserProfile> optUserProfile = Optional.of(userProfileMapper.findById(username).orElseThrow(
            ()-> new UsernameNotFoundException(
                    String.format("USER_NOT_FOUND", username)
            )));
                                               
		UserProfile userProfile = optUserProfile.get();
		List<BookDto> bookOffersDtos = userProfile.buildBookOffersDtos();

        return bookOffersDtos;

    }

    @Override
    public List<BookCategory> getAllCategories() {
        
        return bookCategoryMapper.findAll();
    }

    @Override
    public void saveOffer(String username , BookDto bookOfferDto) {

        Optional<UserProfile> optUserProfile = Optional.of(userProfileMapper.findById(username).orElseThrow(
            ()-> new UsernameNotFoundException(
                    String.format("USER_NOT_FOUND", username)
            )));
		UserProfile userProfile = optUserProfile.get();
		userProfile.addBookOffer(bookOfferDto.buildBookOffer(bookAuthorMapper, bookCategoryMapper));
		
		userProfileMapper.save(userProfile);
    }

    @Override
    public void deleteOffer(int bookId, String username) 
    {
        
        Optional<UserProfile> optUserProfile = Optional.of(userProfileMapper.findById(username).orElseThrow(
            ()-> new UsernameNotFoundException(
                    String.format("USER_NOT_FOUND", username)
            )));
		UserProfile userProfile = optUserProfile.get();
        List<Book> bookOffers = userProfile.getBookOffers();
        Book bookToRemove = null;
        

        for(Book bookOffer : bookOffers)
        {
            if(bookOffer.getOfferId() == bookId)
            {
                bookToRemove = bookOffer;
                break;
            }

            System.err.println("Book with id: " + bookId + " not found");
            return;
        }

        bookOffers.remove(bookToRemove);
        userProfile.setBookOffers(bookOffers);
        userProfileMapper.save(userProfile);
        bookMapper.deleteById(bookId);
    }

    





}
