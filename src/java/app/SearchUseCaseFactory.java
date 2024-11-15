package app;

import data.MuseumDataAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.search.SearchController;
import interface_adapters.search.SearchPresenter;
import interface_adapters.search.SearchViewModel;
import use_case.search.*;
import view.SearchView;

public class SearchUseCaseFactory {

    public static SearchView create(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
                                    MuseumDataAccessObject museumDataAccessObject) {
        final SearchController searchController = createUserSignupUseCase(viewManagerModel, searchViewModel, museumDataAccessObject);
        return new SearchView(searchController, searchViewModel);
    }

    private static SearchController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            SearchViewModel searchViewModel,
                                                            MuseumDataAccessObject museumDataAccessObject) {
        final SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, searchViewModel);
        final SearchInputBoundary searchInteractor = new SearchInteractor(museumDataAccessObject, searchOutputBoundary);

        return new SearchController(searchInteractor);
    }
}
