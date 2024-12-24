package com.mayur.summarizer.entity
import com.fasterxml.jackson.annotation.{JsonFormat, JsonProperty}
import jakarta.persistence._

import java.time.LocalDateTime

@Entity
@Table(name = "request_history")
class RequestHistory() {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  @JsonProperty("id")
  var id: Int = _

  @Column(name = "url", nullable = false, length = 255)
  @JsonProperty("url")
  var url: String = _

  @Column(name = "summary", nullable = false, columnDefinition = "TEXT")
  @JsonProperty("summary")
  var summary: String = _

  @Column(name = "timestamp", nullable = false)
  @JsonProperty("timestamp")
  var timestamp: LocalDateTime = LocalDateTime.now()

  // Primary constructor for all fields
  def this(id: Int, url: String, summary: String, timestamp: LocalDateTime) = {
    this()
    this.id = id
    this.url = url
    this.summary = summary
    this.timestamp = timestamp
  }

  // Auxiliary constructor for `url` and `summary`
  def this(url: String, summary: String) = {
    this()
    this.url = url
    this.summary = summary
  }

  override def toString: String = s"RequestHistory(id=$id, url=$url, summary=$summary, timestamp=$timestamp)"
}
