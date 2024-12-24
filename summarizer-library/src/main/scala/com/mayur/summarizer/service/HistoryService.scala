package com.mayur.summarizer.service

import com.mayur.summarizer.entity.RequestHistory
import com.mayur.summarizer.repository.RequestHistoryRepository
import org.springframework.stereotype.Service
import scala.jdk.CollectionConverters._

@Service
class HistoryService(val repository: RequestHistoryRepository) {

  // Retrieve all history entries
  def getAllHistory: java.util.List[RequestHistory] = {
    val history = repository.findAll()
    if (history.isEmpty) {
      throw new RuntimeException("No history entries found.")
    }
    history.asScala.toList.asJava
  }
}
