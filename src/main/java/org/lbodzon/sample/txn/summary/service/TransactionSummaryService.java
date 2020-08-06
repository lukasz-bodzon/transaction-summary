package org.lbodzon.sample.txn.summary.service;

import org.lbodzon.sample.txn.summary.model.Balance;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntry;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryContainer;
import org.lbodzon.sample.txn.summary.model.ClientTransactionSummaryEntry;
import org.lbodzon.sample.txn.summary.model.TransactionSummaryContainer;
import org.lbodzon.sample.txn.summary.model.TransactionSummaryTuple;
import org.lbodzon.sample.txn.summary.model.TransactionType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TransactionSummaryService {

        public TransactionSummaryContainer createTransactionSummary(ClientTransactionEntryContainer clientTransactionEntries) {
                TransactionSummaryTuple transactionSummaryTuple = new TransactionSummaryTuple();
                transactionSummaryTuple.setClientEntries(clientTransactionEntries.getClientTransactionEntryTuple().getEntries().stream().map(
                                TransactionSummaryService::aggregateTransactions).collect(Collectors.toList()));

                TransactionSummaryContainer transactionSummaryContainer = new TransactionSummaryContainer();
                transactionSummaryContainer.setTransactionSummaryTuple(transactionSummaryTuple);
                return transactionSummaryContainer;
        }

        private static ClientTransactionSummaryEntry aggregateTransactions(ClientTransactionEntry transactionEntry) {
                ClientTransactionSummaryEntry clientTransactionSummaryEntry = new ClientTransactionSummaryEntry();
                clientTransactionSummaryEntry.setClient(transactionEntry.getClient());

                transactionEntry.getTransactions().forEach(transaction -> {
                        if (transaction.getType() == TransactionType.INCOME) {
                                clientTransactionSummaryEntry.setTotalIncome(
                                      clientTransactionSummaryEntry.getTotalIncome().add(transaction.getValue()));
                        } else {
                                clientTransactionSummaryEntry.setTotalExpenditure(
                                      clientTransactionSummaryEntry.getTotalExpenditure().add(transaction.getValue()));
                        }
                });

                clientTransactionSummaryEntry.setTotalTunover(
                      clientTransactionSummaryEntry.getTotalIncome().subtract(clientTransactionSummaryEntry.getTotalExpenditure()));

                adjustBalance(transactionEntry, clientTransactionSummaryEntry);

                return clientTransactionSummaryEntry;
        }

        private static void adjustBalance(ClientTransactionEntry transactionEntry, ClientTransactionSummaryEntry clientTransactionSummaryEntry) {
                Balance balance = new Balance();
                balance.setDate(new Date());
                balance.setCurrency(transactionEntry.getBalance().getCurrency());
                balance.setTotal(transactionEntry.getBalance().getTotal().add(clientTransactionSummaryEntry.getTotalTunover()));
                clientTransactionSummaryEntry.setBalance(balance);
        }
}
