package myy803.socialbookstore.services;

import java.util.List;

import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.formsdata.SearchDto;

public interface SearchAndRecommendService {

    public List<BookDto> search(SearchDto searchDto);
    public List<BookDto> recommend(String username, RecommendationsDto recomDto);
}
