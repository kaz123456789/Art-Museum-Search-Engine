package use_case.favorite;

import data.InMemoryDataAccessObject;
import entities.Artwork;
import entities.ArtworkFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FavoriteInteractorTest {

    @BeforeEach
    void beforeEach() {
        // artwork2 = ArtworkFactory.createArtwork("Madame X", "John Singer Sargent", "1884",
               // "MoMA", "", "starsnightbluefamous");
    }

    @Test
    public void favoriteSavedArtTest() {
        Artwork artwork1 = ArtworkFactory.createArtwork("Starry Night", "Vincent Van Gogh", "1889",
                "MoMA", "", "starsnightbluefamous");
        FavoriteInputData inputData = new FavoriteInputData(artwork1);
        FavoriteDataAccessInterface artworkRepository = new InMemoryDataAccessObject();

        artworkRepository.save(artwork1);

        FavoriteOutputBoundary successPresenter = new FavoriteOutputBoundary() {
            @Override
            public void prepareSuccessView(FavoriteOutputData outputData) {
                assertTrue(artworkRepository.getArtworkByTitle(artwork1.getTitle()).checkFavorited());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        FavoriteInputBoundary favoriteUseCase = new FavoriteInteractor(artworkRepository, successPresenter);
        favoriteUseCase.execute(inputData);
    }

    @Test
    public void favoriteNewArtTest() {
        Artwork artwork1 = ArtworkFactory.createArtwork("Starry Night", "Vincent Van Gogh", "1889",
                "MoMA", "", "starsnightbluefamous");
        FavoriteInputData inputData = new FavoriteInputData(artwork1);
        FavoriteDataAccessInterface artworkRepository = new InMemoryDataAccessObject();

        FavoriteOutputBoundary successPresenter = new FavoriteOutputBoundary() {
            @Override
            public void prepareSuccessView(FavoriteOutputData outputData) {
                assertTrue(artworkRepository.getArtworkByTitle(artwork1.getTitle()).checkFavorited());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        FavoriteInputBoundary favoriteUseCase = new FavoriteInteractor(artworkRepository, successPresenter);
        favoriteUseCase.execute(inputData);
    }
}