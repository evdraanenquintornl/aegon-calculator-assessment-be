package aegon.assessment.simplecalculator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class CalculationDto {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @JsonIgnore
    private long id;
    private String sum;
    private double result;

    public CalculationDto(String sum, double result) {
        this.sum = sum;
        this.result = result;
    }
}
