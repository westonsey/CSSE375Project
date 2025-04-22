package org.openjfx.ui;

import game.PlayerColors;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Map;

public class PlayerColorUtils {

    public static final Map<PlayerColors, Paint> PLAYER_PAINTS = Map.of(
            PlayerColors.RED, Paint.valueOf("#c72d1c"),
            PlayerColors.BLUE, Paint.valueOf("#1f8bf0"),
            PlayerColors.YELLOW, Paint.valueOf("#cc8d3b"),
            PlayerColors.WHITE, Paint.valueOf("#f5efe9"),
            PlayerColors.GREEN, Paint.valueOf("#52ad3b"),
            PlayerColors.PINK, Paint.valueOf("#df7cdd")
    );

    public static final Map<PlayerColors, String> PLAYER_NAMES = Map.of(
            PlayerColors.RED, "Red",
            PlayerColors.BLUE, "Blue",
            PlayerColors.YELLOW, "Yellow",
            PlayerColors.WHITE, "White",
            PlayerColors.GREEN, "Green",
            PlayerColors.PINK, "Pink"
    );

    public static final Map<PlayerColors, Color> PLAYER_COLORS = Map.of(
            PlayerColors.RED, Color.valueOf("#c72d1c"),
            PlayerColors.BLUE, Color.valueOf("#1f8bf0"),
            PlayerColors.YELLOW, Color.valueOf("#cc8d3b"),
            PlayerColors.WHITE, Color.valueOf("#f5efe9"),
            PlayerColors.GREEN, Color.valueOf("#52ad3b"),
            PlayerColors.PINK, Color.valueOf("#df7cdd")
    );

}
