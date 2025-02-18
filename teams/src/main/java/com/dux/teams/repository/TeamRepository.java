package com.dux.teams.repository;

import com.dux.teams.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {

    List<Team> findByNameContainingOrderByName(String name);
}
