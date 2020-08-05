package org.lbodzon.sample.txn.summary.controller;

import org.lbodzon.sample.txn.summary.model.ClientTransactionEntryContainer;
import org.lbodzon.sample.txn.summary.model.TransactionSummaryContainer;
import org.lbodzon.sample.txn.summary.service.TransactionSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionSummaryController {

        private TransactionSummaryService transactionSummaryService;

        @Autowired
        public TransactionSummaryController(TransactionSummaryService service) {
                transactionSummaryService = service;
        }

        @PostMapping(value = "/summary", produces = "application/json")
        public TransactionSummaryContainer retrieveTransactionSummary(@RequestBody ClientTransactionEntryContainer clientTransactionEntries) {
                return transactionSummaryService.createTransactionSummary(clientTransactionEntries);
        }
}
