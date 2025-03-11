package org.openjfx.ui.board_objects;

import javafx.animation.FillTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.openjfx.ui.functions.Action;
import org.openjfx.ui.functions.Getter;
import org.openjfx.ui.functions.Predicate;

public class UIRoad {
    public Rectangle rectangle;
    private Transition fillTransition;
    private Paint fillPaint;

    public UIRoad(Rectangle rect) {
        rectangle = rect;
    }

    public void initialize(Getter<Paint> initialPaint, Getter<Paint> playerPaint,
                           Action onClick, Predicate shouldBlink) {
        this.fillPaint = initialPaint.get();
        rectangle.setFill(initialPaint.get());
        rectangle.setOnMouseClicked(event -> {
            if (shouldBlink.holds()) {
                fillPaint = playerPaint.get();
                if (fillTransition != null) {
                    fillTransition.setOnFinished(e -> rectangle.setFill(fillPaint));
                    fillTransition.stop();
                }
                rectangle.setFill(playerPaint.get());
            }
            onClick.act();
        });
        rectangle.setOnMouseEntered(event -> {
            onHover(initialPaint, playerPaint, shouldBlink);
        });
        rectangle.setOnMouseExited(event -> {
            if (fillTransition != null) {
                fillTransition.setOnFinished(e -> rectangle.setFill(fillPaint));
            }
        });
    }

    private void onHover(Getter<Paint> initialPaint, Getter<Paint> playerPaint, Predicate shouldBlink) {
        if (shouldBlink.holds()) {
            FillTransition transition = new FillTransition(Duration.millis(300), rectangle,
                    (Color) initialPaint.get(), (Color) playerPaint.get());
            transition.setCycleCount(2);
            transition.setAutoReverse(true);
            transition.setOnFinished(e -> {
                transition.play();
            });
            transition.play();
            fillTransition = transition;
        }
    }
}
