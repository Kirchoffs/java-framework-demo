package org.syh.demo.springai.toolcalling.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.syh.demo.springai.toolcalling.entity.HelpDeskTicket;
import org.syh.demo.springai.toolcalling.model.CreateTicketRequest;
import org.syh.demo.springai.toolcalling.repository.HelpDeskTicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {
    private final HelpDeskTicketRepository helpDeskTicketRepository;

    public HelpDeskTicket createTicket(CreateTicketRequest ticketInput, String username) {
        HelpDeskTicket ticket = HelpDeskTicket.builder()
            .issue(ticketInput.issue())
            .username(username)
            .status("OPEN")
            .createdAt(LocalDateTime.now())
            .eta(LocalDateTime.now().plusDays(7))
            .build();

        return helpDeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketsByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }

    public HelpDeskTicket getTicketById(Long id) {
        Optional<HelpDeskTicket> helpDeskTicket = helpDeskTicketRepository.findById(id);
        return helpDeskTicket.orElse(null);
    }

    public void updateTicket(String username, HelpDeskTicket helpDeskTicket) {
        if (helpDeskTicket == null) {
            throw new RuntimeException("Help desk ticket cannot be null");
        }

        if (!username.equals(helpDeskTicket.getUsername())) {
            throw new RuntimeException(
                "You are not authorized to update the specified support ticket"
            );
        }

        helpDeskTicketRepository.save(helpDeskTicket);
    }
}
