package com.igormokritsky.SpringSandBox.repository;

import com.igormokritsky.SpringSandBox.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {


}
