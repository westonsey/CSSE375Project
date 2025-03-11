package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.openjfx.ui.functions.ParameterizedAction;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageController {

    @FXML
    private ComboBox<String> languageOptions;

    private ParameterizedAction<ResourceBundle> onLanguageChanged;

    public LanguageController() {

    }

    public void setLanguageChangeHandler(ParameterizedAction<ResourceBundle> handler) {
        onLanguageChanged = handler;
    }

    public void initialize(){
        languageOptions.getItems().addAll("English", "Korean");
        languageOptions.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                this.changeLanguage(newValue);
            }
        });
    }

    public void changeLanguage(String language){
        if (language.equals("English")){
            ResourceBundle bundle = ResourceBundle.getBundle("CATAN");
            onLanguageChanged.act(bundle);
        } else {
            ResourceBundle bundle = ResourceBundle.getBundle("CATAN", Locale.KOREA);
            onLanguageChanged.act(bundle);
        }
    }

}
