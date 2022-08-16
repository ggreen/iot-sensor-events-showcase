package com.github.ggreen.rabbit.gemfire.filter.oql.service

import com.github.ggreen.rabbit.gemfire.filter.oql.publisher.StreamPublisher
import com.vmware.data.services.gemfire.io.QuerierService
import nyla.solutions.core.patterns.decorator.TextStylist
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

internal class OqlFilterTest {

    private lateinit var textStylist: TextStylist
    private lateinit var query: QuerierService
    private lateinit var publisher : StreamPublisher
    private val id = "123"
    private val expected = """
            {"id" : $id} 
        """.trimIndent().toByteArray()
    private val oql = "select * from /SENSOR WHERE \${id}"

    private lateinit var subject : OqlFilter

    @BeforeEach
    internal fun setUp() {
        textStylist = mock()
        query = mock()
        publisher = mock()
        subject = OqlFilter(query, oql,publisher,textStylist)
    }

    @Test
    internal fun given_query_that_matches_WHEN_filterApplies_THEN_recordIsReturned() {

        val expectedString = "{}"
        whenever(query.query<Any>(any())).thenReturn(listOf(expected))
        whenever(textStylist.format(any(), any())).thenReturn(expectedString)

        var actual = subject.apply(expected)

        assertEquals(expected, actual);

        verify(publisher).send(any())
    }

    @Test
    internal fun given_query_that_DoeNotMatch_WHEN_filterApplies_THEN_returnNull() {
        val id = "123"

        val expected = """
            {"id" : $id} 
        """.trimIndent().toByteArray()

        whenever(query.query<Any>(any())).thenReturn(emptyList())

        val oql = "select * from /SENSOR WHERE \${id}"
        var subject = OqlFilter(query, oql, publisher,textStylist)

        assertNull(subject.apply(expected))

        verify(publisher, never()).send(any())
    }
}