package entities;

public class MuseumFactory {
    public static Artwork createArtwork(String title, String artistName, String timePeriod,
                                        String genre, String gallery, String imageUrl,
                                        String keyWords) {
        return new Artwork(title, artistName, timePeriod, gallery, imageUrl, keyWords);
    }


    public static Artwork createArtwork(String title, String artistName) {
        return new Artwork(title, artistName, "Unknown Time Period",
                "Unknown Genre", "Unknown Gallery", title);
    }
}
