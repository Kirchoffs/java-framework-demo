package org.syh.demo.springai.toolcalling.model;

import java.util.Optional;

import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

@Data
public class UpdateTicketStatusRequest {
    @ToolParam(description = "Ticket ID")
    private Long id;

    @ToolParam(description = "There are four status: OPEN / IN-PROGRESS / RESOLVED / FAILED")
    private String ticketStatus;

    @ToolParam(description = "Reason for the status update")
    private Optional<String> reason;

    @ToolParam(description = "Additional comment besides reason", required = false)
    private String comment;
}
