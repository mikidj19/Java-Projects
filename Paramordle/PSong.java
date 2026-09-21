import java.util.ArrayList;

public class PSong {
    private String title;
    private int album;
    private int track;
    private int length;
    private String[] albums = {"All We Know Is Falling", "Riot!", "Brand New Eyes", "Paramore", "After Laughter", "This Is Why"};
    private ArrayList<String> acceptableNames;
    private final String UP = "^";
    private final String DOWN = "v";

    public PSong(String t, int a, int n, int l, ArrayList<String> ns) {
        title = t;
        album = a;
        track = n;
        length = l;
        acceptableNames = ns;
        acceptableNames.add(0, title);
    }

    public String getTitle() {
        return title;
    }

    public int getAlbum() {
        return album;
    }

    public String getAlbumF() {
        return albums[album-1];
    }

    public int getTrack() {
        return track;
    }

    public int getLength() {
        return length;
    }

    public String getLengthF() {
        return String.format("%d:%02d", length / 60, length % 60);
    }

    public ArrayList<String> getAcceptableNames() {
        return acceptableNames;
    }

    public boolean isName(String s) {
        for(String name: acceptableNames)
            if(name.toUpperCase().equals(s.toUpperCase()))
                return true;
        return false;
    }

    public String toString() {
        return String.format("| %s | %s | %d | %s |", title, this.getAlbumF(), track, this.getLengthF());
    }

    // s is the input song
    public String compareSong(PSong s) {
        String albumIndicator;
        String trackIndicator;
        String lengthIndicator;
        // comparing albums
        if(s.album < this.album)
            albumIndicator = UP;
        else if (s.album > this.album)
            albumIndicator = DOWN;
        else albumIndicator = "*";
        // comparing track number
        if(s.track < this.track) {
            if(this.track - s.track <= 2)
                trackIndicator = UP + UP;
            else trackIndicator = UP;
        }
        else if(s.track > this.track) {
            if(s.track - this.track <= 2)
                trackIndicator = DOWN + DOWN;
            else trackIndicator = DOWN;
        }
        else trackIndicator = "*";
        // comapring length
        if(s.length < this.length) {
            if(this.length - s.length <= 30)
                lengthIndicator = UP + UP;
            else lengthIndicator = UP;
        }
        else if(s.length > this.length) {
            if(s.length - this.length <= 30)
                lengthIndicator = DOWN;
            else lengthIndicator = DOWN;
        }
        else lengthIndicator = "*";
        return String.format("| %s | %s %s | %d %s | %s %s |", s.title, s.getAlbumF(), albumIndicator, s.track, trackIndicator, s.getLengthF(), lengthIndicator); 
    }
    // ↑ ↓
}