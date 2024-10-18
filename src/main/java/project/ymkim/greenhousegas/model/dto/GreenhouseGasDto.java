package project.ymkim.greenhousegas.model.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GreenhouseGasDto {
    private String title;
    private Map<String, String> yearlyEmission;
}
