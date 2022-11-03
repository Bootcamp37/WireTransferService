package com.nttdata.bootcamp.WireTransfer.application;

import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferResponse;
import com.nttdata.bootcamp.WireTransfer.infraestructure.IWireTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("${message.path-wireTransfer}")
@RefreshScope
@Slf4j
public class WireTransferController {
    @Autowired
    private IWireTransferService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<WireTransferResponse> getWireTransfer(@RequestBody WireTransferRequest request) {
        log.debug("====> WireTransferController: GetWireTransfer");
        return service.wireTransfer(request);
    }
}
