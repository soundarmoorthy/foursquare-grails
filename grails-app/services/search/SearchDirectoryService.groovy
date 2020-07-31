package search

import grails.gorm.transactions.Transactional
import search.directory.ISearchProvider
import search.directory.SearchResult
import search.directory.implementations.foursquare.FourSquareSearchProvider

@Transactional
class SearchDirectoryService {

    //TODO : This is the concept, but the actual implementation
    //can be much more performant
    public List<ISearchProvider> get(String city)
    {
        List<ISearchProvider> toProcess = new  ArrayList<>();
        RegisteredProviders().each {p ->
            if(p.serves(city))
                toProcess.add(p);
        }
        return toProcess;
    }

    private List<ISearchProvider> RegisteredProviders()
    {
        List<ISearchProvider> providers = new  ArrayList<>();
        providers.add(new FourSquareSearchProvider());
        return providers;
    }
}
