package org.openjfx;

import org.openjfx.ui.functions.Action;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WeatherSetupController {
	@FXML private Button yesButton;
	@FXML private Button noButton;
	private ResourceBundle bundle;
	private boolean choice;

	public WeatherSetupController(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public void initialize() {
		yesButton.setText(bundle.getString("weatherOn"));
		noButton.setText(bundle.getString("weatherOff"));
	}

	public void setWeatherChoiceHandler(Action handler) {
		yesButton.setOnMouseClicked(e -> {
			handler.act();
			choice = true;
		});
		noButton.setOnMouseClicked(e -> {
			handler.act();
			choice = false;
		});
	}

	public boolean getWeather() {
		return choice;
	}
}
