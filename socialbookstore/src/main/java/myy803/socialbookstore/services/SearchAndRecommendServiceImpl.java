package myy803.socialbookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsFactory;
import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsStrategy;
import myy803.socialbookstore.datamodel.searchstrategies.SearchFactory;
import myy803.socialbookstore.datamodel.searchstrategies.SearchStrategy;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;

@Service
public class SearchAndRecommendServiceImpl implements SearchAndRecommendService {

    @Autowired
	private BookMapper bookMapper;

    @Autowired
	private SearchFactory searchFactory;

    @Autowired
	private RecommendationsFactory recommendationsFactory;

    @Override
    public List<BookDto> search(SearchDto searchDto)
    {
        SearchStrategy searchStrategy = searchFactory.create(searchDto.getSelectedStrategy());
    	List<BookDto> bookDtos = searchStrategy.search(searchDto, bookMapper);

        return bookDtos;
    }

    @Override
    public List<BookDto> recommend(String username, RecommendationsDto recomDto) 
    {
        RecommendationsStrategy recommendationsStrategy = recommendationsFactory.create(recomDto.getSelectedStrategy());
    	List<BookDto> bookDtos = recommendationsStrategy.recommend(username);

        return bookDtos;
    }

}
