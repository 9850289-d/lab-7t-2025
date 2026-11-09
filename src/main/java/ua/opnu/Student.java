package ua.opnu;

import java.util.Arrays;

public class Student {
    private String name;
    private String lastName; // Додано для завдання 4
    private String group;
    private int[] marks;

    public Student(String lastName, String name, String group, int[] marks) {
        this.lastName = lastName;
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public int[] getMarks() { return marks; }
    public void setMarks(int[] marks) { this.marks = marks; }

    @Override
    public String toString() {
        return lastName + " " + name + " (Оцінки: " + Arrays.toString(marks) + ")";
    }
}