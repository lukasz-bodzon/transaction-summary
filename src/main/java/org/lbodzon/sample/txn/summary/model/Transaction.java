package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Getter
@Setter
public class Transaction {

        private TransactionType type;

        private String description;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        private Date date;

        @JsonDeserialize(using = BigDecimalDeserializer.class)
        private BigDecimal value;

        private Currency currency;
}
