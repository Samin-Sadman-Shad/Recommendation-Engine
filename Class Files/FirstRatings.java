
/**
 * Write a description of FirstRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
   
   public ArrayList<Movie> loadMovies(String filename){
     ArrayList<Movie> movieData = new ArrayList<Movie>();
     //FileResource fr = new FileResource("data/"+filename);
     FileResource fr = new FileResource(filename);
     CSVParser parser = fr.getCSVParser();
     for(CSVRecord record : parser){
        String currentID = record.get(0);
        String currentTitle = record.get(1);
        String currentYear = record.get(2);
        String currentCountry = record.get(3);
        String currentGenre = record.get(4);
        String currentDirector = record.get(5);
        int currentMinutes = Integer.parseInt(record.get(6));
        String currentPoster = record.get(7);
        
        Movie currentMovie = new Movie(currentID, currentTitle, 
                  currentYear, currentGenre, 
                  currentDirector, currentCountry, 
                  currentPoster, currentMinutes);
        
        movieData.add(currentMovie);
        }
     return movieData;             
    }
    
   public void testLoadMovies(){
     ArrayList<Movie> movieData = loadMovies("ratedmovies_short.csv");
     //ArrayList<Movie> movieData = loadMovies("ratedmoviesfull.csv");
     System.out.println(movieData.size());
     /*
     for(int i=0; i< movieData.size(); i++){
        System.out.println(movieData.get(i));
        }
     */
     
     ArrayList<Movie> comedyGenre = new ArrayList<Movie>();
     for(int i=0; i<movieData.size(); i++){
        String genre = movieData.get(i).getGenres();
        if(genre.contains("Comedy")){
           comedyGenre.add(movieData.get(i));
        }
     }
     System.out.println(comedyGenre.size());
     
     ArrayList<Movie> greaterLength = new ArrayList<Movie>();
     int length = 150;
     for(int i=0; i<movieData.size(); i++){
        int currLength = movieData.get(i).getMinutes();
        if(currLength > length){
           greaterLength.add(movieData.get(i));
        }
      }
     System.out.println(greaterLength.size());
     
     ///*
     HashMap<String, Integer> directorMap = new HashMap<String, Integer>();
     
     for(int i=0; i<movieData.size(); i++){
        String[] directors = movieData.get(i).getDirector().split(",");
        
        for(String director : directors){
            if(!directorMap.containsKey(director)){
             directorMap.put(director,1);
          }
          else{
            int freq = directorMap.get(director);
            directorMap.put(director, freq+1);
            }
        }
      }
     
     
     int maxNumMov = 0;
     for(String dir : directorMap.keySet()){
        if(directorMap.get(dir) > maxNumMov){
          maxNumMov = directorMap.get(dir);
        }
      }
     System.out.println(maxNumMov);
     ///*
     for(String dir : directorMap.keySet()){
        if(directorMap.get(dir) == maxNumMov){
          System.out.println(dir); 
        }
        }
     //*/
    }
    
   public ArrayList<Rater> loadRaters(String filename){
     ArrayList<Rater> raterList = new ArrayList<Rater>();
     HashMap<String,ArrayList<Rating>> raterMap = new HashMap<String,ArrayList<Rating>>();
     
     FileResource fr = new FileResource("data/"+filename);
     CSVParser parser = fr.getCSVParser();
     for(CSVRecord record : parser){
            String currentRaterID = record.get(0);
            String currentMovieID = record.get(1);
            double currentMovieRating = Double.parseDouble(record.get(2));    
            
            
            //System.out.println(currentRaterID);
            if(!raterMap.containsKey(currentRaterID)){
               Rater curr = new EfficientRater(currentRaterID);
               //System.out.println("new id "+currentRaterID);
               ArrayList<Rating> ratingList = new ArrayList<Rating>();
               curr.addRating(currentMovieID, currentMovieRating);
               ratingList.add(new Rating(currentMovieID, currentMovieRating));
               raterMap.put(currentRaterID, ratingList);
               raterList.add(curr);
               //System.out.println(currentRaterID+curr);
            }
            else {
               //System.out.println("old id "+currentRaterID); 
               ArrayList<Rating> ratingList = raterMap.get(currentRaterID);
               //System.out.println(ratingList);
               ratingList.add(new Rating(currentMovieID, currentMovieRating));
               //System.out.println(ratingList);
               raterMap.put(currentRaterID, ratingList);
               int index = 0;
               for(int k=0; k<raterList.size(); k++){
                  if(raterList.get(k).getID().equals(currentRaterID)){
                     index = k;
                    }
                }
               //int index = raterList.indexOf(curr);
               //System.out.println(index);
               Rater curr = raterList.get(index);
               curr.addRating(currentMovieID, currentMovieRating);
               //System.out.println(currentRaterID+curr);
               raterList.set(index, curr);
            }
      }
     return raterList;
    }
    
   public void testLoadRaters(){
     ArrayList<Rater> raterList = loadRaters("ratings_short.csv");
     //ArrayList<Rater> raterList = loadRaters("ratings.csv");
     System.out.println("size is "+raterList.size());
     ArrayList<String> maxNumList = new ArrayList<String>();
     ArrayList<String> itemRaterList = new ArrayList<String>();
     ArrayList<String> movieList = new ArrayList<String>();
     int id = 193;
     int numRating = 0;
     int maxNum = 0;
     String item = "1798709";
     int count = 0;
      for(int i=0; i<raterList.size(); i++){
        //System.out.println(raterList.get(i).getID());
        /*
        ArrayList<String> itemList = raterList.get(i).getItemsRated();
         for(int j=0; j<itemList.size(); j++){
           String item = itemList.get(j);
           double rating = raterList.get(i).getRating(item);
           System.out.println(item+" "+rating);
        }
        */
        ///*
        if(Integer.parseInt(raterList.get(i).getID()) == id){
          //System.out.println(id);
          numRating = raterList.get(i).numRatings();
          //System.out.println(numRating);
        }
     }  //*/
        for(int i=0; i<raterList.size(); i++){
        if(raterList.get(i).numRatings() > maxNum){
           maxNum = raterList.get(i).numRatings();
        }
     }
     ///*
      for(int i=0; i<raterList.size(); i++){
        if(raterList.get(i).numRatings() == maxNum){
           //System.out.println("max rating "+maxNum);
           //System.out.println("current id "+raterList.get(i).getID());
           //System.out.println("current rating "+raterList.get(i).numRatings());
           maxNumList.add(raterList.get(i).getID()); 
        }
        //System.out.println(raterList.get(i).numRatings());
     }
     for(int i=0; i<raterList.size(); i++){
        if(raterList.get(i).hasRating(item)){
          itemRaterList.add(raterList.get(i).getID());
        }
      }
     for(int i=0; i<raterList.size(); i++){
        ArrayList<String> list = raterList.get(i).getItemsRated();
        if(movieList.equals(null)){
          for(String s : list){
            movieList.add(s);
            }
          }
        else{
          for(String s : list){
            if(!movieList.contains(s)){
               movieList.add(s);
            }
           }
        }
      } 
     //*/
     //System.out.println(numRating);
     //System.out.println(maxNum);
     //System.out.println(maxNumList);
     //System.out.println("max rating is given by "+maxNumList.size()+" raters");
     //System.out.println(itemRaterList.size());
     System.out.println(movieList.size());
    } 
}
