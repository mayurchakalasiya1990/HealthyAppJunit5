# HealthyAppJunit5

## Junit 5 features

## @BeforeEach @AfterEach
    Name of @Before & @After is changed to @BeforeEach and @AfterEach in Junit5. Both Annotation use to run method 
    before all the unit testcases.

## AssertAll(executable1, executable2,...)
    It will execute all given lambda expressions and give result. Executable is lambda expression of java8.
    In general, if one assert Method fail then other methods after that will be not executed, so we will not 
    able to verify all assertion at once.AssertAll method is used to run all the assertions even if one or more 
    than one fails during execution.
    

## @ParameterizedTest()
    @ValueSource: Parameterized test case with given values to execute test case with different inputs.
    
    For example:
    @ParameterizedTest
    @ValueSource(doubles = {89.0,95.0,110.0}) 
   <img src="/images/Parameterizedtestwithvaluesource.png"/>

    
    @CsvSource: Parameterized test case with given values as csv inputs.
    
    For example:
    @ParameterizedTest(name="weight={0}, height={1}")
    @CsvSource(value={"89.0,1.72","95.0,1.75","110.0,1.78"})
   <img src="/images/ParameterizedTestwithCSVSourceOutput.png"/>
    
    @CsvFileSource: Parameterized Test case with Value source as CSV file with inputs in pairs.
    
    For example:
    @ParameterizedTest(name="weight={0}, height={1}")
    @CsvFileSource(resources = "/diet-recommended-input-data.csv",numLinesToSkip = 1)
   <img src="/images/ParameterizedTestwithCSVFile.png"/>
    

## @RepeatedTest 
    Repeat test case n number of times.
    
    For example:
    @RepeatedTest(value=10,name = RepeatedTest.LONG_DISPLAY_NAME)
   <img src="/images/Repeat Test case 10 times.png"/>

## assertTimeout(Duration.ofMillis(1),executable)
    Using assertTimeout, perform performance testing can be done.
    
    For example: As per Given condition, if executable will take time more than 1 Millis seconds, test case will fail.
   <img src="/images/performacetestingwithjunit5.png"/>

## assumeTrue(this.env.equals("prod")) 
    Testcase will be not executed if condition fail. It will skip the test case.
    AssumeTrue is different from assertTrue as it will skip the test case if condition fail, 
    while assertTrue fails testcase if condition fail.
<img src="/images/AssumeTrueJunit5.png"/>


## @Nested 
    It is be used to organize all test cases in different inner classes of TestClass.

## @DisplayName("Custom Test class/Testcase name") 
    It helps to give custom display name to testcase and test class.

## @DisabledOnOS(OS.WINDOWS)
    Test method will skip on windows OS machine.

## @Disabled
    Test method will be Skipped using this annotation.
