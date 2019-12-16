package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Timer {
    private Timeline timeRemaining;
    private int minutes, seconds, centiseconds;

    public void tick(Text label) {
        timeRemaining = new Timeline();

        minutes = 1;
        seconds = 59;
        centiseconds = 100;
        timeRemaining.setCycleCount(Timeline.INDEFINITE);
        timeRemaining.getKeyFrames().add(
           new KeyFrame(Duration.millis(10),
                   actionEvent -> {
                       --centiseconds;
                       if (centiseconds == 0) {
                           seconds--;
                           centiseconds = 100;
                       } else if (seconds == 0) {
                           minutes--;
                           seconds = 59;
                       }
                       if (minutes < 0) {
                           timeRemaining.stop();
                           label.setText("Time's up!");
                       }
                       label.setText(minutes + " : " + numberFormatting(seconds) + " : " + numberFormatting(centiseconds));
                   }
              )
        );
    timeRemaining.playFromStart();
    }

    // formatting numbers
    private String numberFormatting(int num) {
        if (num < 10) {
            return "0" + num;
        }
        return num + "";
    }
}
