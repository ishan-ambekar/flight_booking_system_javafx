package datastructures;
import java.util.LinkedList;
import database.*;

public class Graph {

    LinkedList<LinkedList<Distance>> LIST;
    LinkedList<Path> allPaths;

    public Graph(String[] CITIES, LinkedList<Distance> DISTANCES){
        LIST = new LinkedList<LinkedList<Distance>>();
        for(String city : CITIES){
            LinkedList<Distance> list = new LinkedList<Distance>();
            list.addLast(new Distance(city));
            LIST.addLast(list);
        }
        for(Distance distance : DISTANCES) {
            LinkedList<Distance> firstCityList = findList(distance.firstCity());
            if(firstCityList != null)
                firstCityList.addLast(distance);
        }
    }
    private LinkedList<Distance> findList(String city){
        for(LinkedList<Distance> list : LIST)
            if(list.get(0).firstCity().equals(city))
                return list;
        return null;
    }
    public int findDistance(String city1, String city2){
        LinkedList<Distance> list = findList(city1);
        if(list == null)
            return 0;
        for(Distance d : list)
            if(d.secondCity().equals(city2))
                return d.distance();
        return 0;
    }
    public LinkedList<LinkedList<Distance>> getLIST() {
        return LIST;
    }
    public LinkedList<Path> getAllPaths(String city1, String city2){
        boolean[] isVisited = new boolean[LIST.size()];
        allPaths = new LinkedList<Path>();
        Path path = new Path(city1, 0);
        findPathUtil(city1, city2, isVisited, path);


        LinkedList<Path> tempPaths = new LinkedList<Path>();
        for(Path p : allPaths){
            String[] cities = p.toString().split("-");
            boolean flag = false;
            for(int i = 0; i < cities.length; i++){
                for(int j = i+1; j < cities.length; j++){
                    if(cities[i].equals(cities[j])){
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }
            if(!flag && cities.length <= 5)
                tempPaths.addLast(p);
        }
        allPaths = tempPaths;
        return allPaths;
    }
    private void findPathUtil(String city1, String city2, boolean[] isVisited, Path path){
        LinkedList<Distance> city1List = findList(city1);
        if(city1List == null)
            return;
        isVisited[LIST.indexOf(city1List)] = true;
        Path tempPath = path.clone();
        //tempPath.addNode(city2, findDistance(city1, city2));
        if(city1.equals(city2)){
            allPaths.addLast(tempPath);
        }
        else for(Distance d : city1List){
            if(!isVisited[LIST.indexOf(findList(d.secondCity()))]){
                tempPath.addNode(d.secondCity(), findDistance(city1, d.secondCity()));
                findPathUtil(d.secondCity(), city2, isVisited, tempPath);
                tempPath = path;
            }
        }

        isVisited[LIST.indexOf(city1List)] = false;
    }
}
