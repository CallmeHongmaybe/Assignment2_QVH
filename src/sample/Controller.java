package sample;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    @FXML
    private GridPane cardDeck;
    @FXML
    private ChoiceBox difficultyChoice;
    @FXML
    private Text time;
    @FXML
    private ImageView image01, image02, image03, image04, image05, image06, image07, image08, image09, image10,
            image11, image12, image13, image14, image15, image16, image17, image18, image19, image20;


    URL musicURL = getClass().getResource("buble_christmas_song.mp3");
    Media mp3MusicFile = new Media(musicURL.toExternalForm());
    MediaPlayer musicPlayer = new MediaPlayer(mp3MusicFile);
    int MUSIC_TOGGLED = 0;
    int timeToFade = 0;
    Image firstCard, secondCard;
    Node firstCardButton = null, secondCardButton = null;

    private Timer timer = new Timer();

    // loading players as cards to the playerTally array list
    ArrayList<Cards> playerTally = new ArrayList<>();
    ArrayList<Cards> playerTallyCopy = new ArrayList<>();

    @FXML    // initial setup of the game
    public void initialize() throws FileNotFoundException {
        loadPlayers();
        setUpDifficulty();
        cardDeck.setDisable(true);
    }

    // restarting and reshuffle the cards
    public void setNextGame() {
        System.out.println("Next game boys");
    }

    // quitting the game
    public void quitGame() {
        System.exit(1);
    }

    // creating toggle music button
    public void toggleMusic() {
        musicPlayer.setVolume(0.9);
        ++MUSIC_TOGGLED;

        if (MUSIC_TOGGLED > 1) {
            MUSIC_TOGGLED = 0;
        }
        switch (MUSIC_TOGGLED) {
            case 0:
                musicPlayer.pause();
                break;
            case 1:
                musicPlayer.play();
                break;
        }

        // repeating the music
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.setOnEndOfMedia(() -> musicPlayer.seek(Duration.ZERO));
    }

    // constructing the level button. Upon being clicked the button locks itself and starts the timer
    public void setUpDifficulty() {
        difficultyChoice.getSelectionModel().selectedItemProperty().addListener((one, oldValue, newValue) -> {
            setDifficultyBasedOn(newValue);
            cardDeck.setDisable(false);
            shufflingCards();
            difficultyChoice.setDisable(true);
            timer.tick(time);
        });
    }

    private void setDifficultyBasedOn(Object newValue) {
        String difficulty = newValue.toString().substring(12);
        switch (difficulty) {
            case "Easy":
                timeToFade = 3;
                break;
            case "Medium":
                timeToFade = 2;
                break;
            case "Hard":
                timeToFade = 1;
                break;
        }
    }

    // loading all players to an array list and then creating a clone version from it
    public void loadPlayers() throws FileNotFoundException {
        for (int i = 1; i <= 10; i++) {
            String playerFolder = "src/sample/Players/";
            FileInputStream playerStream = new FileInputStream(playerFolder + "Player" + i + ".jpg");
            Image playerImage = new Image(playerStream);
            Cards newPlayer = new Cards();
            newPlayer.setFrontSide(playerImage);
            playerTally.add(newPlayer);
        }
        playerTallyCopy = (ArrayList<Cards>) playerTally.clone();
    }

    // shuffling all 20 cards
    public void shufflingCards() {
        Collections.shuffle(playerTally);
        Collections.shuffle(playerTallyCopy);
        shuffleImages(playerTally, image01, image02, image03, image04, image05, image06, image07, image08, image09, image10);
        shuffleImages(playerTallyCopy, image11, image12, image13, image14, image15, image16, image17, image18, image19, image20);
    }

    // shuffle a group of 10 cards
    private void shuffleImages(ArrayList<Cards> playerTallyCopy, ImageView image1, ImageView image2, ImageView image3, ImageView image4, ImageView image5, ImageView image6, ImageView image7, ImageView image8, ImageView image9, ImageView image10) {
        image1.setImage(playerTallyCopy.get(0).flipToFront());
        image2.setImage(playerTallyCopy.get(1).flipToFront());
        image3.setImage(playerTallyCopy.get(2).flipToFront());
        image4.setImage(playerTallyCopy.get(3).flipToFront());
        image5.setImage(playerTallyCopy.get(4).flipToFront());
        image6.setImage(playerTallyCopy.get(5).flipToFront());
        image7.setImage(playerTallyCopy.get(6).flipToFront());
        image8.setImage(playerTallyCopy.get(7).flipToFront());
        image9.setImage(playerTallyCopy.get(8).flipToFront());
        image10.setImage(playerTallyCopy.get(9).flipToFront());
        playerTallyCopy.clear();
    }

    // adding event to the cards
    @FXML
    int cardsClicked = 0;

    @FXML
    private void onCardClickedEvent(MouseEvent event) {
        Node chosenButton = (Node) event.getSource();
        ImageView getImageFromButton = (ImageView) chosenButton;

        cardsClicked++;
        System.out.println("cardsClicked = " + cardsClicked);

        if (cardsClicked == 1) {
            firstCardButton = chosenButton;
            firstCard = getImageFromButton.getImage();
            flip(firstCardButton).stop();
        }

        else if (cardsClicked == 2) {
            secondCardButton = chosenButton;
            secondCard = getImageFromButton.getImage();

            if (firstCard.equals(secondCard)) {
                if (firstCardButton.getId().equals(secondCardButton.getId())) {
                    firstCardButton.setDisable(false);
                }
                else {
                    flip(secondCardButton).stop();
                    firstCardButton.setDisable(true);
                    secondCardButton.setDisable(true);
                }
            }
            else {
                flip(firstCardButton).play();
                flip(secondCardButton).play();
            }
            firstCard = null;
            secondCard = null;
            cardsClicked = 0;
        }
    }

    // a function to displaying photos
    private FadeTransition flip(Node node) {
        node.setOpacity(1);
        FadeTransition cardTransition = new FadeTransition(Duration.seconds(timeToFade), node);
        cardTransition.setFromValue(1);
        cardTransition.setToValue(0);
        return cardTransition;
    }

}












