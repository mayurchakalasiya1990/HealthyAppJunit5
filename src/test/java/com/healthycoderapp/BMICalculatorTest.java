package com.healthycoderapp;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class BMICalculatorTest {

    private String env="dev";

    /*
        @BeforeAll : it will run exactly once before all unit tests run.
        Method must be static method
     */
    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All unit tests.");
    }

    /*
        @BeforeAll : it will run exactly once after all unit tests run.
        Method must be static method
     */
    @AfterAll
    static void afterAll(){
        System.out.println("After All unit tests.");
    }


    /*
        @Nested: Class helps to organize all test cases in different inner classes.

     */
    @Nested
    @DisplayName("DietRecommended Methods Unit Testing")
    class IsDietRecommendedTestClass{
        /*
        Junit4 will not allow method without public keyword.
        @ValueSource: Parameterized test case with given values to execute test case with different inputs
     */
        @ParameterizedTest
        @ValueSource(doubles = {89.0,95.0,110.0})
        void should_ReturnTrue_When_DietRecommended(Double coderWeight){
            //Given
            double weight =coderWeight;
            double height=1.72;
            //When
            boolean recommended= BMICalculator.isDietRecommended(weight,height);
            //then
            assertTrue(recommended);
        }

        /*
            @CsvSource: Parameterized Test case with Value source as comma separated values with inputs in pairs.
         */
        @ParameterizedTest(name="weight={0}, height={1}")
        @CsvSource(value={"89.0,1.72","95.0,1.75","110.0,1.78"})
        void should_ReturnTrue_When_DietRecommendedCSV(Double coderWeight,Double coderHeight){
            //Given
            double weight =coderWeight;
            double height=coderHeight;
            //When
            boolean recommended= BMICalculator.isDietRecommended(weight,height);
            //then
            assertTrue(recommended);
        }
        /*
            @CsvFileSource: Parameterized Test case with Value source as CSV file with inputs in pairs.
         */
        @ParameterizedTest(name="weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv",numLinesToSkip = 1)
        void should_ReturnTrue_When_DietRecommendedCSVFile(Double coderWeight,Double coderHeight){
            //Given
            double weight =coderWeight;
            double height=coderHeight;
            //When
            boolean recommended= BMICalculator.isDietRecommended(weight,height);
            //then
            assertTrue(recommended);
        }

        @Test
        @DisabledOnOs(OS.LINUX)
        void should_ReturnFalse_When_DietNotRecommended(){
            //Given
            double weight =50.0;
            double height=1.92;
            //When
            boolean recommended= BMICalculator.isDietRecommended(weight,height);
            //then
            assertFalse(recommended);
        }

    }

    @Nested
    class FindCoderWithWorstBMICodeTestClass{
        /*
        assertAll(executable1, executable2,....) : it will execute all given lambda expressions and give result.
        In general, if one assert Method fail then other methods after that will not execute so we will not able to verify all assertion at once.
     */
        @Test
        void should_ReturnCodeWithWorstBMI_When_CoderListNotEmpty(){
            //Given
            List<Coder> coderList=new ArrayList<>();
            coderList.add(new Coder(1.80,60.0));
            coderList.add(new Coder(1.82,98.0));
            coderList.add(new Coder(1.82,64.7));
            //When
            Coder coder=BMICalculator.findCoderWithWorstBMI(coderList);
            //then
            assertAll(
                    ()-> assertNotNull(coder),
                    ()-> assertEquals(1.82,coder.getHeight()),
                    ()-> assertEquals(98.0,coder.getWeight())
            );
        }

    /*
        assertTimeout(Duration.ofMillis(1),executable): help to perform performance testing for method.
        For example: if executable will take time more than 1 Millis seconds, test case will fail

        assumeTrue(this.env.equals("prod")): it will not test fail if condition fail. it will skip the test case.
        it is different from assertTrue as it is failure the test case if condition fail.
     */

        @Test
        void should_ReturnCodeWithWorstBMIin1Ms_When_CoderListHas10000Elements(){

            assumeTrue(BMICalculatorTest.this.env.equals("prod"));
            //Given
            List<Coder> coderList=new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coderList.add(new Coder(1.0+i,10.0+i));
            }

            //When
            Executable executable=() -> BMICalculator.findCoderWithWorstBMI(coderList);
            //then
            assertTimeout(Duration.ofMillis(1),executable);
        }

    }

    @Nested
    class GetBMITestScoreTestClass{
        @Test
        void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
            //Given
            List<Coder> coderList=new ArrayList<>();
            coderList.add(new Coder(1.80,60.0));
            coderList.add(new Coder(1.82,98.0));
            coderList.add(new Coder(1.82,64.7));
            double[] expected={18.52,29.59,19.53};
            //When
            double[] bmiScores=BMICalculator.getBMIScores(coderList);
            //then
            assertArrayEquals(expected,bmiScores);
        }
        @Test
        void should_ThrowsException_When_HeightZero(){
            //Given
            double weight =50.0;
            double height=0;
            //When
            Executable executable= () -> BMICalculator.isDietRecommended(weight,height);
            //then
            assertThrows(ArithmeticException.class,executable);
        }

        @Test
        void should_ReturnNull_When_CoderListEmpty(){
            //Given
            List<Coder> coderList=new ArrayList<>();
            //When
            Coder coder=BMICalculator.findCoderWithWorstBMI(coderList);
            //then
            assertNull(coder);
        }


    }


}
