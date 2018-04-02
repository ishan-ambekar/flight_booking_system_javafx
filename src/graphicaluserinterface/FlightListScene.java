package graphicaluserinterface;

import datastructures.*;
import database.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Collection;


public class FlightListScene extends UserScene {
    ArrayList<RadioButton> radioButtons = new ArrayList<>();
    ScrollPane scrollFlightList;
    ToggleGroup flightSelectionGroup;
    VBox layout, layout2;
    HBox layout3 = new HBox(20);
    public static String[] flightList = {"\tAir India (10324)", "\tJet Airways (10542)", "\tIndigo (87849)", "\tKingfisher (98472)", "\tEmirates (10112)", "\tAir India (10324)", "\tJet Airways (10542)", "\tIndigo (87849)", "\tKingfisher (98472)", "\tEmirates (10112)"};

    static Button backButton = new Button("Back");
    static Button nextButton = new Button("Next");
    Scene scene;

    public FlightListScene(){
        flightSelectionGroup = new ToggleGroup();
        layout = new VBox(10);
        layout2 = new VBox(5);
        for(String string : flightList){
            RadioButton radioButton = new RadioButton(string);
            radioButton.setToggleGroup(flightSelectionGroup);
            radioButtons.add(radioButton);
        }
        for(RadioButton r : radioButtons)
            layout2.getChildren().add(r);

        backButton.setMinWidth(100);
        backButton.setPadding(new Insets(3));
        nextButton.setMinWidth(100);
        nextButton.setPadding(new Insets(3));

        layout3.getChildren().addAll(backButton, nextButton);
        layout3.setAlignment(Pos.CENTER);

        scrollFlightList = new ScrollPane(layout2);
        scrollFlightList.setPadding(new Insets(10));
        layout.getChildren().addAll(scrollFlightList, layout3);
        layout.setPadding(new Insets(0, 0, 10, 0));
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

    public static Button getNextButton(){return nextButton;};
    public static Button getBackButton(){return backButton;};
    public ToggleGroup getFlightSelectionGroup(){return flightSelectionGroup;}
}
