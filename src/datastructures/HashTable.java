package datastructures;

import database.CityData;
import database.GeneralData;
import database.Path;
import graphicaluserinterface.MessageBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class HashTable {
    public class Record{
        public String path;
        public int availibility_business;
        public int availibility_economic;
    }
    ArrayList<Record> RECORDS = new ArrayList<Record>();
    public Record[] TABLE = new Record[1000];
    int size;
    public void loadData(){
        for(int i = 0; i < 1000; i++){
            TABLE[i] = new Record();
            TABLE[i].path = "";
            TABLE[i].availibility_business = -1;
            TABLE[i].availibility_economic = -1;
        }
        File file = new File("data.csv");
        try{
            if(!file.exists())
                throw new FileNotFoundException("Data file is missing");
            Scanner input = new Scanner(file);
            boolean error = false;
            while(input.hasNextLine()){
                try{
                    String[] data = input.nextLine().split(",");
                    Record record = new Record();
                    record.path = data[0];
                    record.availibility_business = Integer.parseInt(data[1]);
                    record.availibility_economic = Integer.parseInt(data[2]);
                    RECORDS.add(record);


                    int index = hashFunction(record.path);
                    while(TABLE[index].availibility_business != -1)
                        index = (index+1)%1000;
                    TABLE[index].path = record.path;
                    TABLE[index].availibility_business = record.availibility_business;
                    TABLE[index].availibility_economic = record.availibility_economic;
                }catch (Exception e){
                    error = true;
                }
            }
            if(error)
                throw new Exception("Data file not readable");

        }catch (Exception e){
            MessageBox.display("error", e.getMessage());
        }
    }
    public void storeData(){
        File file;
        Formatter formatter;
        Graph g = new Graph(CityData.getCITIES(), CityData.getDISTANCES());
        try{
            file = new File("data.csv");
            formatter = new Formatter(file);
            for(String city1 : CityData.getCITIES()){
                for(String city2 : CityData.getCITIES()){
                    if(!city1.equals(city2)){
                        for(Path path : g.getAllPaths(city1, city2)){
                            formatter.format("%s,%d,%d\n", path.toString(), 5, 5);
                        }
                    }
                }
            }
            formatter.close();
        }catch (Exception e){

        }
    }

    public void saveData(){
        File file;
        Formatter formatter;
        try{
            file = new File("data.csv");
            formatter = new Formatter(file);
            for(Record record : TABLE){
                formatter.format("%s,%d,%d\n", record.path, record.availibility_business, record.availibility_economic);
            }
            formatter.close();
        }catch (Exception e){

        }
    }
    public int size(){
        return this.RECORDS.size();
    }
    public int hashFunction(String str){
        int sum = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == ' ')
                continue;
            sum += (int) str.charAt(i);
        }
        sum = sum%1000;
        return sum;
    }
    public boolean isPresent(String str){
        int i = 1000;
        int index = hashFunction(str);

        while(i > 0){
            if(str.contains(TABLE[index].path) || TABLE[index].path.contains(str))
                return true;
            i--;
        }
        return false;
    }
    public int search(String str){
        int i = 1000;
        int index = hashFunction(str);
        while(i > 0){
            if(str.contains(TABLE[index].path) || TABLE[index].path.contains(str)) {
                System.out.println(TABLE[index].path);
                return index;
            }
            index = (index+1)%1000;
            i--;
        }
        return -1;
    }
    public void addUser(){
        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter("users.csv", true));
            bw.write(GeneralData.details.csvEntry());
            bw.flush();
        }catch (Exception e){
            MessageBox.display("error", "Unable to save user data");
        }finally {
            if(bw != null) try{
                bw.close();
            }catch (Exception e){

            }
        }
    }
}
