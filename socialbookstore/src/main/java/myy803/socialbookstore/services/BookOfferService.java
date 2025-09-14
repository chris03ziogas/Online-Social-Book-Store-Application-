package myy803.socialbookstore.services;

import java.util.List;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.formsdata.BookDto;

public interface BookOfferService {

    public List<BookDto> listBookOffers(String username);
    public List<BookCategory> getAllCategories();
    public void saveOffer(String username, BookDto bookOfferDto);
    public void deleteOffer(int bookId, String username);

}
