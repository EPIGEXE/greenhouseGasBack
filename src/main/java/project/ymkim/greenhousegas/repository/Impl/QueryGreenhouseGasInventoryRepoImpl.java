package project.ymkim.greenhousegas.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import project.ymkim.greenhousegas.model.entity.GreenhouseGasInventory;
import project.ymkim.greenhousegas.repository.custom.QueryGreenhouseGasInventoryRepo;

@Slf4j
@Repository
public class QueryGreenhouseGasInventoryRepoImpl implements QueryGreenhouseGasInventoryRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<GreenhouseGasInventory> findAllByParent(String parentId) {
        Query query = new Query(Criteria.where("parent").is(parentId));
        List<GreenhouseGasInventory> inventories = mongoTemplate.find(query, GreenhouseGasInventory.class);
        return inventories;
    }

    @Override
    public List<GreenhouseGasInventory> findAllByTitle(String title) {
        Query query = new Query(Criteria.where("title").is(title));
        List<GreenhouseGasInventory> inventories = mongoTemplate.find(query, GreenhouseGasInventory.class);
        return inventories;
    }

}