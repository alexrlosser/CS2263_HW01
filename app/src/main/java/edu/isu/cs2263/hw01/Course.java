/*
 * Alex Losser
 * CS2263 - Homework 1
 * Due Jan 31, 2021
 */
package edu.isu.cs2263.hw01;

public class Course {

    String courseName;
    int courseNum;
    int courseCred;
    Department dep;

    public Course(){}

    public Course(String name, int num, int cred, Department department) {
        courseName = name;
        courseNum = num;
        courseCred = cred;
        dep = department;
    }

    @Override
    public String toString() {
        String outputString = String.format("%s: %d credit(s) \n%s, %d", courseName, courseCred, dep.depName, courseNum);
        return outputString;
    }

    //Getters
    public String getCourseName() {
        return courseName;
    }
    public int getCourseNum() {
        return courseNum;
    }
    public int getCourseCred() {
        return courseCred;
    }
}
