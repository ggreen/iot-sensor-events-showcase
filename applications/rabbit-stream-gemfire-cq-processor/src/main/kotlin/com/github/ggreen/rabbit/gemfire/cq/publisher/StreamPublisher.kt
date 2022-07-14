package com.github.ggreen.rabbit.gemfire.cq.publisher

interface StreamPublisher {
    fun send(json: String)
}