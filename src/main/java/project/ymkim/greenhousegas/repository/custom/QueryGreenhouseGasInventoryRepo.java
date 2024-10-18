package project.ymkim.greenhousegas.repository.custom;

import java.util.List;

import project.ymkim.greenhousegas.model.entity.GreenhouseGasInventory;

public interface QueryGreenhouseGasInventoryRepo {
    List<GreenhouseGasInventory> findAllByParent(String parentId);
    List<GreenhouseGasInventory> findAllByTitle(String title);
}