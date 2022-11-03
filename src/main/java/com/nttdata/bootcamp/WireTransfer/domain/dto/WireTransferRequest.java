package com.nttdata.bootcamp.WireTransfer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WireTransferRequest {
    private String source;
    private String target;
    private Double amount;
}
