package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientTransactionEntryContainer {

        @JsonProperty("clients")
        ClientTransactionEntryTuple clientTransactionEntryTuple;
}
