package aegon.assessment.simplecalculator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleCalculatorTest {

    private SimpleCalculator simpleCalculator;

    @BeforeEach
    void setup() {
        this.simpleCalculator = new SimpleCalculator();
    }

    @ParameterizedTest
    @CsvSource({"1,1,2", "1,2,3", "-10,10,0", "-10,-5,-15"})
    void add(int leftSide, int rightSide, double expected) {
        //  ARRANGE
        //  ACT
        double actual = simpleCalculator.add(leftSide, rightSide);
        //  ASSERT
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1,1,0", "1,2,-1", "-10,10,-20", "-10,-5,-5"})
    void subtract(int leftSide, int rightSide, double expected) {
        //  ARRANGE
        //  ACT
        double actual = simpleCalculator.subtract(leftSide, rightSide);
        //  ASSERT
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1,1,1", "1,2,2", "-10,10,-100", "-10,-5,50"})
    void multiply(int leftSide, int rightSide, double expected) {
        //  ARRANGE
        //  ACT
        double actual = simpleCalculator.multiply(leftSide, rightSide);
        //  ASSERT
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"1,1,1", "1,2,0.5", "-10,10,-1", "-10,-5,2"})
    void divide(int leftSide, int rightSide, double expected) {
        //  ARRANGE
        //  ACT
        double actual = simpleCalculator.divide(leftSide, rightSide);
        //  ASSERT
        assertThat(actual).isEqualTo(expected);
    }
}