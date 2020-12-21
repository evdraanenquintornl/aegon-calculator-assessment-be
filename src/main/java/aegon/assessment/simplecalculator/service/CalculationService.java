package aegon.assessment.simplecalculator.service;

import aegon.assessment.simplecalculator.model.CalculationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService {

    public CalculationDto calculate(List<String> argumentList) {
        return new CalculationDto("0", 0);
    }
}
