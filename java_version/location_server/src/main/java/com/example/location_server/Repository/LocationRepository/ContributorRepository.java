package com.example.location_server.Repository.LocationRepository;

import com.example.location_server.JpaClass.LocationTable.Contributor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ContributorRepository extends JpaRepository<Contributor,Integer> {

}
