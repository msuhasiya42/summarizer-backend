package com.mayur.summarizer.controller;

import org.springframework.web.bind.annotation.*;
import com.mayur.summarizer.service.SummaryService;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SummaryController {

  private final SummaryService summaryService;

  public SummaryController(SummaryService summaryService) {
    this.summaryService = summaryService;
  }

  @PostMapping("/summarize")
  public Map<String, String> summarize(@RequestBody Map<String, String> request) {
    // Extracting URL from the request body
    String url = request.getOrDefault("url", "");
    if (url.isEmpty()) {
      throw new IllegalArgumentException("Missing 'url' field");
    }

    // Call the service to summarize the URL
    String summary = summaryService.summarizeUrl(url);

    // Return the response as a map containing the URL and summary
    return Map.of("url", url, "summary", summary);
  }
}
