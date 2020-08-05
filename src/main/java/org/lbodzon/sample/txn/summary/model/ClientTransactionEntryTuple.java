package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientTransactionEntryTuple {

        @JsonProperty("client")
        List<ClientTransactionEntry> entries;
}
