package aegon.assessment.simplecalculator.controller;

import aegon.assessment.simplecalculator.model.CalculationDto;
import aegon.assessment.simplecalculator.service.CalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calculations")
public class CalculationController {

    private CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping
    public ResponseEntity<CalculationDto> calculate(ArrayList<String> argumentList) {
        CalculationDto calculationDto = this.calculationService.calculate(argumentList);
        return ResponseEntity.ok(calculationDto);
    }
}
