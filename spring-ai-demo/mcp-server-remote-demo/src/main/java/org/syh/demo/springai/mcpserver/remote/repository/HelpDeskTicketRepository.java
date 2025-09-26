package org.syh.demo.springai.mcpserver.remote.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syh.demo.springai.mcpserver.remote.entity.HelpDeskTicket;

public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTicket, Long> {
    List<HelpDeskTicket> findByUsername(String username);
}
