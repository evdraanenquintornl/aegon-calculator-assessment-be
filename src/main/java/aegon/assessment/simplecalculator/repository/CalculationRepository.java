package aegon.assessment.simplecalculator.repository;

import aegon.assessment.simplecalculator.model.CalculationDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<CalculationDto, Long> {
}
