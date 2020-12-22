package aegon.assessment.simplecalculator.service;

import aegon.assessment.simplecalculator.model.CalculationDto;
import aegon.assessment.simplecalculator.model.SimpleCalculator;
import aegon.assessment.simplecalculator.repository.CalculationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CalculationService {
    private CalculationRepository calculationRepository;
    SimpleCalculator simpleCalculator = new SimpleCalculator();

    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    /**
     * Calculates the result of a simple sum, only the first three entries are used of list.
     *
     * @param argumentList, sum cut into a list of strings
     * @return calculationDto, http bad request if not able to process
     */
    public CalculationDto calculate(List<String> argumentList) {
        if (argumentList.size() >= 4) {
            throw new ResponseStatusException(HttpStatus.LENGTH_REQUIRED, "Currently only able to parse simple sums");
        }

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

        CalculationDto calculationDto = new CalculationDto(argumentList.stream().collect(Collectors.joining(" ")), calculationResult);
        calculationRepository.save(calculationDto);
        return calculationDto;
    }

    public List<CalculationDto> getAllCalculations() {
        return calculationRepository.findAll();
    }
}
