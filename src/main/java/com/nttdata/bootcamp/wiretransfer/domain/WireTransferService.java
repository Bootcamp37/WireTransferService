package com.nttdata.bootcamp.wiretransfer.domain;

import com.nttdata.bootcamp.wiretransfer.domain.dto.OperationRequest;
import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferResponse;
import com.nttdata.bootcamp.wiretransfer.domain.entity.OperationType;
import com.nttdata.bootcamp.wiretransfer.infraestructure.IWireTransferMapper;
import com.nttdata.bootcamp.wiretransfer.infraestructure.IWireTransferService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Clase Service.
 * WireTransferServive.
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WireTransferService implements IWireTransferService {
  @Autowired
  private final CustomerPassiveProductRepository customerProductRepository;
  @Autowired
  private final OperationRepository operationRepository;
  @Autowired
  private final IWireTransferMapper wireTransferMapper;

  /**
   * Método WireTransfer.
   *
   * @param request   Variable del tipo WireTransferRequest.
   * @return          Retorna un Mono < WireTransferResponse >
   */
  @Override
  public Mono<WireTransferResponse> wireTransfer(WireTransferRequest request) {
    log.info("====> WireTransferService: wireTransfer");
    if (request.getSource().equals(request.getTarget())) {
      log.info("====> WireTransferService: Transferencia a la misma cuenta");
      return Mono.error(RuntimeException::new);
    }
    return customerProductRepository.getById(request.getSource())
          .map(
                e -> {
                  log.info(" 1 ====> " + e.toString());
                  return e;
                }
          )
          .zipWith(customerProductRepository.getById(request.getTarget()))
          .map(
                e -> {
                  log.info(" 2 ====> " + e.getT1().toString());
                  log.info(" 3 ====> " + e.getT2().toString());
                  return e;
                }
          )
          .flatMap(e -> {
            if (e.getT1().getAmount() < request.getAmount()) {
              return Mono.error(RuntimeException::new);
            }
            // Leer operacion y restar Amount en la cuenta origen
            OperationRequest withdrawal = getOperationRequest(request, OperationType.WITHDRAWAL, request.getSource());
            return operationRepository.postOperation(withdrawal)
                  .map(f -> {
                    log.info(" 4 ====> " + f.toString());
                    return f;
                  });
          })
          .flatMap(e -> {
            // Leer operación y agregar Amount en la cuenta destino
            OperationRequest deposit = getOperationRequest(request, OperationType.DEPOSIT, request.getTarget());
            return operationRepository.postOperation(deposit)
                  .map(f -> {
                    log.info(" 5 ====> " + f.toString());
                    return f;
                  });
          })
          .flatMap(e -> Mono.just(request).map(wireTransferMapper::toResponse));
  }

  /**
   * Método GetOperationRequest.
   *
   * @param request          Variable del tipo WireTransferRequest.
   * @param operationType    Variable del tipo OperationType.
   * @return                 Retorna un OperationRequest.
   */
  private OperationRequest getOperationRequest(WireTransferRequest request,
                                               OperationType operationType, String id) {
    log.info("====> WireTransferService: GetOperationRequest");
    OperationRequest operationRequest = new OperationRequest();
    operationRequest.setAmount(request.getAmount());
    operationRequest.setOperationType(operationType);
    operationRequest.setCustomerPassiveProductId(id);
    operationRequest.setOperationDate(getDate());
    return operationRequest;
  }

  /**
   * Método GetDate.
   *
   * @return    Retorna un String.
   */
  public String getDate() {
    log.info("====> WireTransferService: GetDate");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    return dtf.format(now);
  }
}
