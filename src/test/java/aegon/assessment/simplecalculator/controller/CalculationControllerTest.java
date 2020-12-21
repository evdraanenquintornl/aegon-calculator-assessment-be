package aegon.assessment.simplecalculator.controller;

import aegon.assessment.simplecalculator.model.CalculationDto;
import aegon.assessment.simplecalculator.service.CalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CalculationController.class)
class CalculationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculationService calculationService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void calculate() throws Exception {
        //  ARRANGE
        String sum = "";
        double result = 5.0;
        when(calculationService.calculate(any(ArrayList.class))).thenReturn(new CalculationDto(sum, result));
        //  ACT
        MvcResult actual = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/calculations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(anyList())))
                .andReturn();
        //  ASSERT
        assertThat(actual.getResponse().getStatus()).isEqualTo(200);

        CalculationDto calculationDto = objectMapper.readValue(actual.getResponse().getContentAsString(), CalculationDto.class);
        Assertions.assertThat(calculationDto.getSum()).isEqualTo(sum);
        Assertions.assertThat(calculationDto.getResult()).isEqualTo(result);
        Assertions.assertThat(calculationDto.getId());
    }

    @Test
    void getAllCalculations() throws Exception {
        //  ARRANGE
        CalculationDto expected = new CalculationDto("1 x 2", 2);
        when(this.calculationService.getAllCalculations()).thenReturn(List.of(expected));
        //  ACT
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/calculations")).andReturn();
        CalculationDto[] actualList = objectMapper.readValue(result.getResponse().getContentAsString(), CalculationDto[].class);
        //  ASSERT
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);

        Assertions.assertThat(actualList).contains(expected);
    }
}