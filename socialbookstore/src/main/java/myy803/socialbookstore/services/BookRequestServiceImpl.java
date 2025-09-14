package myy803.socialbookstore.services;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;

@Service
public class BookRequestServiceImpl implements BookRequestService {

    @Autowired
	private BookMapper bookMapper;

    @Autowired
	private UserProfileMapper userProfileMapper;

    @Override
    public void request(String username, int bookId) {
        
        Optional<Book> requestedBook = Optional.of(bookMapper.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException(
                    "Book with ID " + bookId + " not found")
                ));

		Optional<UserProfile> userProfile = Optional.of(userProfileMapper.findById(username).orElseThrow(
            ()-> new UsernameNotFoundException(
                    String.format("USER_NOT_FOUND", username)
            )));

		requestedBook.get().addRequestingUser(userProfile.get());
		bookMapper.save(requestedBook.get());

    }

    @Override
    public List<BookDto> getRequests(String username) {
        
        Optional<UserProfile> userProfile = Optional.of(userProfileMapper.findById(username).orElseThrow(
            ()-> new UsernameNotFoundException(
                    String.format("USER_NOT_FOUND", username)
            )));

		List <BookDto> requests = userProfile.get().buildBookRequestsDtos();

        return requests;
    }

    @Override
    public List<UserProfileDto> getRequestingUsersForBookOffer(int bookId) {
        
        Optional<Book> book = Optional.of(bookMapper.findById(bookId).orElseThrow(
            () -> new EntityNotFoundException(
                "Book with ID " + bookId + " not found")
            ));
            
    	List<UserProfileDto> requestingUsers = book.get().getRequestingUserProfileDtos();

        return requestingUsers;
    }

    @Override
    public void deleteRequest(int bookId, String username)
    {
        Optional<UserProfile> optuserProfile = Optional.of(userProfileMapper.findById(username).orElseThrow(
            ()-> new UsernameNotFoundException(
                    String.format("USER_NOT_FOUND", username)
            )));
        
        Optional<Book> optBook = Optional.of(bookMapper.findById(bookId).orElseThrow(
            () -> new EntityNotFoundException(
                "Book with ID " + bookId + " not found")
            ));
        UserProfile userProfile =  optuserProfile.get();
        Book book = optBook.get();
        
        book.removeRequestingUser(userProfile);
        userProfileMapper.save(userProfile);

    }


}
