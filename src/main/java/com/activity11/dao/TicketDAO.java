package com.activity11.dao;
import java.util.Date;
import java.util.List;

import com.activity11.models.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketDAO extends JpaRepository<Ticket, Integer> {
    @Query("SELECT t FROM Ticket t WHERE t.createdDate = :creationDateTime")
    List<Ticket> findByCreatedDate(
      @Param("creationDateTime") Date creationDateTime);
}
