package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionType {

        @JsonProperty("income")
        INCOME,

        @JsonProperty("outcome")
        OUTCOME
}
