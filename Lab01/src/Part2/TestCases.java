package Part2;

import Part1.SimpleIf;
import org.junit.jupiter.api.Test;
import Part1.CourseGrade;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCases {
   /*
    * This test is just to get you started.
    */
   @Test
   public void testGetName()   {
      // This will not compile until you implement the Applicant class
      List<CourseGrade> grades = Arrays.asList(
         new CourseGrade("Intro to CS", 100),
         new CourseGrade("Data Structures", 95),
         new CourseGrade("Algorithms", 91),
         new CourseGrade("Computer Organization", 91),
         new CourseGrade("Operating Systems", 75),
         new CourseGrade("Non-CS", 83)
      );
      Applicant testApplicant = new Applicant("Aakash", grades, 10);
      assertEquals("Aakash", testApplicant.getName());
   }

   /*
    * The tests below here are to verify the basic requirements regarding
    * the "design" of your class.  These are to remain unchanged.
    */
   @Test
   public void testImplSpecifics()
      throws NoSuchMethodException   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getName",
         "getGrades",
         "getGradeFor"
      );

      final List<Class> expectedMethodReturns = Arrays.asList(
         String.class,
         List.class,
         CourseGrade.class
      );

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0],
         new Class[0],
         new Class[] { String.class }
         );

      verifyImplSpecifics(Applicant.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException    {
      assertEquals(0, Applicant.class.getFields().length,
              "Unexpected number of public fields");

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertTrue(expectedMethodNames.size()+1 >= publicMethods.size(),
              "Unexpected number of public methods");

      assertTrue(expectedMethodNames.size() == expectedMethodReturns.size(),
              "Invalid test configuration");
      assertTrue(expectedMethodNames.size() == expectedMethodParameters.size(),
              "Invalid test configuration");

      for (int i = 0; i < expectedMethodNames.size(); i++)       {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }

   @Test
   public void testApplicant1(){
      ArrayList<CourseGrade> noahsGrades = new ArrayList<>();
      noahsGrades.add(0,new CourseGrade("Intro to CS", 60));
      noahsGrades.add(1,new CourseGrade("Algorithms", 94));
      noahsGrades.add(2,new CourseGrade("Operating Systems", 85));

      Applicant noah = new Applicant("Noah Giboney", noahsGrades, 2);


      assertFalse(SimpleIf.analyzeApplicant2(noah, 18));
   }

   @Test
   public void testApplicant2(){
      ArrayList<CourseGrade> noahsGrades = new ArrayList<>();
      noahsGrades.add(0,new CourseGrade("Intro to CS", 77));
      noahsGrades.add(1,new CourseGrade("Algorithms", 80));
      noahsGrades.add(2,new CourseGrade("Operating Systems", 11));

      Applicant noah = new Applicant("Noah Giboney", noahsGrades, 10);

      assertTrue(SimpleIf.analyzeApplicant2(noah, 9));
   }
}
