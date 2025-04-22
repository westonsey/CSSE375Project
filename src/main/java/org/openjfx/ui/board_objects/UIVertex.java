package org.openjfx.ui.board_objects;

import board.BuildingType;
import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
    private boolean isUpgraded;
    private static final float UPGRADED_SIZE_FACTOR = 1.55f;
    private Paint fillPaint;

    private static final String PATH_PREFIX = "../../images/";

    public UIVertex(Rectangle rect) {
        rectangle = rect;
        isUpgraded = false;
    }

    public void initialize(Getter<Paint> initialPaint, Getter<Paint> playerPaint,
                           Action onClick, Predicate shouldBlink, Predicate shouldGrow,
                           Getter<BuildingType> upgradeType, Getter<Color> playerColor) {
        fillPaint = initialPaint.get();
        rectangle.setFill(initialPaint.get());
        rectangle.setOnMouseClicked(event -> {
            if (shouldBlink.holds() || shouldGrow.holds()) {
                this.onClick(playerPaint, upgradeType, playerColor);
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

    private void onClick(Getter<Paint> playerPaint, Getter<BuildingType> upgradeType, Getter<Color> playerColor) {
        if (isUpgraded) {
            rectangle.setScaleX(UPGRADED_SIZE_FACTOR);
            rectangle.setScaleY(UPGRADED_SIZE_FACTOR);
            if (sizeTransition != null) {
                sizeTransition.setOnFinished(e -> {
                    rectangle.setScaleX(UPGRADED_SIZE_FACTOR);
                    rectangle.setScaleY(UPGRADED_SIZE_FACTOR);
                });
                sizeTransition.stop();
            }

            Image image = new Image(getClass().getResource(PATH_PREFIX + upgradeType.get().getName() + ".png").toString());
            image = tintImage(image, playerColor.get());
            rectangle.setFill(new ImagePattern(image));
            rectangle.setStrokeWidth(0);

        } else {
            fillPaint = playerPaint.get();
            if (fillTransition != null) {
                fillTransition.setOnFinished(e -> rectangle.setFill(fillPaint));
                fillTransition.stop();
            }
            rectangle.setFill(playerPaint.get());
            isUpgraded = true;
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
            transition.setToX(UPGRADED_SIZE_FACTOR);
            transition.setToY(UPGRADED_SIZE_FACTOR);
            transition.setCycleCount(2);
            transition.setAutoReverse(true);
            transition.setOnFinished(e -> transition.play());
            transition.play();
            sizeTransition = transition;
        }
    }

    private Image tintImage(Image src, Color tintColor) {
        int width = (int) src.getWidth();
        int height = (int) src.getHeight();
        WritableImage tinted = new WritableImage(width, height);
        PixelReader reader = src.getPixelReader();
        PixelWriter writer = tinted.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                Color tintedColor;
                if (color.getRed() == 0 && color.getBlue() == 0 && color.getGreen() == 0) {
                    tintedColor = color;
                } else {
                    tintedColor = color.interpolate(tintColor, color.getOpacity());
                }
                writer.setColor(x, y, tintedColor);
            }
        }

        return tinted;
    }
}
