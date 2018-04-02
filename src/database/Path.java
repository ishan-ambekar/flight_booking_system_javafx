package database;
import java.util.LinkedList;

public class Path {
    private String path;
    private int totalDistance;
    public Path(String path, int totalDistance){
        this.path = path;
        this.totalDistance = totalDistance;
    }

    public String getPath() {
        return path;
    }
    public int getTotalDistance() {
        return totalDistance;
    }

    public void addNode(String city, int distance){
        path = path+"-"+city;
        totalDistance += distance;
    }

    public Path clone(){
        return new Path(this.path, this.totalDistance);
    }
    public String toString(){
        long price = (int)CustomerDetails.getPricePerKilometer()*totalDistance;
        return path+"-Rs"+ price;
    }

    public static LinkedList<Path> removeRedundand(LinkedList<Path> allPaths){
        LinkedList<Path> newList = new LinkedList<Path>();
        for(Path path : allPaths)
            if(!multipleOccurence(path.toString()))
                newList.add(path);
        return newList;
    }

    public static boolean multipleOccurence(String path){
        String[] cities = path.split("-");
        for(int i = 0; i < cities.length; i++)
            for(int j = i+1; j < cities.length; j++)
                if(cities[i].equals(cities[j]))
                    return true;
        return false;
    }
}
