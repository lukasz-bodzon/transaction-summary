package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Balance {

        @JsonDeserialize(using = BigDecimalDeserializer.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        private BigDecimal total;

        private Currency currency;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        private Date date;

        public Balance(Balance other) {
                this.total = other.total;
                this.currency = other.currency;
                this.date = other.date;
        }
}
