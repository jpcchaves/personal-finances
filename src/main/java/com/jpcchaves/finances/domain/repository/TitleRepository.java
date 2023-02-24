package com.jpcchaves.finances.domain.repository;

import com.jpcchaves.finances.domain.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
}
