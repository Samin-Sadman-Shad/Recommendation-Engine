
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {

   public void printAverageRatings(){
      //int minimalRaters =50;
      int minimalRaters =1;
      //String moviesFile = "ratedmoviesfull.csv";
      //String ratingsFile = "ratings.csv";
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      
      ThirdRatings tr = new ThirdRatings(ratingsFile);
      MovieDatabase.initialize(moviesFile);
      
      System.out.println("read data for "+tr.getRaterSize()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies");
      
      ArrayList<Rating> avgRatingList = tr.getAverageRatings(minimalRaters);
      System.out.println(avgRatingList.size());
      Collections.sort(avgRatingList);
      
      for(Rating r : avgRatingList){
         String item = r.getItem();
         System.out.println(MovieDatabase.getTitle(item));
         System.out.println(r.getValue()+" "+r.getItem());
         
        }
    } 
   
   public void printAverageRatingsByYear(){
      int year = 2000;
      int minimalRaters =1;
      //String moviesFile = "ratedmoviesfull.csv";
      //String ratingsFile = "ratings.csv";
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      
      ThirdRatings tr = new ThirdRatings(ratingsFile);
      MovieDatabase.initialize(moviesFile);
      
      System.out.println("read data for "+tr.getRaterSize()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies");
      
      ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters,new YearAfterFilter(year));
      System.out.println(avgRatingList.size());
      Collections.sort(avgRatingList);
      
      for(Rating r : avgRatingList){
         String item = r.getItem();
         System.out.println(MovieDatabase.getTitle(item));
         System.out.println(r.getValue()+" "+r.getItem());
         
        }
    
    }
    
   public void printAverageRatingsByGenre(){
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      ThirdRatings tr = new ThirdRatings(ratingsFile);
      MovieDatabase.initialize(moviesFile);
      
      System.out.println("read data for "+tr.getRaterSize()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies");
      
      GenreFilter gFilter = new GenreFilter("Crime");
      ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,gFilter);
      System.out.println("found "+ratingList.size());
      Collections.sort(ratingList);
      
      for(Rating r : ratingList){
        String item = r.getItem(); 
        System.out.println(MovieDatabase.getTitle(item)+" "+ r.getValue());
        System.out.println(MovieDatabase.getGenres(item));
        }
    }
    
   public void printAverageRatingsByMinutes(){
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      ThirdRatings tr = new ThirdRatings(ratingsFile);
      MovieDatabase.initialize(moviesFile);
      
      System.out.println("read data for "+tr.getRaterSize()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies");  
      
      MinutesFilter mFilter = new MinutesFilter(110, 170);
      ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,mFilter);
      
      System.out.println("found "+ratingList.size());
      Collections.sort(ratingList);
      
      for(Rating r : ratingList){
        String item = r.getItem(); 
        System.out.println(MovieDatabase.getTitle(item)+" "+ r.getValue());
        System.out.println(MovieDatabase.getMinutes(item));
        }
    }
    
   public void printAverageRatingsByDirectors(){
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      ThirdRatings tr = new ThirdRatings(ratingsFile);
      MovieDatabase.initialize(moviesFile);
      
      System.out.println("read data for "+tr.getRaterSize()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies");  
      
      DirectorsFilter dFilter = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
      ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,dFilter);
      
      System.out.println("found "+ratingList.size());
      Collections.sort(ratingList);
      
      for(Rating r : ratingList){
        String item = r.getItem(); 
        System.out.println(MovieDatabase.getTitle(item)+" "+ r.getValue());
        System.out.println(MovieDatabase.getDirector(item));
        }      
    }
    
   public void printAverageRatingsByYearAfterAndGenre(){
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      ThirdRatings tr = new ThirdRatings(ratingsFile);
      MovieDatabase.initialize(moviesFile);
      
      System.out.println("read data for "+tr.getRaterSize()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies");      
    
      AllFilters aFilter = new AllFilters();
      aFilter.addFilter(new YearAfterFilter(1980));
      aFilter.addFilter(new GenreFilter("Romance"));
      
      ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,aFilter);
      System.out.println("found "+ratingList.size());
      Collections.sort(ratingList);
      
      for(Rating r : ratingList){
        String item = r.getItem(); 
        System.out.println(MovieDatabase.getTitle(item)+" "+ r.getValue());
        System.out.println(MovieDatabase.getGenres(item));
        }      
    }
    
   public void printAverageRatingsByDirectorsAndMinutes(){
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      ThirdRatings tr = new ThirdRatings(ratingsFile);
      MovieDatabase.initialize(moviesFile);
      
      System.out.println("read data for "+tr.getRaterSize()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies");      
    
      AllFilters aFilter = new AllFilters();
      aFilter.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
      aFilter.addFilter(new MinutesFilter(30,170));
      
      ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,aFilter);
      System.out.println("found "+ratingList.size());
      Collections.sort(ratingList);
      
      for(Rating r : ratingList){
        String item = r.getItem(); 
        System.out.println(MovieDatabase.getTitle(item)+" "+ r.getValue());
        System.out.println(MovieDatabase.getDirector(item)+MovieDatabase.getMinutes(item));
        }       
    }
}
