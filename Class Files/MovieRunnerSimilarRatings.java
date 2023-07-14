
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {

   public void printAverageRatings(){
      //int minimalRaters =50;
      int minimalRaters =1;
      //String moviesFile = "ratedmoviesfull.csv";
      //String ratingsFile = "ratings.csv";
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      
      FourthRatings tr = new FourthRatings();
      MovieDatabase.initialize(moviesFile);
      RaterDatabase.initialize(ratingsFile);
      
      System.out.println("read data for "+RaterDatabase.size()+" raters");
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
    
      public void printAverageRatingsByYearAfterAndGenre(){
      String moviesFile = "ratedmovies_short.csv";
      String ratingsFile = "ratings_short.csv";
      FourthRatings tr = new FourthRatings();
      MovieDatabase.initialize(moviesFile);
      RaterDatabase.initialize(ratingsFile);
      
      System.out.println("read data for "+RaterDatabase.size()+" raters");
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
      FourthRatings tr = new FourthRatings();
      MovieDatabase.initialize(moviesFile);
      RaterDatabase.initialize(ratingsFile);
      
      System.out.println("read data for "+RaterDatabase.size()+" raters");
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
    
   public void printSimilarRatings(){
      //String moviesFile = "ratedmovies_short.csv";
      //String ratingsFile = "ratings_short.csv";
      String moviesFile = "ratedmoviesfull.csv";
      String ratingsFile = "ratings.csv";      
      FourthRatings tr = new FourthRatings();
      MovieDatabase.initialize(moviesFile);
      RaterDatabase.initialize(ratingsFile);
      
      System.out.println("read data for "+RaterDatabase.size()+" raters");
      System.out.println("read data for "+MovieDatabase.size()+" movies"); 
      
      //ArrayList<Rating> similarList = tr.getSimilarRatings("337",10,3);
      ArrayList<Rating> similarList = tr.getSimilarRatings("71",20,5);
      //ArrayList<Rating> similarList = tr.practice("65",20,5);
      System.out.println(similarList.size());
      for(Rating rating : similarList){
         System.out.println(rating.getValue() + " " + 
                            MovieDatabase.getTitle(rating.getItem()));  
        }
    }
    public void printSimilarRatingsByGenre () {
      //String moviesFile = "ratedmovies_short.csv";
      //String ratingsFile = "ratings_short.csv";
      String moviesFile = "ratedmoviesfull.csv";
      String ratingsFile = "ratings.csv"; 
        FourthRatings fourthRatings = new FourthRatings ();
        RaterDatabase.initialize(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
       String genre = "Mystery"; // variable
       //String genre = "Action";
        GenreFilter gf = new GenreFilter(genre);
         
        //String id = "964"; // variable
        String id = "964";
        int numSimilarRaters = 20; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, gf);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre. " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector () {
      //String moviesFile = "ratedmovies_short.csv";
      //String ratingsFile = "ratings_short.csv";
      String moviesFile = "ratedmoviesfull.csv";
      String ratingsFile = "ratings.csv";   
        FourthRatings fourthRatings = new FourthRatings ();
        RaterDatabase.initialize(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; // variable
        //String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
       DirectorsFilter df = new DirectorsFilter(directors);
         
        String id = "120"; // variable
        //String id = "1034";
        int numSimilarRaters = 10; // variable
        int minimalRaters = 2; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, df);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that was(were) directed by either of the following directors: "
        + directors + ". " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Directed by : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes () {
       String moviesFile = "ratedmoviesfull.csv";
       String ratingsFile = "ratings.csv";        
       //String moviesFile = "ratedmovies_short.csv";
      //String ratingsFile = "ratings_short.csv";
        FourthRatings fourthRatings = new FourthRatings ();
        RaterDatabase.initialize(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Drama"; // variable
        GenreFilter gf = new GenreFilter (genre);
        
        int minMin = 80; // variable
        int maxMin = 160; // variable
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        
        String id = "168"; // variable
        int numSimilarRaters = 10; // variable
        int minimalRaters = 3; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre, that is(are) between " + minMin
        + " and " + maxMin + " minutes in length. " + numSimilarRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Time: " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes () {
      //String moviesFile = "ratedmovies_short.csv";
      //String ratingsFile = "ratings_short.csv";
      String moviesFile = "ratedmoviesfull.csv";
      String ratingsFile = "ratings.csv";         
        FourthRatings fourthRatings = new FourthRatings ();
        RaterDatabase.initialize(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1975; // variable
        //int year = 2000;
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        int minMin = 70; // variable
        //int minMin = 80;
        int maxMin = 200; // variable
        //int maxMin = 100;
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(mf);
        
        String id = "314"; // variable
        //String id = "65";
        int numSimilarRaters = 10; // variable
        int minimalRaters = 5; // variable
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that is(are) between " + minMin + " and " + maxMin 
        + " minutes in length and released after year " + year + ". " + numSimilarRaters 
        + " closest raters were considered.");
        
        for (Rating rating : similarRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Year: " + MovieDatabase.getYear(rating.getItem()) + " Time: " 
            + MovieDatabase.getMinutes(rating.getItem()));
        }
    }
}
