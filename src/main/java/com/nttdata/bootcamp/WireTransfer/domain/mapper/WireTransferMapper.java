package com.nttdata.bootcamp.WireTransfer.domain.mapper;

import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferResponse;
import com.nttdata.bootcamp.WireTransfer.infraestructure.IWireTransferMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WireTransferMapper implements IWireTransferMapper {
    @Override
    public WireTransferResponse toResponse(WireTransferRequest request) {
        log.debug("====> WireTransferMapper: ToResponse");
        WireTransferResponse wireTransferResponse = new WireTransferResponse();
        BeanUtils.copyProperties(request, wireTransferResponse);
        return wireTransferResponse;
    }
}
