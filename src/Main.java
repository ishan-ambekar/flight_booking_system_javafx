import graphicaluserinterface.*;
import datastructures.*;
import database.*;

import javafx.application.Application;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application{

    QueryScene queryScene = new QueryScene();
    FlightListScene flightListScene = new FlightListScene();
    FlightBookingScene flightBookingScene = new FlightBookingScene();
    Stage window;
    HashTable hashTable;

    @Override
    public void start(Stage primaryStage) throws Exception {
        hashTable = new HashTable();
        hashTable.loadData();

        window = primaryStage;
        window.setScene(queryScene.getScene());

        queryScene.getSearchButton().setOnAction(e -> onSearchFlightClick());

        FlightListScene.getNextButton().setOnAction(e -> onNextButtonClick());
        FlightListScene.getBackButton().setOnAction(e -> onBackButtonClickFromList());

        flightBookingScene.getBackButton().setOnAction(e -> onBackButtonClickFromBooking());
        flightBookingScene.getBookButton().setOnAction(e -> onBookButton());

        window.setOnCloseRequest(e -> hashTable.saveData());
        window.show();
    }
    public static void main(String[] args){
        (new HashTable()).storeData();
        launch(args);
    }
    public void onSearchFlightClick(){

        try {
            GeneralData.details.setStartCity(queryScene.getSourceString());
            GeneralData.details.setEndCity(queryScene.getDestinationString());

            if(GeneralData.details.getStartCity().equals("") || GeneralData.details.getEndCity().equals(""))
                throw new Exception("Select the cities");

            GeneralData.details.setDateOfTravel(queryScene.getDateOfTravel());
            String[] date = GeneralData.details.getDateOfTravel().split("-");
            int day = Integer.parseInt(date[2]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[0]);
            GeneralData.checkDate(day, month, year);

        }catch (Exception e){
            MessageBox.display("Error", e.getMessage()+"\nPlease enter all details correctly");
            return;
        }

        Graph graph = new Graph(CityData.getCITIES(), CityData.getDISTANCES());
        GeneralData.details.setPath(graph.getAllPaths(GeneralData.details.getStartCity(), GeneralData.details.getEndCity()).toString());
        FlightListScene.flightList = GeneralData.details.getPath().replace('[', ' ').replace(']', ' ').split(",");
        Arrays.sort(FlightListScene.flightList, new java.util.Comparator<String>(){
            @Override
            public int compare(String s1, String s2) {
                // TODO: Argument validation (nullity, length)
                return s1.split("-").length - s2.split("-").length;// comparision
            }
        });
        flightListScene = new FlightListScene();

        window.setScene(flightListScene.getScene());
    }
    public void onNextButtonClick(){
        String pathSelected;
       try {
           pathSelected = ((RadioButton) (flightListScene.getFlightSelectionGroup().getSelectedToggle())).getText();
       }catch (Exception e){
           MessageBox.display("Error", "Please select a path");
           return;
       }
       GeneralData.details.hashTableIndex = hashTable.search(pathSelected);
       GeneralData.details.setSelectedPath(pathSelected);
       window.setScene(flightBookingScene.getScene());
    }
    public void onBackButtonClickFromList(){
        window.setScene(queryScene.getScene());
    }
    public  void onBackButtonClickFromBooking(){
        window.setScene(flightListScene.getScene());
    }
    public void onBookButton(){
        try{
            GeneralData.details.setName(flightBookingScene.getNameString());
            GeneralData.details.setAge(flightBookingScene.getAge());
            if(GeneralData.details.getAge() < 0)
                throw new Exception();
            GeneralData.details.setCategory(flightBookingScene.getCategory());
        }catch (Exception e){
            MessageBox.display("Error", "Please enter all details correctly");
            return;
        }
        boolean permission = YesNoBox.display(GeneralData.details.toString()+"\nAre you sure you want to book?");
        boolean error = false;
        if(permission){

            HashTable.Record record = hashTable.TABLE[GeneralData.details.hashTableIndex];
            String category = flightBookingScene.getCategory();

            if(category.equals("Business")) {
                if (record.availibility_business > 0) {
                    record.availibility_business--;
                } else
                    error = true;
            }
            else {
                if (record.availibility_economic > 0)
                    record.availibility_economic--;
                else
                    error = true;
            }
            if(!error) {
                MessageBox.display("Ticket Confirmation", "Congratulations! Your ticket is booked!");
                hashTable.addUser();
            }
            else
                MessageBox.display("Ticket not available", "Sorry, the tickets are not available!");

            hashTable.saveData();
            hashTable.loadData();
        }
        return;
    }
}