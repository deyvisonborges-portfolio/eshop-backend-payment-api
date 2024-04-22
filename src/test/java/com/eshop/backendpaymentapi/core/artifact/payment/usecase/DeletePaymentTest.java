/**
 * Esta classe contém testes para a funcionalidade de exclusão de pagamento.
 */
package com.eshop.backendpaymentapi.core.artifact.payment.usecase;

import com.eshop.backendpaymentapi.core.artifacts.payment.Payment;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentMethod;
import com.eshop.backendpaymentapi.core.artifacts.payment.constant.PaymentStatus;
import com.eshop.backendpaymentapi.core.artifacts.payment.repository.PaymentRepositoryContract;
import com.eshop.backendpaymentapi.core.artifacts.payment.usecase.commands.delete.DeletePaymentCommandHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

/**
 * Esta classe contém os testes unitários para a funcionalidade de exclusão de pagamento.
 */
@ExtendWith(MockitoExtension.class)
public class DeletePaymentTest {
  /**
   * Mock para simular o comportamento de um repositório de pagamentos.
   */
  @Mock
  private PaymentRepositoryContract repository;

  /**
   * Instância da classe handler que será testada.
   */
  @InjectMocks
  private DeletePaymentCommandHandler handler;

  /**
   * Este método é executado antes de cada teste para redefinir o estado dos mocks.
   */
  @BeforeEach
  void cleanUp() {
    Mockito.reset(repository);
  }

  /**
   * Testa o caso em que um comando de exclusão válido é passado e o pagamento é excluído com sucesso.
   */
  @Test
  public void givenAValidCommand_whenCallsDeletePayment_shouldBeOk() {
    final var id = UUID.randomUUID().toString();
    final var payment = Payment.factory(10.00, PaymentStatus.OPEN, PaymentMethod.DEBIT_CARD, Instant.now(), id, id);

    final var expectedId = payment.getId().toString();

    /*
     * Configura o comportamento do mock repository para esperar a chamada do método delete
     * */
    Mockito.doNothing().when(repository).delete(
      Mockito.eq(
        expectedId
      )
    );

    Assertions.assertDoesNotThrow(() -> this.handler.execute(expectedId));

    /*
     * Verifica se o método delete foi chamado exatamente uma vez
     * */
    Mockito.verify(repository, Mockito.times(1)).delete(expectedId);
  }
}
