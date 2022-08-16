package  com.github.ggreen.rabbit.gemfire.filter.oql.publisher

interface StreamPublisher {
    fun send(json: ByteArray)
}