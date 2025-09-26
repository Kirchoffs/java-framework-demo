package org.syh.demo.springai.toolcalling.tools;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.syh.demo.springai.toolcalling.entity.HelpDeskTicket;
import org.syh.demo.springai.toolcalling.model.CreateTicketRequest;
import org.syh.demo.springai.toolcalling.model.UpdateTicketStatusRequest;
import org.syh.demo.springai.toolcalling.service.HelpDeskTicketService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HelpDeskTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskTools.class);

    private final HelpDeskTicketService helpDeskTicketService;
    
    @Tool(name = "create_ticket", description = "Create a support ticket", returnDirect = true)
    public String createTicket(
        @ToolParam(description = "Details to create a support ticket") CreateTicketRequest createTicketRequest,
        ToolContext toolContext
    ) {
        String username = (String) toolContext.getContext().get("username");
        LOGGER.info("Creating a support ticket for user {} with issue {}", username, createTicketRequest.issue());

        HelpDeskTicket savedTicket = helpDeskTicketService.createTicket(createTicketRequest, username);
        LOGGER.info("Ticket #{} has been created successfully for user {}", savedTicket.getId(), savedTicket.getUsername());

        return "Ticket #" + savedTicket.getId() + " has been created successfully for user " + savedTicket.getUsername();
    }

    @Tool(name = "get_ticket_status", description = "Fetch the status of the open tickets based on the given username")
    public List<HelpDeskTicket> getTicketStatus(ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        LOGGER.info("Fetching the status of the open tickets for user {}", username);

        List<HelpDeskTicket> tickets = helpDeskTicketService.getTicketsByUsername(username);
        LOGGER.info("Found {} open tickets for user {}", tickets.size(), username);

        return tickets;
    }

    @Tool(name = "update_ticket_status", description = "Update the status of an existing support ticket")
    public boolean updateTicketStatus(UpdateTicketStatusRequest updateTicketStatusRequest, ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");

        Long ticketId = updateTicketStatusRequest.getId();
        HelpDeskTicket ticket = helpDeskTicketService.getTicketById(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Ticket #" + ticketId + " does not exist");
        }
        ticket.setStatus(updateTicketStatusRequest.getTicketStatus());

        helpDeskTicketService.updateTicket(username, ticket);
        return true;
    }
}
