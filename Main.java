import com.spotify.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = createUser("Hashem_Nickravan", "Hashem1380");
        createUserWithException("MohamadHossein", "ICPC2026");
        createUserWithException("Arman Hosseini", "1111");

        User artist = createUser("Ed_Sheeran", "1234");
        Music music1 = createMusic("Azizam", artist);
        Music music2 = createMusic("Bad_Habits", artist);

        testPlayLimit(user1, music1);
        upgradeToPremium(user1);
        Playlist playlist = user1.getPlaylists().get(0);

        testPlaylistManagement(playlist, music1, user1);
        testSocialFeatures(user1, artist);
        testMusicSearchFeatures(artist, music2);
    }

    private static User createUser(String username, String password) {
        try {
            return new User(username, password);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static void createUserWithException(String username, String password) {
        try {
            new User(username, password);
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Music createMusic(String title, User artist) {
        try {
            return new Music(title, artist);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    private static void testPlayLimit(User user, Music track) {
        if (user == null || track == null)
            return;

        System.out.println("\nPlay Limit Test: ");
        for (int i = 0; i < 6; i++) {
            try {
                user.getBehavior().playMusic(track);
                System.out.println("Play: " + (i+1) + " success");
            } catch (InvalidOperationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void upgradeToPremium(User user) {
        if (user == null)
            return;

        System.out.println("\nPremium Upgrade");
        user.buyPremium(12);
        user.createPlaylist("### MyTopMusics ###");
        System.out.println("Playlist : " + user.getPlaylists().get(0).getTitle());
    }

    private static void testPlaylistManagement(Playlist playlist, Music track, User user) {
        if (playlist == null || track == null || user == null)
            return;

        System.out.println("\n### Playlist Tests ###");

        try {
            playlist.editTitle("Best Tracks", user.getPassword());
            System.out.println("Renamed to: " + playlist.getTitle());
        } catch (InvalidOperationException e) {
            System.out.println("Rename error: " + e.getMessage());
        }

        try {
            playlist.editTitle("Best of 1403", "Mohammad12345");
        } catch (InvalidOperationException e) {
            System.out.println("Edit error: " + e.getMessage());
        }

        System.out.println("Playlist owner: " + playlist.getOwner().getUsername());

        try {
            playlist.addMusic(track, user.getPassword());
            System.out.println("Track added Counter: " + playlist.getPlaylist().size());
        } catch (InvalidOperationException e) {
            System.out.println("Add error: " + e.getMessage());
        }
    }

    private static void testSocialFeatures(User user, User artist) {
        if (user == null || artist == null)
            return;

        System.out.println("\n### Social Features ###");
        try {
            user.follow(artist);
            System.out.println("Follow success - Following: " + user.getFollowingList().size() + " Followers: " + artist.getFollowerList().size());
        } catch (InvalidOperationException e) {
            System.out.println("Follow error: " + e.getMessage());
        }
    }

    private static void testMusicSearchFeatures(User artist, Music track) {
        if (artist == null || track == null)
            return;

        System.out.println("\n### Music Search ###");
        List<Music> results = Music.search("Azizam");
        System.out.println("Search results: " + results.size());

        Music found = Music.search("Bad_Habits", artist);
        if (found != null) {
            System.out.println("found");
        } else {
            System.out.println("not found:(");
        }
    }
}