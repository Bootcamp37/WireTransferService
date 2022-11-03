package com.nttdata.bootcamp.WireTransfer.domain;

import com.nttdata.bootcamp.WireTransfer.domain.dto.OperationRequest;
import com.nttdata.bootcamp.WireTransfer.domain.dto.OperationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OperationRepository {
    public static final String OPERATION_SERVICE = "ms-operation";
    @Value("${message.path-operationDomain")
    public String urlOperation;
    @Value("${message.path-post}")
    public String pathPost;
    @Autowired
    ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    public Mono<OperationResponse> postOperation(OperationRequest request) {
        WebClient webClientOperation = WebClient.builder().baseUrl(urlOperation).build();
        return webClientOperation.post()
                .uri(pathPost)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OperationResponse.class)
                .transform(it -> reactiveCircuitBreakerFactory.create(OPERATION_SERVICE)
                        .run(it, throwable -> Mono.just(new OperationResponse())));
    }
}
