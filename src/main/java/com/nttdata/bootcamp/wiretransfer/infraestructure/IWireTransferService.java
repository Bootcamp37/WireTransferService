package com.nttdata.bootcamp.wiretransfer.infraestructure;

import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferResponse;
import reactor.core.publisher.Mono;

/**
 * Interfaz par WireTransferService.
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
public interface IWireTransferService {
  Mono<WireTransferResponse> wireTransfer(WireTransferRequest request);
}
