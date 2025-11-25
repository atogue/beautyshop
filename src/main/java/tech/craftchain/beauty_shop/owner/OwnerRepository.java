package tech.craftchain.beauty_shop.owner;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends MongoRepository<Owner, String> {
    Optional<Owner> findByName(String name);
}
