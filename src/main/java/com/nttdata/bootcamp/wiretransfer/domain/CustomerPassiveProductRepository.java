package com.nttdata.bootcamp.wiretransfer.domain;

import com.nttdata.bootcamp.wiretransfer.domain.dto.CustomerPassiveProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * CustomerPassiveProductRepository.
 *
 * <p>Clase del tipo repositorio para consultar las cuentas pasivas.</p>
 *
 * @author Pedro Manuel Díaz Santa María
 * @version 1.0.0
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerPassiveProductRepository {
  public static final String CUSTOMER_PRODUCT_SERVICE = "ms-customerProduct";
  @Value("${message.path-customerProductDomain}")
  public String urlCustomerProduct;
  @Value("${message.path-get}")
  public String pathGet;
  @Value("${message.path-update}")
  public String pathUpdate;
  @Autowired
  ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

  /**
   * Método GetById.
   *
   * @param id   Variable del tipo String
   * @return     Retorna Mono < CustomerPassiveProductResponse >
   */
  public Mono<CustomerPassiveProductResponse> getById(String id) {
    log.info("====> CustomerPassiveProductRepository: GetById");
    log.info("====> Call : " + urlCustomerProduct + pathGet + id);
    WebClient webClientProduct = WebClient.builder().baseUrl(urlCustomerProduct).build();
    return webClientProduct.get()
          .uri(pathGet + "{id}", id)
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .bodyToMono(CustomerPassiveProductResponse.class)
          .transform(it -> reactiveCircuitBreakerFactory.create(CUSTOMER_PRODUCT_SERVICE)
                .run(it, throwable -> Mono.just(new CustomerPassiveProductResponse()))
          );
  }
}
