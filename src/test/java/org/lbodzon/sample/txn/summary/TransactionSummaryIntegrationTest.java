package org.lbodzon.sample.txn.summary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lbodzon.sample.txn.summary.controller.TransactionSummaryController;
import org.lbodzon.sample.txn.summary.service.TransactionSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { TransactionSummaryService.class, TransactionSummaryController.class })
@WebMvcTest
public class TransactionSummaryIntegrationTest {

        static Logger logger = LoggerFactory.getLogger(TransactionSummaryIntegrationTest.class);

        @Autowired
        private WebApplicationContext webApplicationContext;

        @Autowired
        private MockMvc mockMvc;


        @Test
        public void testResponseForValidPayload() throws Exception {
                MvcResult result = mockMvc.perform(post("/transactions/summary")
                                .content(readFile("./src//test/resources/request.json"))
                                .contentType("application/json;charset=UTF-8"))
                      .andExpect(status().isOk()).andExpect(content()
                      .contentType("application/json"))
                      .andReturn();

                assertEquals(readFile("./src//test/resources/response.json"), result.getResponse().getContentAsString());
        }

        @Test
        public void testResponseForInvalidPayload() throws Exception {
                mockMvc.perform(post("/transactions/summary")
                      .content(readFile("./src//test/resources/request_invalid.json"))
                      .contentType("application/json;charset=UTF-8"))
                      .andExpect(status().is4xxClientError());
        }

        private static String readFile(String path) {
                StringBuilder builder = new StringBuilder();
                try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
                        stream.forEach(builder::append);
                } catch (IOException ex) {
                        logger.error(ex.getMessage());
                }
                return builder.toString().replaceAll("\\s+", "");
        }
}
