package equality;

import java.util.List;
import java.util.Objects;

class Student {
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
      final List<CourseSection> currentCourses) {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null || getClass() != obj.getClass()) {
         return false;
      }
      if (obj == this) {
         return true;
      }

      Student check = (Student) obj;
      return Objects.equals(this.getSurname(), check.getSurname()) && Objects.equals(this.getGivenName(), check.getGivenName())
              && this.getAge() == check.getAge() && this.currentCourses.equals(check.getCurrentCourses());
   }


   @Override
   public int hashCode(){
      return Objects.hash(surname, givenName, age, currentCourses);
   }

   public String getSurname() {
      return surname;
   }

   public String getGivenName() {
      return givenName;
   }

   public int getAge() {
      return age;
   }

   public List<CourseSection> getCurrentCourses() {
      return currentCourses;
   }
}
