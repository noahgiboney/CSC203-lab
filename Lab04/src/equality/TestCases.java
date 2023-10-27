package equality;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.time.LocalTime;

import org.junit.Test;

public class TestCases
{
   @Test
   public void testExercise1()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void testExercise2()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(1, 10), LocalTime.of(2, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void testExercise3()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void testExercise4()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
         LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 34,
         LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void testStudent1() {

      ArrayList<CourseSection> courseList = new ArrayList<>();
      courseList.add(new CourseSection("CSC", "203", 35, LocalTime.of(9, 10), LocalTime.of(10, 0)));
      courseList.add(new CourseSection("BOT", "121", 34, LocalTime.of(2, 10), LocalTime.of(10, 0)));

      final Student noah1 = new Student("Noah", "Noah", 3, courseList);
      final Student noah2 = new Student("Noah", "Noah", 3, courseList);

      assertTrue(noah1.equals(noah2));
   }

   @Test
   public void testStudent2() {

      ArrayList<CourseSection> noahList = new ArrayList<>();
      noahList.add(new CourseSection("CSC", "203", 35, LocalTime.of(9, 10), LocalTime.of(10, 0)));
      noahList.add(new CourseSection("BOT", "121", 34, LocalTime.of(2, 10), LocalTime.of(10, 0)));

      ArrayList<CourseSection> johnlist = new ArrayList<>();
      johnlist.add(new CourseSection("CSC", "357", 44, LocalTime.of(22, 10), LocalTime.of(10, 0)));
      johnlist.add(new CourseSection("COM", "324", 34, LocalTime.of(2, 10), LocalTime.of(10, 1)));

      final Student noah = new Student("Noah", "Noah", 3, noahList);
      final Student john = new Student("Noah", "Noah", 3, johnlist);

      assertFalse(noah.equals(john));
   }

   @Test
   public void testStudent3() {

      ArrayList<CourseSection> courseList = new ArrayList<>();
      courseList.add(new CourseSection("CSC", "203", 35, LocalTime.of(9, 10), LocalTime.of(10, 0)));
      courseList.add(new CourseSection("BOT", "121", 34, LocalTime.of(2, 10), LocalTime.of(10, 0)));

      final Student noah1 = new Student("Noah", "Noah", 3, courseList);
      final Student noah2 = new Student("Noah", "Noah", 3, courseList);

      assertEquals(noah1.hashCode(), noah2.hashCode());
   }

   @Test
   public void testStudent4() {

      ArrayList<CourseSection> noahList = new ArrayList<>();
      noahList.add(new CourseSection("CSC", "203", 35, LocalTime.of(9, 10), LocalTime.of(10, 0)));
      noahList.add(new CourseSection("BOT", "121", 34, LocalTime.of(2, 10), LocalTime.of(10, 0)));

      ArrayList<CourseSection> johnlist = new ArrayList<>();
      johnlist.add(new CourseSection("CSC", "357", 44, LocalTime.of(22, 10), LocalTime.of(10, 0)));
      johnlist.add(new CourseSection("COM", "324", 34, LocalTime.of(2, 10), LocalTime.of(10, 1)));

      final Student noah = new Student("Noah", "Noah", 3, noahList);
      final Student john = new Student("Noah", "Noah", 3, johnlist);

      assertNotEquals(noah.hashCode(), john.hashCode());
   }
}

