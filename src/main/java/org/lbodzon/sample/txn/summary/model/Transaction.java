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

        TransactionType type;

        String description;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        Date date;

        @JsonDeserialize(using = BigDecimalDeserializer.class)
        BigDecimal value;

        Currency currency;
}
