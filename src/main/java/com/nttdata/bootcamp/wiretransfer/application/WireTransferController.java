package com.nttdata.bootcamp.wiretransfer.application;

import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferResponse;
import com.nttdata.bootcamp.wiretransfer.infraestructure.IWireTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * WireTransferController.
 * <p>Clase ControllerRest para los metodos de Transferencia bancaria.</p>
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-wireTransfer}")
@RefreshScope
@Slf4j
public class WireTransferController {
  @Autowired
  private IWireTransferService service;

  /**
   * Método GetWireTransfer.
   *
   * <p>Esté realiza la transferencia bancaria.</p>
   *
   * @param request    Variable del tipo WireTransferRequest.
   * @return           Retorna un Mono < WireTransferResponse >
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<WireTransferResponse> getWireTransfer(@RequestBody WireTransferRequest request) {
    log.debug("====> WireTransferController: GetWireTransfer");
    return service.wireTransfer(request);
  }
}
