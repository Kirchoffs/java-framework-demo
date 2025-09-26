package org.syh.demo.springai.toolcalling.repository;

import org.syh.demo.springai.toolcalling.entity.HelpDeskTicket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTicket, Long> {
    List<HelpDeskTicket> findByUsername(String username);
    @NonNull
    Optional<HelpDeskTicket> findById(@NonNull Long ticketId);
}
