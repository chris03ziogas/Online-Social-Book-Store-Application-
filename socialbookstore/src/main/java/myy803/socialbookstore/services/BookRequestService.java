package myy803.socialbookstore.services;

import java.util.List;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;

public interface BookRequestService {

    public void request(String username, int bookId);
    public List<BookDto> getRequests(String username);
    public List<UserProfileDto> getRequestingUsersForBookOffer(int bookId);
    public void deleteRequest(int bookId, String username);
    //public void acceptRequest()
}
