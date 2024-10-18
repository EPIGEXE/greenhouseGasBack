package project.ymkim.greenhousegas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import project.ymkim.greenhousegas.model.entity.ReigonData;

@Repository
public interface ReigonDataRepo extends MongoRepository<ReigonData, String> {
    
}
