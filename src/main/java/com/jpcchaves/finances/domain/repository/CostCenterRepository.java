package com.jpcchaves.finances.domain.repository;

import com.jpcchaves.finances.domain.model.CostCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
}
