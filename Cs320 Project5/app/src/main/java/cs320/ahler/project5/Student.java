package cs320.ahler.project5;

public class Student {

    private String firstName;
    private String lastName;
    private String year;
    private Double gpa;

    public Student(String fn, String ln, String y, Double g) {
        this.firstName = fn;
        this.lastName = ln;
        this.year = y;
        this.gpa = g;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getYear() {
        return year;
    }

    public Double getGpa() {
        return gpa;
    }
}
