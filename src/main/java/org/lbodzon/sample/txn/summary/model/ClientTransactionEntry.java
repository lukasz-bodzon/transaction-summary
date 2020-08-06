package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
public class ClientTransactionEntry {

        @JsonProperty("info")
        private Client client;

        private Balance balance;

        private List<Transaction> transactions;

        public Client getClient() {
              return new Client(client);
        }

        public Balance getBalance() {
                return new Balance(balance);
        }

        public List<Transaction> getTransactions() {
                return Collections.unmodifiableList(transactions);
        }
}
