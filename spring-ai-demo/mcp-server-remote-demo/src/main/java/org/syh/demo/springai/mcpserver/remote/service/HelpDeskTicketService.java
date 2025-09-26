package org.syh.demo.springai.mcpserver.remote.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.syh.demo.springai.mcpserver.remote.entity.HelpDeskTicket;
import org.syh.demo.springai.mcpserver.remote.model.TicketRequest;
import org.syh.demo.springai.mcpserver.remote.repository.HelpDeskTicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {
    private final HelpDeskTicketRepository helpDeskTicketRepository;

    public HelpDeskTicket createTicket(TicketRequest ticketInput) {
        HelpDeskTicket ticket = HelpDeskTicket.builder()
            .issue(ticketInput.issue())
            .username(ticketInput.username())
            .status("OPEN")
            .createdAt(LocalDateTime.now())
            .eta(LocalDateTime.now().plusDays(7))
            .build();

        return helpDeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }
}
