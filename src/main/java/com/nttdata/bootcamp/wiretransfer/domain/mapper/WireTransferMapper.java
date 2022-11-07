package com.nttdata.bootcamp.wiretransfer.domain.mapper;

import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferResponse;
import com.nttdata.bootcamp.wiretransfer.infraestructure.IWireTransferMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * Clase WireTransferMapper.
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Component
@Slf4j
public class WireTransferMapper implements IWireTransferMapper {

  /**
   * Método ToResponse.
   *
   * @param request   Variable del tipo WireTransferRequest
   * @return          Retorna WireTransferResponse
   */
  @Override
  public WireTransferResponse toResponse(WireTransferRequest request) {
    log.debug("====> WireTransferMapper: ToResponse");
    WireTransferResponse wireTransferResponse = new WireTransferResponse();
    BeanUtils.copyProperties(request, wireTransferResponse);
    return wireTransferResponse;
  }
}
