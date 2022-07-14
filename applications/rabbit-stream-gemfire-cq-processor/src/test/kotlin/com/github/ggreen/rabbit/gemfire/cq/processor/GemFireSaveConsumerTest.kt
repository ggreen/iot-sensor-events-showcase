package com.github.ggreen.rabbit.gemfire.cq.processor

import com.fasterxml.jackson.databind.ObjectMapper
import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator
import org.apache.geode.cache.Region
import org.apache.geode.pdx.PdxInstance
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.function.Function

internal class GemFireSaveConsumerTest{
    private val idFieldName: String = "id"
    private lateinit var region: Region<String, PdxInstance>
    private val objectMapper = ObjectMapper()
    private lateinit var pdx : PdxInstance

    private lateinit var binaryToPdxConverter : Function<ByteArray,PdxInstance>

    @BeforeEach
    internal fun setUp() {
        pdx = mock()
        region = mock()
        binaryToPdxConverter = mock()
    }

    @Test
    internal fun given_jsonBinary_whenSave_thenSavedToRegion() {

        val expectId = "001"
        whenever(binaryToPdxConverter.apply(any())).thenReturn(pdx)
        whenever(pdx.getField(any())).thenReturn(expectId)

        var subject = GemFireSaveConsumer(region,binaryToPdxConverter,idFieldName)

        val payload = objectMapper.writeValueAsBytes(JavaBeanGeneratorCreator.of(ExampleData::class.java).create())
        subject.accept(payload)

        verify(region).put(anyString(),any())
        verify(binaryToPdxConverter).apply(any())

    }
}