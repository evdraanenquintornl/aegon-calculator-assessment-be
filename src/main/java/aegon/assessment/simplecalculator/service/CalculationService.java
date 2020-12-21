package aegon.assessment.simplecalculator.service;

import aegon.assessment.simplecalculator.model.CalculationDto;
import aegon.assessment.simplecalculator.model.SimpleCalculator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CalculationService {
    SimpleCalculator simpleCalculator = new SimpleCalculator();

    public CalculationDto calculate(List<String> argumentList) {
        String operator = argumentList.get(1);

        int operatorIndex = argumentList.indexOf(operator);
        int leftSide = Integer.parseInt(argumentList.get(operatorIndex - 1));
        int rightSide = Integer.parseInt(argumentList.get(operatorIndex + 1));

        Double calculationResult = null;

        switch (operator) {
            case "/":
                calculationResult = simpleCalculator.divide(leftSide, rightSide);
                break;
            case "x":
                calculationResult = simpleCalculator.multiply(leftSide, rightSide);
                break;
            case "-":
                calculationResult = simpleCalculator.subtract(leftSide, rightSide);
                break;
            case "+":
                calculationResult = simpleCalculator.add(leftSide, rightSide);
                break;
        }

        if (Objects.isNull(calculationResult)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to parse given sum");
        }

        return new CalculationDto(argumentList.stream().collect(Collectors.joining(" ")), calculationResult);
    }
}
