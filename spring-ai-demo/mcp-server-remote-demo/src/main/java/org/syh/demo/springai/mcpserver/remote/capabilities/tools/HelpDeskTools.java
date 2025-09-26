package org.syh.demo.springai.mcpserver.remote.capabilities.tools;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;
import org.syh.demo.springai.mcpserver.remote.entity.HelpDeskTicket;
import org.syh.demo.springai.mcpserver.remote.model.TicketRequest;
import org.syh.demo.springai.mcpserver.remote.service.HelpDeskTicketService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelpDeskTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelpDeskTools.class);

    private final HelpDeskTicketService helpDeskTicketService;
    
    @McpTool(name = "create_ticket", description = "Create a support ticket")
    public String createTicket(@McpToolParam(description = "Details to create a support ticket") TicketRequest ticketRequest) {
        LOGGER.info("Creating a support ticket for user {} with details {}", ticketRequest.username(), ticketRequest.issue());
        HelpDeskTicket savedTicket = helpDeskTicketService.createTicket(ticketRequest);
        LOGGER.info("Ticket #{} has been created successfully for user {}", savedTicket.getId(), savedTicket.getUsername());
        return "Ticket #" + savedTicket.getId() + " has been created successfully for user " + savedTicket.getUsername();
    }

    @McpTool(name = "get_ticket_status", description = "Fetch the status of the open tickets based on the given username")
    public List<HelpDeskTicket> getTicketStatus(@McpToolParam(description = "Username to fetch the status of the help desk tickets") String username) {
        LOGGER.info("Fetching the status of the open tickets for user {}", username);
        List<HelpDeskTicket> tickets = helpDeskTicketService.getTicketsByUsername(username);
        LOGGER.info("Found {} open tickets for user {}", tickets.size(), username);
        return tickets;
    }
}
