package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Setter
public class Transaction {

        @Getter
        private TransactionType type;

        @Getter
        private String description;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        private Date date;

        @Getter
        @JsonDeserialize(using = BigDecimalDeserializer.class)
        private BigDecimal value;

        @Getter
        private Currency currency;

        public Date getDate() {
                return (Date)date.clone();
        }
}
