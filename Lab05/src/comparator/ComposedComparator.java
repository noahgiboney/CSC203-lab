package comparator;

import java.util.Comparator;

public class ComposedComparator implements Comparator<Song> {

    private Comparator<Song> c1; // this will compare years
    private Comparator<Song> c2; // this will compare titles

    public ComposedComparator(Comparator<Song> c1, Comparator<Song> c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public int compare(Song o1, Song o2) {

        if(c1.compare(o1, o2) == 0){ // if the first compare is equal then move to check the next condition
            return c2.compare(o1,o2);
        }
        else{
            return c1.compare(o1,o2); // if they are not equal then sort by the next condition
        }
    }
}
