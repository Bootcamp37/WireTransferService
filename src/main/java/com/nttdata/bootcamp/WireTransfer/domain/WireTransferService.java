package com.nttdata.bootcamp.WireTransfer.domain;

import com.nttdata.bootcamp.WireTransfer.domain.dto.OperationRequest;
import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferRequest;
import com.nttdata.bootcamp.WireTransfer.domain.dto.WireTransferResponse;
import com.nttdata.bootcamp.WireTransfer.domain.entity.OperationType;
import com.nttdata.bootcamp.WireTransfer.infraestructure.IWireTransferMapper;
import com.nttdata.bootcamp.WireTransfer.infraestructure.IWireTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Override
    public Mono<WireTransferResponse> wireTransfer(WireTransferRequest request) {
        log.debug("====> WireTransferService: wireTransfer");
        if (request.getSource().equals(request.getTarget())) return Mono.error(RuntimeException::new);
        return customerProductRepository.getById(request.getSource())
                .zipWith(customerProductRepository.getById(request.getTarget()))
                .flatMap(e -> {
                    if (e.getT2().getAmount() < request.getAmount()) return Mono.error(RuntimeException::new);
                    // Leer operacion y restar Amount en la cuenta origen
                    OperationRequest withdrawal = getOperationRequest(request, OperationType.WITHDRAWAL);
                    return operationRepository.postOperation(withdrawal);
                })
                .flatMap(e -> {
                    // Leer operación y agregar Amount en la cuenta destino
                    OperationRequest deposit = getOperationRequest(request, OperationType.DEPOSIT);
                    return operationRepository.postOperation(deposit);
                })
                .flatMap(e -> {
                    return Mono.just(request)
                            .map(wireTransferMapper::toResponse);
                });
    }

    private OperationRequest getOperationRequest(WireTransferRequest request, OperationType operationType) {
        log.debug("====> WireTransferService: GetOperationRequest");
        OperationRequest operationRequest = new OperationRequest();
        operationRequest.setAmount(request.getAmount());
        operationRequest.setOperationType(operationType);
        operationRequest.setCustomerPassiveProductId(request.getSource());
        operationRequest.setOperationDate(getDate());
        return operationRequest;
    }

    public String getDate() {
        log.debug("====> WireTransferService: GetDate");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
