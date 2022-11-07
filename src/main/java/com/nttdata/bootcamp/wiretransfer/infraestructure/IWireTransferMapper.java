package com.nttdata.bootcamp.wiretransfer.infraestructure;

import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferResponse;

/**
 * Interfaz para el WireTransferMapper.
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
public interface IWireTransferMapper {
  WireTransferResponse toResponse(WireTransferRequest request);
}
