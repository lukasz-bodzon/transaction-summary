package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
public class ClientTransactionSummaryEntry {

        @JsonProperty("info")
        private Client client;

        private Balance balance;

        @Getter
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private BigDecimal totalTunover = new BigDecimal(0);

        @Getter
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private BigDecimal totalIncome = new BigDecimal(0);

        @Getter
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private BigDecimal totalExpenditure = new BigDecimal(0);

        public Client getClient() {
                return new Client(client);
        }

        public Balance getBalance() {
                return new Balance(balance);
        }
}
