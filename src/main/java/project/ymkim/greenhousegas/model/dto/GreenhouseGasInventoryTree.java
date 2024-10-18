package project.ymkim.greenhousegas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class GreenhouseGasInventoryTree {

    private String id;

    private String title;

    private Map<String, String> yearlyEmission;

    private List<GreenhouseGasInventoryTree> children;
}
