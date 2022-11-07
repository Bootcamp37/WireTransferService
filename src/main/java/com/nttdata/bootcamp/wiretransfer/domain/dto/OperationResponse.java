package com.nttdata.bootcamp.wiretransfer.domain.dto;

import com.nttdata.bootcamp.wiretransfer.domain.entity.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO para los response de Operation.
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
  private String id;
  private String customerPassiveProductId;
  private OperationType operationType;
  private Double amount;
  private String operationDate;
}
