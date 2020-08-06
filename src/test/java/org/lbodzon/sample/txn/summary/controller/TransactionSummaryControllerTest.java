package org.lbodzon.sample.txn.summary.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryContainer;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryTuple;
import org.lbodzon.sample.txn.summary.model.TransactionSummaryContainer;
import org.lbodzon.sample.txn.summary.service.TransactionSummaryService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionSummaryControllerTest {

        @Mock
        TransactionSummaryService service;

        @Test
        public void testRequestProcessing() {
                when(service.createTransactionSummary(Mockito.any())).thenCallRealMethod();

                TransactionSummaryController transactionSummaryController = new TransactionSummaryController(service);

                ClientTransactionEntryContainer container = new ClientTransactionEntryContainer();
                container.setClientTransactionEntryTuple(new ClientTransactionEntryTuple());
                container.getClientTransactionEntryTuple().setEntries(new LinkedList<>());
                TransactionSummaryContainer summaryContainer = transactionSummaryController.retrieveTransactionSummary(container);

                assertNotNull(summaryContainer);

                Mockito.verify(service).createTransactionSummary(container);
                Mockito.verifyNoMoreInteractions(service);
        }
}
