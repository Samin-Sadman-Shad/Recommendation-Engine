
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    String directorsList;
    
    public DirectorsFilter (String directors) {
        directorsList = directors;
    }
    
    public boolean satisfies(String id){
       String[] directors = directorsList.split(",");
       for(int i=0; i<directors.length; i++){
         if(MovieDatabase.getDirector(id).contains(directors[i])){
            return true;
            }
        }
       return false;
    }
}
