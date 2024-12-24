package com.mayur.summarizer.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.{HttpEntity, HttpHeaders, MediaType}
import org.springframework.beans.factory.annotation.Autowired
import com.mayur.summarizer.repository.RequestHistoryRepository
import com.mayur.summarizer.entity.RequestHistory
import java.time.LocalDateTime
import scala.jdk.CollectionConverters._

@Service
class SummaryService @Autowired()(val requestHistoryRepository: RequestHistoryRepository) {

  @Value("${python.api.url}")
  private val pythonApiUrl: String = "http://127.0.0.1:8000/summarize" // FastAPI URL

  private val restTemplate = new RestTemplate()

  def summarizeUrl(url: String): String = {
    // Create headers with content type JSON
    val headers = new HttpHeaders()
    headers.setContentType(MediaType.APPLICATION_JSON)

    // Create the request body as a Map
    val requestBody = Map("url" -> url)

    // Log the request body for debugging
    println(s"Request body: $requestBody")

    // Create HttpEntity with the body and headers
    val entity = new HttpEntity(requestBody.asJava, headers)

    // Make the POST request
    val response = restTemplate.postForEntity(pythonApiUrl, entity, classOf[String])

    val summary = response.getBody

    // Save the request history with timestamp in the database
    val requestHistory = new RequestHistory(url, summary)
    requestHistory.timestamp = LocalDateTime.now() // Set the timestamp
    requestHistoryRepository.save(requestHistory)  // Save to the database

    // Return the summary from the response
    summary
  }
}
