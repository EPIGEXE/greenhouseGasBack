package project.ymkim.greenhousegas.service;


import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.annotations.Generated;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import project.ymkim.greenhousegas.model.dto.GreenhouseGasInventoryTree;
import project.ymkim.greenhousegas.model.entity.GreenhouseGasInventory;
import project.ymkim.greenhousegas.model.entity.ReigonData;
import project.ymkim.greenhousegas.repository.GreenhouseGasInventoryRepo;
import project.ymkim.greenhousegas.repository.ReigonDataRepo;
import project.ymkim.greenhousegas.repository.Impl.QueryGreenhouseGasInventoryRepoImpl;




@Slf4j
@Service
public class GreenhouseGasService {

    @Autowired
    private GreenhouseGasInventoryRepo greenhouseGasInventoryRepo;

    @Autowired
    private ReigonDataRepo reigonDataRepo;

    @Autowired
    private QueryGreenhouseGasInventoryRepoImpl queryGreenhouseGasInventoryRepoImpl;

    // @PostConstruct
    // private void dataProcess() {
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     try {
    //         ClassPathResource resource = new ClassPathResource("util/RegionData.json");
    //         JsonNode rootNode = objectMapper.readTree(resource.getInputStream());
            
    //         Map<String, ReigonData> reigonDataMap = new HashMap<>();
            
    //         for (JsonNode node : rootNode) {
    //             String region = node.get("METROPL_SIDO_NM").asText();
    //             String year = node.get("YY").asText();
    //             String emission = node.get("GAS_EMISN_AMNT").asText();
                
    //             ReigonData reigonData = reigonDataMap.computeIfAbsent(region, k -> {
    //                 ReigonData newRegion = new ReigonData();
    //                 newRegion.setRegion(region);
    //                 newRegion.setYearlyEmission(new HashMap<>());
    //                 return newRegion;
    //             });
                
    //             reigonData.getYearlyEmission().put(year, emission);
    //         }
            
    //         List<ReigonData> reigonDataList = new ArrayList<>(reigonDataMap.values());
            
    //         log.info("데이터 로드 완료: {} 개의 항목", reigonDataList.size());
    //         reigonDataRepo.saveAll(reigonDataList);
    //     } catch (IOException e) {
    //         log.error("데이터 파일을 로드하는 중 오류 발생", e);
    //     }
    // }

    public List<GreenhouseGasInventory> getAllGreenhouseGas() {
        return greenhouseGasInventoryRepo.findAll();
    }

    public List<GreenhouseGasInventoryTree> getGreenhouseGasInventoryTree(String parentId) {
        List<GreenhouseGasInventory> inventories = queryGreenhouseGasInventoryRepoImpl.findAllByParent(parentId);
        return inventories.stream().map(this::convertToTree).collect(Collectors.toList());
    }
    
    public List<GreenhouseGasInventoryTree> getGreenhouseGasInventoryTreeByTitle(String title) {
        List<GreenhouseGasInventory> inventories = queryGreenhouseGasInventoryRepoImpl.findAllByTitle(title);
        // title에 해당하는 요소의 자식들만 반환
        return inventories.stream()
            .flatMap(inventory -> getGreenhouseGasInventoryTree(inventory.getId()).stream())
            .collect(Collectors.toList());
    }

    public List<GreenhouseGasInventoryTree> getGreenhouseGasInventoryCompare( String year1, String year2) {
        List<GreenhouseGasInventory> inventories = getAllGreenhouseGas();
        return inventories.stream()
            .map(tree -> calculateDifference(tree, year1, year2))
            .collect(Collectors.toList());
    }
    
    private GreenhouseGasInventoryTree calculateDifference(GreenhouseGasInventory inventory, String year1, String year2) {
        Map<String, String> newYearlyEmission = new HashMap<>();
        String difference = calculateEmissionDifference(inventory.getYearlyEmission(), year1, year2);
        newYearlyEmission.put("difference", difference);
    
        return new GreenhouseGasInventoryTree(
            inventory.getId(),
            inventory.getTitle(),
            newYearlyEmission,
            null
        );
    }
    
    private String calculateEmissionDifference(Map<String, String> yearlyEmission, String year1, String year2) {
        double emission1 = Double.parseDouble(yearlyEmission.getOrDefault(year1, "0"));
        double emission2 = Double.parseDouble(yearlyEmission.getOrDefault(year2, "0"));
        return String.format("%.4f", emission2 - emission1);
    }

    private GreenhouseGasInventoryTree convertToTree(GreenhouseGasInventory inventory) {
        List<GreenhouseGasInventoryTree> children = getGreenhouseGasInventoryTree(inventory.getId());
        return new GreenhouseGasInventoryTree(
            inventory.getId(),
            inventory.getTitle(),
            inventory.getYearlyEmission(),
            children
        );
    }

    public List<ReigonData> getAllReigonData() {
        return reigonDataRepo.findAll();
    }

}
