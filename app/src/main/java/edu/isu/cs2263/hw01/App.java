/*
* Alex Losser
* CS2263 - Homework 1
* Due Jan 31, 2021
 */
package edu.isu.cs2263.hw01;

import com.google.common.collect.Lists;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Vector;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Homework 01");

        //Variables
        Vector<Course> vectorStore = new Vector();
        Department computerScience = new Department("Computer Science", "CS");
        Department chemistry = new Department("Chemistry", "CHEM");
        Department physics = new Department("Physics", "PHYS");
        Department mathematics = new Department("Mathematics", "MATH");
        Department botany = new Department("Botany", "BTNY");
        Department zoology = new Department("Zoology", "ZOO");
        List<Department> depList = Lists.newArrayList(computerScience, chemistry, physics, mathematics, botany, zoology);

        //Components
        //Left
        List<String> comboList = Lists.newArrayList("Computer Science", "Chemistry", "Physics", "Math", "Botanics", "Zoology");
        ComboBox comboBox = new ComboBox();
        for (Department dep : depList) {
            comboBox.getItems().add(dep.depName);
        }
        Label lblCrsNum = new Label("Course Number:");
        TextField tfdNum = new TextField();
        Label lblCrsName = new Label("Course Name:");
        TextField tfdName = new TextField();
        Label lblCrsCred = new Label("Credits:");
        TextField tfdCred = new TextField();
        Button btnAdd = new Button("Add");
        Label lblLog = new Label("");

        //Right
        Button btnShowAll = new Button("Show All");
        Button btnShowDept = new Button("Show selected Department");
        ListView listCourses = new ListView();

        //Styling
        listCourses.setPrefWidth(400);
        tfdCred.setPrefWidth(250);
        tfdName.setPrefWidth(250);
        tfdNum.setPrefWidth(250);
        lblLog.setMaxWidth(250);
        lblLog.setWrapText(true);


        //Layouts
        VBox vBoxLeft = new VBox();
        vBoxLeft.getChildren().add(comboBox);
        vBoxLeft.getChildren().add(lblCrsNum);
        vBoxLeft.getChildren().add(tfdNum);
        vBoxLeft.getChildren().add(lblCrsName);
        vBoxLeft.getChildren().add(tfdName);
        vBoxLeft.getChildren().add(lblCrsCred);
        vBoxLeft.getChildren().add(tfdCred);
        vBoxLeft.getChildren().add(btnAdd);
        vBoxLeft.getChildren().add(lblLog);

        VBox vBoxRight = new VBox();
        HBox topRight = new HBox();
        topRight.getChildren().add(btnShowAll);
        topRight.getChildren().add(btnShowDept);
        vBoxRight.getChildren().add(topRight);
        vBoxRight.getChildren().add(listCourses);

        HBox hBoxPrimary = new HBox(vBoxLeft, vBoxRight);

        //Scene
        Scene scene = new Scene(hBoxPrimary);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Actions
        btnAdd.setOnAction(value -> {
            //Check if any tfd is empty first
            if(tfdCred.getText().equals("") ||
                    tfdName.getText().equals("") ||
                    tfdNum.getText().equals("") ||
                    comboBox.getSelectionModel().isEmpty()
            ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot add");
                alert.setContentText("Please ensure all fields are filled to add this course.");
                alert.setHeaderText("");
                alert.showAndWait();
            } else {
                //Button Add Action
                String name = tfdName.getText();
                int num = Integer.parseInt(tfdNum.getText());
                int cred = Integer.parseInt(tfdCred.getText());
                Department department = new Department("Unknown", "XX00");
                //Check which department is selected
                for(Department dep : depList) {
                    if(comboBox.getValue().equals(dep.depName)) {
                        department = dep;
                    }
                }
                Course newCourse = new Course(name, num, cred, department);

                //Add to the vectorStore and output success
                vectorStore.add(newCourse);
                lblLog.setText(String.format("Successfully added %s!", newCourse.courseName));
            }

        });
        btnShowDept.setOnAction(value -> {
            //Button Show Department Action
            listCourses.getItems().clear();
            for (Course course : vectorStore) {
                if(course.dep.depName.equals(comboBox.getValue())) {
                    listCourses.getItems().add(course);
                }
            }

        });
        btnShowAll.setOnAction(value -> {
            //Button Show All Action
            listCourses.getItems().clear();
            for (Course course : vectorStore) {
                listCourses.getItems().add(course.toString());
            }
        });

        //Listeners
        //Numbers Only
        tfdCred.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    tfdCred.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        tfdNum.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")) {
                    tfdNum.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
