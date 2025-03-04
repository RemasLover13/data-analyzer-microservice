package com.remaslover.dataanalyzermicroservice.repository;

import com.remaslover.dataanalyzermicroservice.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
}
