package org.openjfx.ui.dialog;

import game.GameHandler;
import game.Player;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.openjfx.ui.data.DevelopmentCardInfo;
import org.openjfx.ui.data.ResourceInfo;

import board.Road;
import board.location.BorderLocation;

public class RoadBuildingDialog {

    private Stage stage;
    private boolean isCanceled;
    private Road selected = null;
    private Button submitButton;
    private Player player;
    private GameHandler game;
    private static final int IMG_SIZE = 50;
    private ResourceBundle bundle;

    public RoadBuildingDialog(Window parent, Player player, GameHandler game, ResourceBundle bundle) {
        this.bundle = bundle;
        this.player = player;
        this.game = game;
        submitButton = new Button(bundle.getString("submit"));
        HBox buttonRow = new HBox();
        buttonRow.getChildren().addAll(submitButton);

        VBox superParent = new VBox();
        superParent.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        HBox content = new HBox();
        content.getChildren().add(createSelectBox());

        Text titleLabel = new Text(bundle.getString("selectRoad"));
        titleLabel.setFont(new Font(30));

        superParent.getChildren().addAll(titleLabel, content, buttonRow);

        var scene = new Scene(superParent);
        stage = new Stage();
        stage.setTitle(bundle.getString("selectRoad"));
        stage.initOwner(parent);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);

        submitButton.setOnMouseClicked(e -> {
            isCanceled = false;
            stage.close();
        });
        updateSubmitButton();
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public ArrayList<Road> getResources() {
        stage.showAndWait();
        ArrayList<Road> list = new ArrayList<>();
        list.add(selected);
        return list;
    }

    public List<Road> getAdjacentUnclaimedRoads(){
        List<Road> playerRoads = game.getBoard().getRoadsForPlayer(player);
        List<Road> adjacentRoads = new ArrayList<>();

        for(Road road: playerRoads){
            for(BorderLocation loc : road.getLocation().getBorders()){
                if(game.getBoard().getRoadAt(loc).getOwner() == null){
                    adjacentRoads.add(game.getBoard().getRoadAt(loc));
                }
            }
        }

        return adjacentRoads;
    }

    private VBox createSelectBox() {
        VBox box = new VBox();
        box.getChildren().add(new Text(bundle.getString("selectRoad")));
        ToggleGroup toggleGroup = new ToggleGroup();
        box.setStyle("-fx-padding: 12px; -fx-spacing: 5px;");

        List<Road> adjacentRoads = getAdjacentUnclaimedRoads();

        for (Road road : adjacentRoads) {
            HBox rbox = new HBox();
            Text label = new Text(bundle.getString("roadAtCol") + " " + road.getLocation().getCol() +
                    " " + bundle.getString("roadAtRow") + " " + road.getLocation().getRow());

            RadioButton radioButton = new RadioButton();
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnMouseClicked(e -> {
                selected = road;
                updateSubmitButton();
            });


            rbox.getChildren().addAll(radioButton, label);
            box.getChildren().add(rbox);
        }

        return box;
    }

    private void updateSubmitButton() {
        submitButton.setDisable(selected == null);
    }

}
