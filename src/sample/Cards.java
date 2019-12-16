package sample;

import javafx.scene.image.Image;

public class Cards {
    private Image frontSide;

    public void setFrontSide(Image frontSide) {
        this.frontSide = frontSide;
    }

    public Image flipToFront() {
        return frontSide;
    }
}
