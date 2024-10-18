package project.ymkim.greenhousegas.model.entity;

import java.util.Map;
import java.util.HashMap;

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
@Document(collection = "regionData")
public class ReigonData {
    
    @Id
    private String id;

    private String region;

    private Map<String, String> yearlyEmission = new HashMap<>();
    
}
