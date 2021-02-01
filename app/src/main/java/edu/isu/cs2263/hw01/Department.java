/*
 * Alex Losser
 * CS2263 - Homework 1
 * Due Jan 31, 2021
 */
package edu.isu.cs2263.hw01;

import java.util.List;

public class Department {

    String depName;
    String depCode;

    public Department() {};

    public Department(String departmentName, String departmentCode) {
        depName = departmentName;
        depCode = departmentCode;
    }

    public String getDepCode() {
        return depCode;
    }
    public String getDepName() {
        return depName;
    }
}
