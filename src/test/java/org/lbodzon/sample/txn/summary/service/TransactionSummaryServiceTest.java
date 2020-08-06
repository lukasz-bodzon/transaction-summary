package org.lbodzon.sample.txn.summary.service;

import org.junit.jupiter.api.Test;
import org.lbodzon.sample.txn.summary.model.Balance;
import org.lbodzon.sample.txn.summary.model.Client;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntry;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryContainer;
import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryTuple;
import org.lbodzon.sample.txn.summary.model.Transaction;
import org.lbodzon.sample.txn.summary.model.TransactionSummaryContainer;
import org.lbodzon.sample.txn.summary.model.TransactionType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionSummaryServiceTest {

        TransactionSummaryService service = new TransactionSummaryService();

        @Test
        public void testTransactionSummary() throws ParseException {
                ClientTransactionEntryContainer container = new ClientTransactionEntryContainer();
                container.setClientTransactionEntryTuple(new ClientTransactionEntryTuple());
                container.getClientTransactionEntryTuple().setEntries(constructTestClientTransactionEntryList());

                TransactionSummaryContainer summary = service.createTransactionSummary(container);

                assertEquals(container.getClientTransactionEntryTuple().getEntries().get(0).getClient(),
                      summary.getTransactionSummaryTuple().getClientEntries().get(0).getClient());
                assertEquals(new Date(), summary.getTransactionSummaryTuple().getClientEntries().get(0).getBalance().getDate());
                assertEquals(container.getClientTransactionEntryTuple().getEntries().get(0).getBalance().getCurrency(),
                      summary.getTransactionSummaryTuple().getClientEntries().get(0).getBalance().getCurrency());
                assertEquals(new BigDecimal("14999.50"), summary.getTransactionSummaryTuple().getClientEntries().get(0).getBalance().getTotal());
                assertEquals(new BigDecimal("8249.50"), summary.getTransactionSummaryTuple().getClientEntries().get(0).getTotalTunover());
                assertEquals(new BigDecimal("10500"), summary.getTransactionSummaryTuple().getClientEntries().get(0).getTotalIncome());
                assertEquals(new BigDecimal("2250.50"), summary.getTransactionSummaryTuple().getClientEntries().get(0).getTotalExpenditure());
        }

        private Client constructTestClient() {
                Client client = new Client();
                client.setName("Natalia");
                client.setSurname("Nowak");
                return client;
        }

        private Balance constructTestBalance() throws ParseException {
                Balance balance = new Balance();
                balance.setTotal(new BigDecimal(6750));
                balance.setCurrency(Currency.getInstance("PLN"));
                balance.setDate(new SimpleDateFormat("dd-MMM-yyyy").parse("01-May-2020"));
                return balance;
        }

        private Transaction createTestTransaction(TransactionType type, BigDecimal value) {
                Transaction transaction = new Transaction();
                transaction.setType(type);
                transaction.setValue(value);
                return transaction;
        }

        private List<Transaction> constructTestTransactionList() {
                List<Transaction> transactions = new LinkedList<>();
                transactions.add(createTestTransaction(TransactionType.INCOME, new BigDecimal("10500")));
                transactions.add(createTestTransaction(TransactionType.OUTCOME, new BigDecimal("1200")));
                transactions.add(createTestTransaction(TransactionType.OUTCOME, new BigDecimal("1050.50")));
                return transactions;
        }

        private ClientTransactionEntry constructTestClientTransactionEntry() throws ParseException {
                ClientTransactionEntry clientTransactionEntry = new ClientTransactionEntry();
                clientTransactionEntry.setClient(constructTestClient());
                clientTransactionEntry.setBalance(constructTestBalance());
                clientTransactionEntry.setTransactions(constructTestTransactionList());
                return clientTransactionEntry;
        }

        private List<ClientTransactionEntry> constructTestClientTransactionEntryList() throws ParseException {
                List<ClientTransactionEntry> clientTransactionEntryList = new LinkedList<>();
                clientTransactionEntryList.add(constructTestClientTransactionEntry());
                return clientTransactionEntryList;
        }
}
