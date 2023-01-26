import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;

public class WikiRacer {

    String startURL = "https://en.wikipedia.org/wiki/December_2022_North_American_winter_storm";
    String endURL = "https://en.wikipedia.org/wiki/Extratropical_cyclone";
//    String endURL = "https://en.wikipedia.org/wiki/Tropical_wave";
    int maxDepth = 2;

    private ArrayList<String> path = new ArrayList<>();
    private ArrayList<String> visited = new ArrayList<>();

    
//    public static void main(String[] args) {
//        WikiRacer racer = new WikiRacer();
//        racer.searchDFS(racer.startURL, racer.endURL, 0);
////        racer.searchBFS(racer.startURL, racer.endURL, 0);
//    }

    public WikiRacer(){

    }

    public ArrayList<String> getPath() {
        return path;
    }

    public boolean searchDFS(String currentURL, String endURL, int depth){

        path.add(currentURL);
        visited.add(currentURL);

        if (currentURL.equalsIgnoreCase(endURL)){
            System.out.println("Found: " + currentURL);
            System.out.println("Path: " + path.toString());
            return true;

        } else if (depth == maxDepth) {
            path.remove(path.size() - 2);
            return false;
        } else {
            try {
                URL streamURL = new URL(startURL);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(streamURL.openStream())
                );
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("href")) {
                        try {
                            int beginIndex = line.indexOf("/wiki/");
                            int endIndexDouble = line.indexOf("\"", beginIndex);

                            String link = line.substring(beginIndex, endIndexDouble);
                            link = "https://en.wikipedia.org" + link;

                            if (!visited.contains(link) && !path.contains(link) && depth < maxDepth) {
                                System.out.println("| " + depth + " |" + link);
                                if (searchDFS(link, endURL, depth + 1)){
                                    return true;
                                }
                            }

                        } catch (Exception e) {
                        }
                    }
                }
                reader.close();
            } catch (Exception ex){

            }
        }
        return false;
    }

    private boolean searchBFS(String currentURL, String endURL, int depth) {

        if (currentURL.equalsIgnoreCase(endURL)){
            System.out.println("Found: " + currentURL);
            System.out.println("Path: " + path.toString());
            return true;
//        } else if (depth == maxDepth) {
//            path.remove(path.size() - 2);
//            return false;
        } else {
            try {
                URL streamURL = new URL(startURL);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(streamURL.openStream())
                );
                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("href")) {
                        try {
                            int beginIndex = line.indexOf("/wiki/");
                            int endIndexDouble = line.indexOf("\"", beginIndex);

                            String link = line.substring(beginIndex, endIndexDouble);
                            link = "https://en.wikipedia.org" + link;



                        } catch (Exception e) {
                        }
                    }
                }
                reader.close();
            } catch (Exception ex){

            }
        }
        return false;
    }


}
