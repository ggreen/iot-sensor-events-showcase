package com.github.ggreen.rabbit.gemfire.cq.processor

import org.apache.geode.cache.Region
import org.apache.geode.pdx.PdxInstance
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.function.Consumer
import java.util.function.Function

/**
 * Save records for GemFire
 * @author Gregory Green
 */
@Component
class GemFireSaveConsumer(
    private val region: Region<String, PdxInstance>,
    @Qualifier("jsonBytesToPdxConverter")
    private val binaryToPdxConverter: Function<ByteArray, PdxInstance>,
    @Value("\${gemfire.region.idFieldName}")
    private val idFieldName: String
) : Consumer<ByteArray> {

    override fun accept(payload: ByteArray) {
        var pdx = binaryToPdxConverter.apply(payload)

        var id = pdx.getField(idFieldName) as String?

        region[id] = pdx

    }

}