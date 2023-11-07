package comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator() {
      ArtistCompare myCompare = new ArtistCompare();

      int compareResult1 = myCompare.compare(songs[0], songs[1]); // D vs R (-1)
      int compareResult2 = myCompare.compare(songs[1], songs[4]); // R vs C (1)
      int compareResult3 = myCompare.compare(songs[3], songs[7]); // Same Artist (0)

      assertTrue( compareResult1 < 0);
      assertTrue(compareResult2 > 0);
      assertEquals(0, compareResult3);
   }

   @Test
   public void testLambdaTitleComparator() {

      Comparator<Song> myCompare = (Song1, Song2) -> Song1.getTitle().compareTo(Song2.getTitle());

      int compareResult1 = myCompare.compare(songs[0], songs[1]);// T vs L (1)
      int compareResult2 = myCompare.compare(songs[3], songs[5]);// Same Title (0)
      int compareResult3 = myCompare.compare(songs[3], songs[4]);// B vs S (-1)

      assertTrue( compareResult1 > 0);
      assertEquals(0, compareResult2);
      assertTrue(compareResult3 < 0);

   }


   @Test
   public void testYearExtractorComparator() {

      Comparator<Song> myCompare = Comparator.comparing(Song::getYear).reversed(); // reversed to do in descending order

      int compareResult1 = myCompare.compare(songs[1], songs[2]);//2005 vs 2006 (1)
      int compareResult2 = myCompare.compare(songs[0], songs[1]);//2005 vs 2006 (0)
      int compareResult3 = myCompare.compare(songs[4], songs[2]);//2007 vs 2006 (-1)

      assertTrue( compareResult1 > 0);
      assertEquals(0, compareResult2);
      assertTrue( compareResult3 < 0);

   }

   @Test
   public void testComposedComparator() {

      Comparator<Song> c1 = Comparator.comparing(Song::getYear);
      Comparator<Song> c2 = Comparator.comparing(Song::getArtist);

      ComposedComparator yearArtist = new ComposedComparator(c1,c2);

      //first check the year then compare artist
      int compareResult1 = yearArtist.compare(songs[3], songs[7]); // "Gerry Rafferty", 1998 vs "Gerry Rafferty", 1978 (1)
      int compareResult2 = yearArtist.compare(songs[7], songs[3]); // "Gerry Rafferty", 1978 vs "Gerry Rafferty", 1998 (-1)
      int compareResult3 = yearArtist.compare(songs[0], songs[1]); //"Decemberists", 2005, "Rogue Wave", 2005 (-1)
      int compareResult4 = yearArtist.compare(new Song("City and Colour", "Sleeping Sickness", 2007),
                                              new Song("City and Colour", "Sleeping Sickness", 2007)); // (0)

      assertTrue( compareResult1 > 0);
      assertTrue( compareResult2 < 0);
      assertTrue(compareResult3 < 0);
      assertEquals(0, compareResult4);

   }

   @Test
   public void testThenComparing() {
      Comparator<Song> c1 = Comparator.comparing(Song::getTitle).thenComparing(Song::getArtist); // compare title then by artist

      int compareResult1 = c1.compare(songs[3], songs[5]); // "Gerry Rafferty", "Baker Street" vs "Foo Fighters", "Baker Street" (1) {Baker is title}
      int compareResult2 = c1.compare(songs[1], songs[2]); // "Rogue Wave", "Love's Lost Guarantee" vs "Avett Brothers", "Talk on Indolence"" (-1)

      assertTrue( compareResult1 > 0);
      assertTrue( compareResult2 < 0);

   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      Comparator<Song> c1 = Comparator.comparing(Song::getArtist).thenComparing(Song::getTitle).thenComparing(Song::getYear);

      songList.sort(c1);

      assertEquals(songList, expectedList);
   }
}
