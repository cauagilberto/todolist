import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

class Performance {
    public double assignments;
    public double exams;
    public double attendance;
}

class Subject {
    public String name;
    public int credits;
    public Performance performance;

    public double calculateFinalGrade() {
        return performance.assignments * 0.50 + performance.exams * 0.20 + performance.attendance * 0.30;
    }

    public double calculateGradePoint() {
        return (calculateFinalGrade() / 100.0) * 4.0;
    }
}

class Semester {
    public String term;
    public List<Subject> subjects;

    public double calculateGPA() {
        double totalGradePoints = 0.0;
        int totalCredits = 0;
        for (Subject subject : subjects) {
            totalGradePoints += subject.calculateGradePoint() * subject.credits;
            totalCredits += subject.credits;
        }
        return totalGradePoints / totalCredits;
    }
}

class Student {
    public String id;
    public String name;
    public List<Semester> semesters;

    public double calculateCumulativeGPA() {
        double totalGradePoints = 0.0;
        int totalCredits = 0;
        for (Semester semester : semesters) {
            for (Subject subject : semester.subjects) {
                totalGradePoints += subject.calculateGradePoint() * subject.credits;
                totalCredits += subject.credits;
            }
        }
        return totalGradePoints / totalCredits;
    }

    public void generateTranscript() {
        System.out.println("Transcript for " + name + " (ID: " + id + ")");
        System.out.println("----------------------------------------");
        for (Semester semester : semesters) {
            System.out.println("Semester: " + semester.term);
            double semesterGPA = semester.calculateGPA();
            System.out.printf("GPA: %.2f", semesterGPA);
            if (semesterGPA >= 3.7) {
                System.out.println(" (Academic Honors)");
            } else {
                System.out.println();
            }
        }
        double cumulativeGPA = calculateCumulativeGPA();
        System.out.println("----------------------------------------");
        System.out.printf("Cumulative GPA: %.2f", cumulativeGPA);
        if (cumulativeGPA >= 3.7) {
            System.out.println(" (Academic Honors)");
        } else {
            System.out.println();
        }
        System.out.println();
    }
}

class StudentsData {
    public List<Student> students;
}

public class TranscriptGenerator {
    public static void main(String[] args) {
        String jsonString = "{\n" +
                "  \"students\": [\n" +
                "    {\n" +
                "      \"id\": \"S001\",\n" +
                "      \"name\": \"Alice\",\n" +
                "      \"semesters\": [\n" +
                "        {\n" +
                "          \"term\": \"Fall 2023\",\n" +
                "          \"subjects\": [\n" +
                "            { \"name\": \"Math\", \"credits\": 4, \"performance\": { \"assignments\": 80, \"exams\": 70, \"attendance\": 85 } },\n" +
                "            { \"name\": \"Physics\", \"credits\": 3, \"performance\": { \"assignments\": 90, \"exams\": 60, \"attendance\": 70 } }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"S002\",\n" +
                "      \"name\": \"Bob\",\n" +
                "      \"semesters\": [\n" +
                "        {\n" +
                "          \"term\": \"Fall 2023\",\n" +
                "          \"subjects\": [\n" +
                "            { \"name\": \"Math\", \"credits\": 4, \"performance\": { \"assignments\": 85, \"exams\": 75, \"attendance\": 90 } },\n" +
                "            { \"name\": \"English\", \"credits\": 2, \"performance\": { \"assignments\": 95, \"exams\": 82, \"attendance\": 60 } }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        try {
            StudentsData data = mapper.readValue(jsonString, StudentsData.class);
            for (Student student : data.students) {
                student.generateTranscript();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
