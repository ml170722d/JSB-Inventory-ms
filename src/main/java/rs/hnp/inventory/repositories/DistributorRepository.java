package rs.hnp.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.hnp.inventory.models.Distributor;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
}
