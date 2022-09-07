package ru.ifmo.se.web4.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface Points extends CrudRepository<Point, Long> {
    @Modifying
    @Query("delete from Point p where p.creator_user=:name")
    void deleteBooks(@Param("name") String name);

}