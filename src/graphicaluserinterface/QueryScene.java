package graphicaluserinterface;

import database.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QueryScene extends UserScene {
    Scene scene;
    private Label sourceLabel;
    private Label destinationLabel;
    private Label dateLabel;
    private ComboBox sourceComboBox;
    private ComboBox destinationComboBox;
    private DatePicker journeyDate;
    private GridPane dataGridLayout;
    private VBox layout;
    Button button;

    public QueryScene() {
        sourceLabel = new Label("Source");
        destinationLabel = new Label("Destination");
        dateLabel = new Label("Date of departure");
        journeyDate = new DatePicker();
        sourceComboBox = new ComboBox();
        destinationComboBox = new ComboBox();
        dataGridLayout = new GridPane();
        layout = new VBox();
        button = new Button("Search Flights!");

        sourceLabel.setMinWidth(100);
        destinationLabel.setMinWidth(100);
        dateLabel.setMinWidth(100);
        sourceComboBox.setMinWidth(160);
        destinationComboBox.setMinWidth(160);
        journeyDate.setMinWidth(100);
        button.setMinWidth(100);

        journeyDate.setPromptText("Pick a date");
        journeyDate.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate)
            {
                if(localDate==null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString)
            {
                if(dateString==null || dateString.trim().isEmpty())
                {
                    return null;
                }
                return LocalDate.parse(dateString,dateTimeFormatter);
            }
        });


        sourceComboBox.getItems().addAll(CityData.getCITIES());
        destinationComboBox.getItems().addAll(CityData.getCITIES());

        GridPane.setConstraints(sourceLabel, 0, 0);
        GridPane.setConstraints(sourceComboBox,1, 0);
        GridPane.setConstraints(destinationLabel, 0, 1);
        GridPane.setConstraints(destinationComboBox, 1, 1);
        GridPane.setConstraints(dateLabel, 0, 2);
        GridPane.setConstraints(journeyDate, 1, 2);

        dataGridLayout.getChildren().addAll(sourceLabel, sourceComboBox, destinationLabel, destinationComboBox, dateLabel, journeyDate);
        dataGridLayout.setPadding(new Insets(10));
        dataGridLayout.setVgap(10);
        dataGridLayout.setHgap(10);

        layout.getChildren().addAll(dataGridLayout, button);
        layout.setPadding(new Insets(10, 10, 20, 10));
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout, 300, 200);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    public Button getSearchButton(){
        return button;
    }

    public String getSourceString(){
        return sourceComboBox.getSelectionModel().getSelectedItem().toString();
    }
    public String getDestinationString(){
        return destinationComboBox.getSelectionModel().getSelectedItem().toString();
    }

    public String getDateOfTravel(){
        return journeyDate.getValue().toString();
    }
}
