package org.openjfx.ui;

import game.PlayerColors;
import javafx.scene.paint.Paint;

import java.util.Map;

public class PlayerColorUtils {

    public static final Map<PlayerColors, Paint> PLAYER_PAINTS = Map.of(
            PlayerColors.RED, Paint.valueOf("#c72d1c"),
            PlayerColors.BLUE, Paint.valueOf("#1f8bf0"),
            PlayerColors.YELLOW, Paint.valueOf("#cc8d3b"),
            PlayerColors.WHITE, Paint.valueOf("#f5efe9")
    );

    public static final Map<PlayerColors, String> PLAYER_NAMES = Map.of(
            PlayerColors.RED, "Red",
            PlayerColors.BLUE, "Blue",
            PlayerColors.YELLOW, "Yellow",
            PlayerColors.WHITE, "White"
    );

}
