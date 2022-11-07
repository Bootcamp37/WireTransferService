package com.nttdata.bootcamp.wiretransfer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO paro el Request de WireTransfer.
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WireTransferRequest {
  private String source;
  private String target;
  private Double amount;
}
