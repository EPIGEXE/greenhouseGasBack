package project.ymkim.greenhousegas.model.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "greenhouseGas Inventory2")
public class GreenhouseGasInventory {
    
    @Id
    private String id;

    private String title;

    private Map<String, String> yearlyEmission;

    private String parent;

}
