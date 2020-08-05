package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClientTransactionSummaryEntry {

        @JsonProperty("info")
        Client client;

        Balance balance;

        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal totalTunover;

        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal totalIncome;

        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal totalExpenditure;
}
