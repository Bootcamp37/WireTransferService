package com.nttdata.bootcamp.WireTransfer.infraestructure;

import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferResponse;

public interface IWireTransferMapper {
    WireTransferResponse toResponse(WireTransferRequest request);
}
