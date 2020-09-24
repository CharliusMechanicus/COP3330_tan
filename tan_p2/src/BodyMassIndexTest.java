/*************************************
 CHARLIE TAN
 COP 3330 SECTION 2
 ASSIGNMENT 2
 *************************************/

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {

    private BodyMassIndex bmi_instance;

    /********************************************
      BMI SCORE TESTS
    *********************************************/

    @org.junit.jupiter.api.Test
    void test1_calculate_bmi_score() {
        bmi_instance = new BodyMassIndex(65.0, 100.0);
        assertEquals(16.6, bmi_instance.calculate_bmi_score());
    }

    @org.junit.jupiter.api.Test
    void test2_calculate_bmi_score() {
        bmi_instance = new BodyMassIndex(73.0, 160.0);
        assertEquals(21.1, bmi_instance.calculate_bmi_score());
    }

    @org.junit.jupiter.api.Test
    void test3_calculate_bmi_score() {
        bmi_instance = new BodyMassIndex(75.0, 220.0);
        assertEquals(27.5, bmi_instance.calculate_bmi_score());
    }

    @org.junit.jupiter.api.Test
    void test4_calculate_bmi_score() {
        bmi_instance = new BodyMassIndex(84.0, 500.0);
        assertEquals(49.8, bmi_instance.calculate_bmi_score());
    }

    /*********************************************
     BMI CATEGORY TESTS
     *********************************************/

    @org.junit.jupiter.api.Test
    void test1_calculate_bmi_category() {
        bmi_instance = new BodyMassIndex(65.0, 100.0);
        assertEquals("Underweight", bmi_instance.calculate_bmi_category());
    }

    @org.junit.jupiter.api.Test
    void test2_calculate_bmi_category() {
        bmi_instance = new BodyMassIndex(73.0, 160.0);
        assertEquals("Normal weight", bmi_instance.calculate_bmi_category());
    }

    @org.junit.jupiter.api.Test
    void test3_calculate_bmi_category() {
        bmi_instance = new BodyMassIndex(75.0, 220.0);
        assertEquals("Overweight", bmi_instance.calculate_bmi_category());
    }

    @org.junit.jupiter.api.Test
    void test4_calculate_bmi_category() {
        bmi_instance = new BodyMassIndex(84.0, 500.0);
        assertEquals("Obesity", bmi_instance.calculate_bmi_category());
    }

} // END BodyMassIndexTest CLASS