package org.syh.demo.springai.toolcalling.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
public class UpdateTicketStatusRequest {
    @JsonPropertyDescription("Ticket ID")
    private Long id;

    @JsonPropertyDescription("There are four status: OPEN / IN-PROGRESS / RESOLVED / FAILED")
    private String ticketStatus;
}
