package com.jpcchaves.finances.domain.repository;

import com.jpcchaves.finances.domain.model.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
}
