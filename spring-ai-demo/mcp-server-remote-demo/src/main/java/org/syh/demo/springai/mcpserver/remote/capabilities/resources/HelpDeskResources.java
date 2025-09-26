package org.syh.demo.springai.mcpserver.remote.capabilities.resources;

import org.springaicommunity.mcp.annotation.McpResource;
import org.springframework.stereotype.Service;

@Service
public class HelpDeskResources {
    @McpResource(
        uri = "docs://helpdesk/ticket_status_information", 
        name = "ticket_status_information", 
        description = "How to update the ticket status"
    )
    public String ticketStatusInformation() {
        return
            """
                You are not allowed to update the ticket status.
                Please contact the support team for assistance.
            """;
    }
}
