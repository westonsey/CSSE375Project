package org.openjfx.ui.board_objects;

import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.openjfx.ui.functions.Action;
import org.openjfx.ui.functions.Getter;
import org.openjfx.ui.functions.Predicate;

public class UIVertex {
    private Rectangle rectangle;
    private Transition fillTransition;
    private Transition sizeTransition;
    private boolean isCity;
    private static final float CITY_SIZE_FACTOR = 1.55f;
    private Paint fillPaint;

    public UIVertex(Rectangle rect) {
        rectangle = rect;
        isCity = false;
    }

    public void initialize(Getter<Paint> initialPaint, Getter<Paint> playerPaint,
                           Action onClick, Predicate shouldBlink, Predicate shouldGrow) {
        fillPaint = initialPaint.get();
        rectangle.setFill(initialPaint.get());
        rectangle.setOnMouseClicked(event -> {
            if (shouldBlink.holds() || shouldGrow.holds()) {
                this.onClick(initialPaint, playerPaint, shouldBlink, shouldGrow);
            }
            onClick.act();

            // May need to start hovering for the city since we still have the mouse on the vertex
            onHover(initialPaint, playerPaint, shouldBlink, shouldGrow);
        });
        rectangle.setOnMouseEntered(event -> {
            onHover(initialPaint, playerPaint, shouldBlink, shouldGrow);
        });
        rectangle.setOnMouseExited(event -> {
            if (fillTransition != null) {
                fillTransition.setOnFinished(e -> rectangle.setFill(fillPaint));
            }
            if (sizeTransition != null) {
                sizeTransition.setOnFinished(null);
            }
        });
    }

    private void onClick(Getter<Paint> initialPaint, Getter<Paint> playerPaint,
                         Predicate shouldBlink, Predicate shouldGrow) {
        if (isCity) {
            rectangle.setScaleX(CITY_SIZE_FACTOR);
            rectangle.setScaleY(CITY_SIZE_FACTOR);
            if (sizeTransition != null) {
                sizeTransition.setOnFinished(e -> {
                    rectangle.setScaleX(CITY_SIZE_FACTOR);
                    rectangle.setScaleY(CITY_SIZE_FACTOR);
                });
                sizeTransition.stop();
            }

        } else {
            fillPaint = playerPaint.get();
            if (fillTransition != null) {
                fillTransition.setOnFinished(e -> rectangle.setFill(fillPaint));
                fillTransition.stop();
            }
            rectangle.setFill(playerPaint.get());
            isCity = true;
        }
    }

    private void onHover(Getter<Paint> initialPaint, Getter<Paint> playerPaint,
                         Predicate shouldBlink, Predicate shouldGrow) {
        if (shouldBlink.holds()) {
            FillTransition transition = new FillTransition(Duration.millis(300), rectangle, (Color) initialPaint.get(),
                    (Color) playerPaint.get());
            transition.setCycleCount(2);
            transition.setAutoReverse(true);
            transition.setOnFinished(e -> {
                transition.play();
            });
            transition.play();
            fillTransition = transition;
        }

        if (shouldGrow.holds()) {
            ScaleTransition transition = new ScaleTransition(Duration.millis(250), rectangle);
            transition.setFromX(1);
            transition.setFromY(1);
            transition.setToX(CITY_SIZE_FACTOR);
            transition.setToY(CITY_SIZE_FACTOR);
            transition.setCycleCount(2);
            transition.setAutoReverse(true);
            transition.setOnFinished(e -> transition.play());
            transition.play();
            sizeTransition = transition;
        }
    }
}
