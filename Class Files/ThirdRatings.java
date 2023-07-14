
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
       this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        
        FirstRatings fRating = new FirstRatings();
        
        myRaters = fRating.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
       return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
      double sum = 0.0;
      int count = 0;
      for(Rater rater : myRaters){
         if(rater.hasRating(id)){
            count += 1;
            sum += rater.getRating(id);
            }
       }
      if(count >= minimalRaters){
        return sum/count;
        }
      return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
      ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter()); 
      ArrayList<Rating> ratingList = new ArrayList<Rating>();
      double rating = 0.0;
      for(String movieID : movies){
         
         rating = getAverageByID(movieID, minimalRaters);
         if(rating>0.0){
           ratingList.add(new Rating(movieID, rating)); 
           //System.out.println(ratingList);
            }
       }
      return ratingList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
       double rating = 0.0;
       ArrayList<Rating> ratingList = new ArrayList<Rating>();
       ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria); 
       for(String movieID : movies){
          rating = getAverageByID(movieID, minimalRaters);
          //System.out.println(rating);
         if(rating>0.0){
           ratingList.add(new Rating(movieID, rating)); 
           //System.out.println(ratingList);
            }          
        }
       return ratingList;
    }
}
