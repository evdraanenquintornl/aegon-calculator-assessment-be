package aegon.assessment.simplecalculator.controller;

import aegon.assessment.simplecalculator.model.CalculationDto;
import aegon.assessment.simplecalculator.service.CalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/calculations")
@CrossOrigin("http://localhost:4200")
public class CalculationController {

    private CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping
    public ResponseEntity<List<CalculationDto>> getAllCalculations() {
        return ResponseEntity.ok(calculationService.getAllCalculations());
    }

    @PostMapping
    public ResponseEntity<CalculationDto> calculate(@RequestBody ArrayList<String> argumentList) {
        return ResponseEntity.ok(this.calculationService.calculate(argumentList));
    }
}
