/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    HashMap<String,Double> ratingMap = new HashMap<String, Double>();
      
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        
        FirstRatings fRating = new FirstRatings();
        myMovies = fRating.loadMovies(moviefile);
        myRaters = fRating.loadRaters(ratingsfile);
    }
      
    public int getMovieSize(){
       return myMovies.size();
    }
    
    public int getRaterSize(){
       return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
      double sum = 0.0;
      int count = 0;
      for(Rater rater : myRaters){
         if(rater.hasRating(id)){
            count += 1;
            sum += rater.getRating(id);
            }
       }
      if(count > minimalRaters){
        return sum/count;
        }
      return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
      ArrayList<Rating> ratingList = new ArrayList<Rating>();
      double rating = 0.0;
      for(Movie movie : myMovies){
         String item = movie.getID();
         rating = getAverageByID(item, minimalRaters);
         if(rating>0.0){
           ratingList.add(new Rating(item, rating)); 
            }
       }
      return ratingList;
    }
    
    public String getTitle(String id){
       for(Movie movie : myMovies){
          if(movie.getID().equals(id)){
              return movie.getTitle();
            }
        }
       return "ID not found";
    }
    
    public String getID(String title){
      String id = " ";
      for(Movie movie : myMovies){
        if(movie.getTitle().equals(title)){
           id = movie.getID();
        }
       }
      return id;
    }
    
    private void buildMap(int minRaters){
       double rating = 0.0;
       String item = " ";
       for(Movie movie : myMovies){
          item = movie.getID();
          rating = getAverageByID(item,minRaters);
        }
       ratingMap.put(item,rating);
    }
    
    public void testAvgRatingList(){
       System.out.println(getAverageRatings(3));
    }
}