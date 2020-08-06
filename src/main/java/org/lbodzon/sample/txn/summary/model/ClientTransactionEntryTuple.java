package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Setter
public class ClientTransactionEntryTuple {

        @JsonProperty("client")
        private List<ClientTransactionEntry> entries;

        public List<ClientTransactionEntry> getEntries() {
                return Collections.unmodifiableList(entries);
        }
}
