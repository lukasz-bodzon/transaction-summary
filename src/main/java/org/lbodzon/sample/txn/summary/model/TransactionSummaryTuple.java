package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
public class TransactionSummaryTuple {

        @JsonProperty("client")
        private List<ClientTransactionSummaryEntry> clientEntries;

        public List<ClientTransactionSummaryEntry> getClientEntries() {
                return Collections.unmodifiableList(clientEntries);
        }
}
