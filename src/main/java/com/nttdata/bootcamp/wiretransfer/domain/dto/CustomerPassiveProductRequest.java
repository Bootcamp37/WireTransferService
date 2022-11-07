package com.nttdata.bootcamp.wiretransfer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerPassiveProductRequest.
 *
 * <p>Clase DTO para manejar el request de CustomePassiveProduct.</p>
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPassiveProductRequest {
  private String customerId;
  private String productId;
  private Double maintenance;
  private Integer movementLimit;
  private Integer movementDay;
  private Integer movementMonth;
  private Double amount;
}
