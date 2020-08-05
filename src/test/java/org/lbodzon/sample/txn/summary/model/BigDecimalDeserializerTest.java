package org.lbodzon.sample.txn.summary.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalDeserializerTest {

        @Test
        public void testDeserializationComma() throws IOException {
                String json = "{\"type\": \"outcome\",\"description\": \"transfer\",\"date\": \"11.05.2020\",\"value\": \"1050,50\",\"currency\": \"PLN\"}";

                Transaction transaction = new ObjectMapper().readValue(json, Transaction.class);
                assertEquals("1050.50", transaction.getValue().toString());
        }

        @Test
        public void testDeserializationDot() throws IOException {
                String json = "{\"type\": \"outcome\",\"description\": \"transfer\",\"date\": \"11.05.2020\",\"value\": \"1050.5\",\"currency\": \"PLN\"}";

                Transaction transaction = new ObjectMapper().readValue(json, Transaction.class);
                assertEquals("1050.5", transaction.getValue().toString());
        }
}
