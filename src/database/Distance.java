package database;

public class Distance {
    String city1, city2;
    int distance;
    public Distance(String city1, String city2, int distance){
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
    }
    public Distance(String city){
        this.city1 = city;
        this.city2 = city;
        this.distance = 0;
    }
    public String firstCity(){return city1;}
    public String secondCity(){return city2;}
    public int distance(){return distance;}
    public String toString(){
         return city1+"-"+city2+"-"+distance;
    }
}
