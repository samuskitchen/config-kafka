package com.apexdigital.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonDeserialize(as = EndDateRequest.class)
public class EndDateRequest {
    private String messageUniqueId;
    private Integer route;
    private Integer trip;
    private String user;
    private Integer amount;
    private String liquidateDate;
    private String tripDate;
    private String channel;
    private Instant eventDate;
}
