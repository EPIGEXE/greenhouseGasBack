package project.ymkim.greenhousegas.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import project.ymkim.greenhousegas.model.entity.GreenhouseGasInventory;
import project.ymkim.greenhousegas.repository.custom.QueryGreenhouseGasInventoryRepo;

@Repository
public interface GreenhouseGasInventoryRepo extends MongoRepository<GreenhouseGasInventory, String>, QueryGreenhouseGasInventoryRepo {
    Optional<GreenhouseGasInventory> findByTitle(String title);
}
