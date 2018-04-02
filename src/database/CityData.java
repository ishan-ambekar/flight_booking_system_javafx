package database;

import java.util.LinkedList;

public class CityData {
    private static String[] CITIES = {"Mumbai", "Delhi", "Kolkata", "Chennai", "Pune", "Bangalore", "Hyderabad"};
    public static String[] getCITIES(){return CITIES;}
    private static LinkedList<Distance> DISTANCES;
    public static LinkedList<Distance> getDISTANCES(){
        DISTANCES = new LinkedList<Distance>();
        //Mumbai
        DISTANCES.addLast(new Distance("Mumbai", "Delhi", 1148));
        DISTANCES.addLast(new Distance("Mumbai", "Kolkata", 1654));
        DISTANCES.addLast(new Distance("Mumbai", "Chennai", 1028));
        DISTANCES.addLast(new Distance("Mumbai", "Bangalore", 981));
        DISTANCES.addLast(new Distance("Mumbai", "Hyderabad", 622));

        //Delhi
        DISTANCES.addLast(new Distance("Delhi", "Mumbai", 1148));
        DISTANCES.addLast(new Distance("Delhi", "Kolkata", 1307));
        DISTANCES.addLast(new Distance("Delhi", "Chennai", 1760));
        DISTANCES.addLast(new Distance("Delhi", "Pune", 1173));
        DISTANCES.addLast(new Distance("Delhi", "Bangalore", 1740));
        DISTANCES.addLast(new Distance("Delhi", "Hyderabad", 1253));

        //Kolkata
        DISTANCES.addLast(new Distance("Kolkata", "Mumbai", 1654));
        DISTANCES.addLast(new Distance("Kolkata", "Delhi", 1307));
        DISTANCES.addLast(new Distance("Kolkata", "Chennai", 1366));
        DISTANCES.addLast(new Distance("Kolkata", "Hyderabad", 1180));

        //Chennai
        DISTANCES.addLast(new Distance("Chennai", "Mumbai", 1028));
        DISTANCES.addLast(new Distance("Chennai", "Delhi", 1760));
        DISTANCES.addLast(new Distance("Chennai", "Kolkata", 1366));
        DISTANCES.addLast(new Distance("Chennai", "Pune", 1195));

        //Pune
        DISTANCES.addLast(new Distance("Pune", "Delhi", 1173));
        DISTANCES.addLast(new Distance("Pune", "Chennai", 1195));

        //Bangalore
        DISTANCES.addLast(new Distance("Bangalore", "Mumbai", 981));
        DISTANCES.addLast(new Distance("Bangalore", "Delhi", 1740));

        //Hyderabad
        DISTANCES.addLast(new Distance("Hyderabad", "Mumbai", 622));
        DISTANCES.addLast(new Distance("Hyderabad", "Delhi", 1253));
        DISTANCES.addLast(new Distance("Hyderabad", "Kolkata", 1180));

        return DISTANCES;
    }
}