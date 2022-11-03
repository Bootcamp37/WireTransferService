package com.nttdata.bootcamp.WireTransfer.infraestructure;

import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferResponse;
import reactor.core.publisher.Mono;

public interface IWireTransferService {
    Mono<WireTransferResponse> wireTransfer(WireTransferRequest request);
}
