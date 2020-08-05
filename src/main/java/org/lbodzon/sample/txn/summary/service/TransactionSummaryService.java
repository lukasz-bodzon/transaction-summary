package org.lbodzon.sample.txn.summary.service;

import org.lbodzon.sample.txn.summary.model.Balance;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntry;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryContainer;
import org.lbodzon.sample.txn.summary.model.ClientTransactionSummaryEntry;
import org.lbodzon.sample.txn.summary.model.Transaction;
import org.lbodzon.sample.txn.summary.model.TransactionSummaryContainer;
import org.lbodzon.sample.txn.summary.model.TransactionSummaryTuple;
import org.lbodzon.sample.txn.summary.model.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TransactionSummaryService {

        private static ClientTransactionSummaryEntry apply(ClientTransactionEntry transactionEntry) {
                ClientTransactionSummaryEntry clientTransactionSummaryEntry = new ClientTransactionSummaryEntry();
                clientTransactionSummaryEntry.setClient(transactionEntry.getClient());

                Date now = new Date();
                Balance balance = new Balance();
                balance.setCurrency(transactionEntry.getBalance().getCurrency());
                balance.setTotal(transactionEntry.getBalance().getTotal());
                balance.setDate(now);

                BigDecimal totalIncome = new BigDecimal(0);
                BigDecimal totalExpenditure = new BigDecimal(0);

                for (Transaction transaction : transactionEntry.getTransactions()) {
                        if (transaction.getType() == TransactionType.INCOME) {
                                totalIncome = totalIncome.add(transaction.getValue());
                                balance.setTotal(balance.getTotal().add(transaction.getValue()));
                        } else {
                                totalExpenditure = totalExpenditure.add(transaction.getValue());
                                balance.setTotal(balance.getTotal().subtract(transaction.getValue()));
                        }
                }

                clientTransactionSummaryEntry.setBalance(balance);
                clientTransactionSummaryEntry.setTotalIncome(totalIncome);
                clientTransactionSummaryEntry.setTotalExpenditure(totalExpenditure);
                clientTransactionSummaryEntry.setTotalTunover(totalIncome.subtract(totalExpenditure));
                return clientTransactionSummaryEntry;
        }

        public TransactionSummaryContainer createTransactionSummary(ClientTransactionEntryContainer clientTransactionEntries) {
                TransactionSummaryTuple transactionSummaryTuple = new TransactionSummaryTuple();
                transactionSummaryTuple.setClientEntries(clientTransactionEntries.getClientTransactionEntryTuple().getEntries().stream().map(
                                TransactionSummaryService::apply).collect(Collectors.toList()));

                TransactionSummaryContainer transactionSummaryContainer = new TransactionSummaryContainer();
                transactionSummaryContainer.setTransactionSummaryTuple(transactionSummaryTuple);
                return transactionSummaryContainer;
        }
}
