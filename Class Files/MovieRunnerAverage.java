
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class MovieRunnerAverage {
    
   public void printAverageRatings(){
      //int minimalRaters =50;
      int minimalRaters =11;
      String moviesFile = "ratedmoviesfull.csv";
       String ratingsFile = "ratings.csv";
      
      //SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
      SecondRatings sr = new SecondRatings(moviesFile,ratingsFile);
      System.out.println(sr.getMovieSize());
      System.out.println(sr.getRaterSize());
      
      ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
      Collections.sort(avgRatingList);
      
      for(Rating r : avgRatingList){
         String item = r.getItem();
         System.out.println(sr.getTitle(item));
         System.out.println(r.getValue()+" "+r.getItem());
         
        }
    }
    
   public void getAverageRatingOneMovie(){
      int minimalRaters = 12;
      String moviesFile = "ratedmoviesfull.csv";
      String ratingsFile = "ratings.csv";
      //String moviesFile = "exercise-movie1.csv";
      //String ratingsFile = "exercise-rater1.csv";
      //String moviesFile = "ratedmovies_short.csv";
      //String ratingsFile = "ratings_short.csv";
      SecondRatings sr = new SecondRatings(moviesFile,ratingsFile);
      //String title = "The Godfather";
      //String title = "Moneyball";
      String title = "Vacation";
      //String title = "Inside Llewyn Davis";
      //String title = "No Country for Old Men";
      String id = sr.getID(title);
      ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
      for(Rating rating:avgRatingList){
            
            if(rating.getItem().equals(id)){
                System.out.println("Average rating for '" + title + "' is: " + rating.getValue());
            }

        }
      System.out.println("There are " + avgRatingList.size() + 
                         " movies that has  more than "+ minimalRaters);
    }
    
   
}
