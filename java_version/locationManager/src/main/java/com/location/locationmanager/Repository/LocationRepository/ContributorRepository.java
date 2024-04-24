package com.location.locationmanager.Repository.LocationRepository;

import com.location.locationmanager.JpaClass.LocationTable.Contributor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface ContributorRepository extends JpaRepository<Contributor,Integer> {
}
