package org.lbodzon.sample.txn.summary.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryContainer;
import org.lbodzon.sample.txn.summary.service.TransactionSummaryService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionSummaryControllerTest {

        @Mock
        TransactionSummaryService service;

        @Test
        public void testRequestProcessing() {
                TransactionSummaryController transactionSummaryController = new TransactionSummaryController(service);

                ClientTransactionEntryContainer container = new ClientTransactionEntryContainer();
                transactionSummaryController.retrieveTransactionSummary(container);

                Mockito.verify(service).createTransactionSummary(container);
                Mockito.verifyNoMoreInteractions(service);
        }
}
