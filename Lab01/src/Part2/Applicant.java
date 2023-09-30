package Part2;

import Part1.CourseGrade;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Applicant {
    private String name;
    private int yearsOfExperience;
    private List<CourseGrade> grades = new ArrayList<>();
    public Applicant(String name, List<CourseGrade> grades, int yearsOfExperience){
        this.name = name;
        this.grades = grades;
        this.yearsOfExperience = yearsOfExperience;
    }

    public CourseGrade getGradeFor(String course){
        for (CourseGrade grade : grades) {
            if (Objects.equals(grade.getCourseName(), course)) {
                return grade;
            }
        }
        return null;
    }
    public List<CourseGrade> getGrades() {
        return grades;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getName() {
        return name;
    }
}
