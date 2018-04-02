package database;

import datastructures.HashTable;

public class CustomerDetails {
    private static double pricePerKilometer = 4.3;

    public static double getPricePerKilometer(){
        return pricePerKilometer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String name = "";
    String path = "";
    long price = 0;
    String date = "";


    String startCity;
    String endCity;
    String dateOfTravel;
    String category;
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    String selectedPath;

    public String getSelectedPath() {
        return selectedPath;
    }

    public void setSelectedPath(String selectedPath) {
        this.selectedPath = selectedPath;
    }

    public int hashTableIndex = -1;

    public String toString(){
        String detail = "";
        detail  = detail + "Name : " + name + "\n";
        detail = detail + "Age : " + age + "\n";
        detail = detail + "Start City : " + startCity + "\n";
        detail = detail + "End City : " + endCity + "\n";
        detail = detail + "Path selected : " +  selectedPath + "\n";
        detail = detail + "Category : " + category + "\n";
        detail = detail + "Hash table entry : " + hashTableIndex + "\n";
        return detail;
    }
    public String csvEntry(){
        return name+","+age+","+dateOfTravel+","+startCity+","+endCity+","+selectedPath+","+category+"\n";
    }
}
