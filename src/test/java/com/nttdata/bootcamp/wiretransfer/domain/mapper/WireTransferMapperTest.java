package com.nttdata.bootcamp.wiretransfer.domain.mapper;

import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.wiretransfer.domain.dto.WireTransferResponse;
import com.nttdata.bootcamp.wiretransfer.infraestructure.IWireTransferMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WireTransferMapperTest {

  private IWireTransferMapper mapper = new WireTransferMapper();

  @Test
  void toResponse() {
    WireTransferRequest request = new WireTransferRequest("1a1","1b2",500.0);
    WireTransferResponse response = new WireTransferResponse("1a1","1b2",500.0);
    assertEquals(mapper.toResponse(request), response);
  }
}