package graphicaluserinterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FlightBookingScene extends UserScene {

    Label passengerNameLabel;
    Label passengerAgeLabel;
    TextField passengerName;
    TextField passengerAge;
    RadioButton bussinessClassOption;
    RadioButton economyClassOption;
    ToggleGroup classGroup;
    Button backButton;
    Button bookButton;
    GridPane detailsLayout = new GridPane();
    Scene scene;

    public FlightBookingScene(){
        passengerNameLabel = new Label("Passenger name");
        passengerAgeLabel = new Label("Passenger age");
        passengerName = new TextField();
        passengerAge = new TextField();
        bussinessClassOption = new RadioButton("Business class");
        economyClassOption = new RadioButton("Economy class");
        classGroup = new ToggleGroup();
        backButton = new Button("Back");
        bookButton = new Button("Book");
        passengerNameLabel.setMinWidth(50);
        passengerAgeLabel.setMinWidth(50);
        passengerName.setMinWidth(100);
        passengerAge.setMinWidth(100);

        bussinessClassOption.setToggleGroup(classGroup);
        economyClassOption.setToggleGroup(classGroup);

        backButton.setMinWidth(100);
        backButton.setPadding(new Insets(3));
        bookButton.setMinWidth(100);
        bookButton.setPadding(new Insets(3));

        GridPane.setConstraints(passengerNameLabel, 0, 0);
        GridPane.setConstraints(passengerName, 1, 0);
        GridPane.setConstraints(passengerAgeLabel, 0, 1);
        GridPane.setConstraints(passengerAge, 1, 1);
        GridPane.setConstraints(bussinessClassOption, 0, 2);
        GridPane.setConstraints(economyClassOption, 1, 2);
        GridPane.setConstraints(backButton, 0, 3);
        GridPane.setConstraints(bookButton, 1, 3);

        detailsLayout.setPadding(new Insets(10));
        detailsLayout.setHgap(10);
        detailsLayout.setVgap(10);

        detailsLayout.setAlignment(Pos.CENTER);

        detailsLayout.getChildren().addAll(passengerNameLabel, passengerName, passengerAgeLabel, passengerAge, bussinessClassOption, economyClassOption, backButton, bookButton);
        scene = new Scene(detailsLayout, 300, 200);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public Button getBackButton() {
        return backButton;
    }
    public Button getBookButton(){
        return bookButton;
    }
    public String getNameString(){return passengerName.getText();}
    public int getAge(){return Integer.parseInt(passengerAge.getText());}
    public String getCategory(){
        RadioButton rad = (RadioButton)classGroup.getSelectedToggle();
        String[] data = rad.getText().split(" ");
        return data[0];
    }
}
