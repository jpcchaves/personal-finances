package com.jpcchaves.finances.domain.repository;

import com.jpcchaves.finances.domain.model.CostCenter;
import com.jpcchaves.finances.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
    List<CostCenter> findByUser(User user);

}
