package app;
import data.InMemoryDataAccessObject;
import data.MuseumDataAccessObject;
import entities.Artwork;
import interface_adapters.ViewManagerModel;
import interface_adapters.click_art.ClickArtController;
import interface_adapters.click_art.ClickArtPresenter;
import interface_adapters.click_art.ClickArtViewModel;
import interface_adapters.search.SearchViewModel;
import use_case.click_art.ClickArtInteractor;
import use_case.click_art.ClickArtOutputBoundary;
import use_case.search.SearchDataAccessInterface;
import view.ClickView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        // Create the main JFrame
        final JFrame frame = new JFrame("Museum Search Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up CardLayout for view switching
        final CardLayout cardLayout = new CardLayout();
        final JPanel views = new JPanel(cardLayout);
        frame.setContentPane(views); // Add to frame as content pane

        // View manager model for controlling states
        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // Search view setup
        final SearchViewModel searchViewModel = new SearchViewModel();
        final MuseumDataAccessObject museumDataAccessObject = new MuseumDataAccessObject();
        final ClickArtViewModel clickArtViewModel = new ClickArtViewModel();
        final SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, museumDataAccessObject, clickArtViewModel);

        // ClickArt view setup
        final ClickArtPresenter clickArtPresenter = new ClickArtPresenter(searchViewModel, clickArtViewModel, viewManagerModel);
        final ClickView clickView = ClickUseCaseFactory.create(viewManagerModel, searchViewModel, clickArtViewModel, museumDataAccessObject);

        // Add views to CardLayout
        views.add(searchView, searchView.getViewName());
        views.add(clickView, clickView.getViewName());

        // Set the initial state to SearchView
        viewManagerModel.setState(searchView.getViewName());
        viewManagerModel.firePropertyChanged();

        // Configure frame for full-screen mode
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        frame.setUndecorated(false); // Keep window decorations like title bar
        frame.setVisible(true);
    }
}


