
/**
 * Write a description of FourthRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {

    public double getAverageByID(String id, int minimalRaters){
      double sum = 0.0;
      int count = 0;
      for(Rater rater : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
      ArrayList<String> myItems = me.getItemsRated();
      ArrayList<String> rItems = r.getItemsRated();
      //ArrayList<String> commonItems = new ArrayList<String>();
      //ArrayList<Double> converted = new ArrayList<Double>();
      double product = 0.0;
      for(int i=0; i<myItems.size(); i++){
         if(rItems.contains(myItems.get(i))){
            //commonItems.add(myItems.get(i));
            String item = myItems.get(i);
            //String item2 = rItems.get(i);
            double rating1 = me.getRating(item) - 5.0;
            double rating2 = r.getRating(item) - 5.0;
             product += rating1*rating2;
            //product sums every pair of converted ratings of the common 
            //movies. The more common movies rater by the raters,
            // the more the number products get higher
            }
       }
      return product;
      /*
      for(int j=0; j<commonItems.size(); j++){
         double rating1 = me.getRating(commonItems.get(j)) - 5.0;
         double rating2 = r.getRating(commonItems.get(j)) - 5.0;
         double product = rating1*rating2;
         converted.add(product);
        }
        */
      //return converted;
     }
     
    private ArrayList<Rating> getSimilarities(String id){
       ArrayList<Rating> ratingsList = new ArrayList<Rating>();
       double rating = 0.0;
       for(Rater r : RaterDatabase.getRaters()){
          if(!r.getID().equals(id)){
            rating = dotProduct(RaterDatabase.getRater(id), r);
            if(rating >= 0){
              ratingsList.add(new Rating(r.getID(), rating));
            }
           }
        }
       Collections.sort(ratingsList, Collections.reverseOrder());
       return ratingsList;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer>();
        
        for(String movieID : movies){
           double currRating = 0.0;
           int currCount = 0;
           //System.out.println(movieID);
           for(int i=0; i<numSimilarRaters; i++){
              Rating r = similarRaters.get(i);
              String raterID = r.getItem();
              double weight = r.getValue();
              //System.out.println("the rater "+raterID);
              Rater rater = RaterDatabase.getRater(raterID);
              //System.out.println("the movie "+movieID);
               if(rater.hasRating(movieID)){
                //System.out.println(raterID +" no rater has rated this movie");
                //System.out.println(MovieDatabase.getTitle(movieID));
                double rating = rater.getRating(movieID)*weight;
                currRating += rating;
                currCount += 1;
                }
            }
           if(currCount>0){
              //System.out.println(currCount+" raters has rated "+MovieDatabase.getTitle(movieID));
            }
           
           if(currCount >= minimalRaters){
                //System.out.println("greater than minimal" + minimalRaters);
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);            
            }
        }
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
         
    }
     public ArrayList<Rating> getSimilarRatingsByFilter 
    (String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : filteredMovies) {
            double currRating = 0.0;
            int currCount = 0;
            //count for every rater
            //System.out.println(movieID);
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                //get rater id
                //System.out.println(raterID);
                double weight = r.getValue();
                //get weight to multiply
                
                Rater rater = RaterDatabase.getRater(raterID);
                //get an original rater
                
                if (rater.hasRating(movieID)) {
                    //this rater id is got from similarRaters
                    //check if the rater has rated the movie
                    double rating = rater.getRating(movieID) * weight;
                    //get a weighted rating for a rater
                    currRating += rating;
                    //get total weighted rating for the movie
                    currCount += 1;
                    //check how many raters have rated this
                }
            }
            
            if (currCount >= minimalRaters) {
                //check if a minimal rater has rated
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            //System.out.println(rating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        //System.out.println(weightedRatings);
        return weightedRatings;
    }
   
    public ArrayList<Rating> practice(String raterID, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        //HashMap<String, Double> myMap = new HashMap<String, Double>();
        //contains movie and their weighted average rating
        ArrayList<Rating> similarList = getSimilarities(raterID);
        //contains Rater Id and ratings who gaves similar ratings to raterID
        //ratings are the weight
        for(String movieID : MovieDatabase.filterBy(new TrueFilter())){
           double totalRating = 0.0;
           int currCount = 0;
           double averageRating =0.0;
           for(int i=0; i<numSimilarRaters; i++){
              Rating r = similarList.get(i);
              String currRaterID = r.getItem();
              double weight = r.getValue();
              
              Rater rater = RaterDatabase.getRater(currRaterID);
              //checking for raters only in similarList
              if(rater.hasRating(movieID)){
                double rating = rater.getRating(movieID)* weight;
                totalRating += rating;
                currCount += 1;
                }
            } 
            
           if(currCount >= minimalRaters){
             averageRating = totalRating / (double)currCount ;
             //myMap.put(movieID,averageRating);
             ratingList.add(new Rating(movieID,averageRating));
            }
        }
        Collections.sort(ratingList, Collections.reverseOrder());
        return ratingList;
    }
}
