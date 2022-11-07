package com.nttdata.bootcamp.wiretransfer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO para los response de WireTransfer.
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WireTransferResponse {
  private String source;
  private String target;
  private Double amount;
}
