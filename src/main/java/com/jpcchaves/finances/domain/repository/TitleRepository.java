package com.jpcchaves.finances.domain.repository;

import com.jpcchaves.finances.domain.model.Title;
import com.jpcchaves.finances.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM public.title " +
                    "WHERE expiration_date " +
                    "BETWEEN TO_TIMESTAMP(:initialDate, 'YYYY-MM-DD hh24:MI:SS') AND " +
                    "TO_TIMESTAMP(:finalDate, 'YYYY-MM-DD hh24:MI:SS')"
    )
    List<Title> findByExpirationDate(@Param("initialDate") String initialDate, @Param("finalDate") String finalDate);

    List<Title> findByUser(User user);
}
