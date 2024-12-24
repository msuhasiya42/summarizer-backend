package com.mayur.summarizer.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.mayur.summarizer.entity.RequestHistory

@Repository
trait RequestHistoryRepository extends JpaRepository[RequestHistory, Long]
