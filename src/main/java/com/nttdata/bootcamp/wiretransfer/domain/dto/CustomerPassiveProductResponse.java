package com.nttdata.bootcamp.wiretransfer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerPassiveProductResponse.
 *
 * <p>Clase DTO para manejar el response de CustomePassiveProduct.</p>
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPassiveProductResponse {
  private String id;
  private String customerId;
  private String customerUrl;
  private String productId;
  private String productUrl;
  private Double maintenance;
  private Integer movementLimit;
  private Integer movementDay;
  private Integer movementMonth;
  private Double amount;
}