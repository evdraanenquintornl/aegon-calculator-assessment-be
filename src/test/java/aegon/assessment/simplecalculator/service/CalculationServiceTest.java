package aegon.assessment.simplecalculator.service;

import aegon.assessment.simplecalculator.model.CalculationDto;
import aegon.assessment.simplecalculator.repository.CalculationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculationServiceTest {

    @InjectMocks
    CalculationService calculationService;

    @Mock
    CalculationRepository calculationRepository;

    @ParameterizedTest
    @CsvSource({
            "1 * 2, 2",
            "3 / 2, 1.5",
            "10 + 5, 15",
            "25 - 10, 15"
    })
    void calculate_simple_sums(String sum, double result) throws IOException {
        //  ARRANGE
        ArrayList<String> argumentList = new ArrayList<>(List.of(sum.split(" ")));
        //  ACT
        CalculationDto actual = this.calculationService.calculate(argumentList);
        //  ASSERT
        Assertions.assertThat(actual.getSum()).isEqualTo(sum);
        assertThat(actual.getResult()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({
            "1 * 2 + 2 * 5, 13",
            "3 / 2 + 6 / 3, 3.5",
            "10 + 5 + 15, 30",
            "25 - 10 - 10 - 9, 9"
    })
    void calculate_extended_sums(String sum, double result) throws IOException {
        //  ARRANGE
        ArrayList<String> argumentList = new ArrayList<>(List.of(sum.split(" ")));
        //  ACT
        CalculationDto actual = this.calculationService.calculate(argumentList);
        //  ASSERT
        Assertions.assertThat(actual.getSum()).isEqualTo(sum);
        assertThat(actual.getResult()).isEqualTo(result);
    }
}