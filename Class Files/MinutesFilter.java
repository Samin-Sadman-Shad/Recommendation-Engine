
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MinutesFilter implements Filter{
    int minimum;
    int maximum;
    public MinutesFilter(int min, int max){
       maximum = max;
       minimum = min; 
    }
    
    public boolean satisfies(String id){
       int minutes  = MovieDatabase.getMinutes(id);
       if(minutes >= minimum && minutes <= maximum){
          return true;
        }
       return false;
    }
}
