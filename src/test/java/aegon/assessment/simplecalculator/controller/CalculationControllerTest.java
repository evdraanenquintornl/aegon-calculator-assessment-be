package aegon.assessment.simplecalculator.controller;

import aegon.assessment.simplecalculator.model.CalculationDto;
import aegon.assessment.simplecalculator.service.CalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                MockMvcRequestBuilders.post("/calculations")).andReturn();
        //  ASSERT
        assertThat(actual.getResponse().getStatus()).isEqualTo(200);

        CalculationDto calculationDto = objectMapper.readValue(actual.getResponse().getContentAsString(), CalculationDto.class);
        Assertions.assertThat(calculationDto.getSum()).isEqualTo(sum);
        Assertions.assertThat(calculationDto.getResult()).isEqualTo(result);
    }
}