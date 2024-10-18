package project.ymkim.greenhousegas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import project.ymkim.greenhousegas.common.http.GreenhouseGasResponse;
import project.ymkim.greenhousegas.model.dto.GreenhouseGasCompareRequest;
import project.ymkim.greenhousegas.model.dto.GreenhouseGasInventoryTree;
import project.ymkim.greenhousegas.model.entity.GreenhouseGasInventory;
import project.ymkim.greenhousegas.model.entity.ReigonData;
import project.ymkim.greenhousegas.service.GreenhouseGasService;

@RestController
@RequestMapping("/api/v1/greenhouseGas")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "온실가스 API", description = "온실가스 API")
public class GreenhouseController {
    
    private final GreenhouseGasService greenhouseGasService;

    public GreenhouseController(GreenhouseGasService greenhouseGasService) {
        this.greenhouseGasService = greenhouseGasService;
    }

    @Operation(summary = "온실가스 조회", description = "온실가스를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "온실가스 조회 성공"),
    })
    @GetMapping("/getAllGreenhouseGas")
    public ResponseEntity<GreenhouseGasResponse<List<GreenhouseGasInventory>>> getAllGreenhouseGas() {
        List<GreenhouseGasInventory> greenhouseGasInventoryList = greenhouseGasService.getAllGreenhouseGas();
        return ResponseEntity.ok(GreenhouseGasResponse.success(greenhouseGasInventoryList));
    }

    @Operation(summary = "온실가스 인벤토리 트리 조회", description = "온실가스 인벤토리 트리를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "온실가스 인벤토리 트리 조회 성공"),
    })
    @GetMapping("/getGreenhouseGasInventoryTree")
    public ResponseEntity<GreenhouseGasResponse<List<GreenhouseGasInventoryTree>>> getGreenhouseGasInventoryTree(@RequestParam String parentId) {
        List<GreenhouseGasInventoryTree> greenhouseGasInventoryTreeList = greenhouseGasService.getGreenhouseGasInventoryTree(parentId);
        return ResponseEntity.ok(GreenhouseGasResponse.success(greenhouseGasInventoryTreeList));
    }

    @Operation(summary = "온실가스 인벤토리 트리 조회", description = "온실가스 인벤토리 트리를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "온실가스 인벤토리 트리 조회 성공"),
    })
    @GetMapping("/getGreenhouseGasInventoryTreeByTitle")
    public ResponseEntity<GreenhouseGasResponse<List<GreenhouseGasInventoryTree>>> getGreenhouseGasInventoryTreeByTitle(@RequestParam String title) {
        List<GreenhouseGasInventoryTree> greenhouseGasInventoryTreeList = greenhouseGasService.getGreenhouseGasInventoryTreeByTitle(title);
        return ResponseEntity.ok(GreenhouseGasResponse.success(greenhouseGasInventoryTreeList));
    }

    @Operation(summary = "온실가스 인벤토리 트리 비교", description = "온실가스 인벤토리 트리를 비교합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "온실가스 인벤토리 트리 비교 성공"),
    })
    @PostMapping("/getGreenhouseGasInventoryTreeCompare")
    public ResponseEntity<GreenhouseGasResponse<List<GreenhouseGasInventoryTree>>> getGreenhouseGasInventoryTreeCompare(@RequestBody GreenhouseGasCompareRequest request) {
        List<GreenhouseGasInventoryTree> greenhouseGasInventoryTreeList = greenhouseGasService.getGreenhouseGasInventoryCompare(request.getYear1(), request.getYear2());
        return ResponseEntity.ok(GreenhouseGasResponse.success(greenhouseGasInventoryTreeList));
    }

    @Operation(summary = "지역별 온실가스 데이터 조회", description = "지역별 온실가스 데이터를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "지역별 온실가스 데이터 조회 성공"),
    })
    @GetMapping("/getReigonData")
    public ResponseEntity<GreenhouseGasResponse<List<ReigonData>>> getReigonData() {
        List<ReigonData> reigonDataList = greenhouseGasService.getAllReigonData();
        return ResponseEntity.ok(GreenhouseGasResponse.success(reigonDataList));
    }
}
