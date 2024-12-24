package com.mayur.summarizer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.mayur.summarizer.service.HistoryService;
import com.mayur.summarizer.entity.RequestHistory;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class RequestHistoryController {

    private final HistoryService historyService;
    private static final Logger logger = LoggerFactory.getLogger(RequestHistoryController.class);

    public RequestHistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    // Combine the route and method into a single function
    @RequestMapping(method = RequestMethod.GET)
    public List<RequestHistory> getAllHistory() {
        List<RequestHistory> history = historyService.getAllHistory();

        // Log the retrieved history
        logger.info("Retrieved history: {}", history);

        return history;
    }
}
