package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class DietPlannerTest {
    private DietPlanner dietPlanner;


    /*
        Name of @Before & @After is changed to @BeforeEach and @AfterEach in Junit5
     */
    @BeforeEach
    void setup(){
        dietPlanner=new DietPlanner(20,30,50);
    }

    @AfterEach
    void afterEach(){
        System.out.println("A unit test was finished.");
    }

    /*
        @RepeatedTest: repeat test case n number of times
     */
    @RepeatedTest(value=10,name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_ReturnCorrectDietPlan_When_CorrectCoder(){

        //Given
        Coder coder=new Coder(1.82,75.0,26,Gender.MALE);
        DietPlan expected=new DietPlan(2202,110,73,275);

        //When
        DietPlan actual=dietPlanner.calculateDiet(coder);

        //Then
        assertAll(
                ()->assertEquals(expected.getCalories(),actual.getCalories()),
                ()->assertEquals(expected.getCarbohydrate(),actual.getCarbohydrate()),
                ()->assertEquals(expected.getFat(),actual.getFat()),
                ()->assertEquals(expected.getProtein(),actual.getProtein())
        );

    }
}
