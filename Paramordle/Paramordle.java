import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Paramordle {
    public static void main(String[] args) throws FileNotFoundException {
        File SongList = new File("SongData.txt"); // file with data for all the songs (title, album, length, etc)
        Scanner fileScanner = new Scanner(SongList);
        PSong[] songs = new PSong[68]; // array to store all the songs in

        // fills songs array with PSong objects
        int counter = 0;
        while(fileScanner.hasNextLine()) {
            String[] temp = fileScanner.nextLine().split("/");
            songs[counter] = new PSong(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), new ArrayList<>(Arrays.asList(temp[4].split(","))));
            counter ++;
        }
        fileScanner.close();

        PSong mystery = songs[(int)(Math.random() * counter)]; // chooses a song that the player has to guess
        System.out.println("Welcome to Paramordle! You have 6 tries to guess the mystery song from Paramore's discography");
        System.out.println("RULES AND GUIDELINES");
        System.out.println("Only songs from the standard version of each album are included (No AWKIF or Self Titled Deluxe)");
        System.out.println("* = correct, ^ = higher and ^^ = within 2 for track number and within 30 seconds for length (same applies for v and vv)");
        System.out.println("Song titles are not case or punctuation sensitive, abbreviations are allowed.");
        System.out.println("Have fun!");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        PSong guess; // guess will be initialized when the user inputs a valid song
        int tries = 5; // amount of turns the player has
        boolean guessed = false; // boolean to end loop if the guess is correct
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        
        while(tries >= 0 && !guessed) {
            String input = inputScanner.nextLine();
            // initializes guess to a song in the songs array if input is valid
            while(guess == null) {
                for(int i = 0; i < counter; i++)
                    if(songs[i].isName(input))
                        guess = songs[i];
                if(guess == null) {
                    System.out.println("Song not found. Please enter a valid song title: ");
                    input = inputScanner.nextLine();
                }
            }
            System.out.println(mystery.compareSong(guess) + String.format(" %s guesses left", tries));
            if(guess.equals(mystery)) guessed = true;
            guess = null;
            tries --;
        }
        inputScanner.close();
        if(guessed) System.out.println("Congrats! You guessed the right song!");
        else System.out.println(String.format("Sorry, but the song was %s. Thanks for playing!", mystery.getTitle()));
    }
}
