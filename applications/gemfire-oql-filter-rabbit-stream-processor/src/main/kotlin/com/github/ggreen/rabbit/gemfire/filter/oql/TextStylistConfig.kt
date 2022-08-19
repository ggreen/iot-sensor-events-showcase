package com.github.ggreen.rabbit.gemfire.filter.oql

import nyla.solutions.core.patterns.decorator.BasicTextStyles
import nyla.solutions.core.patterns.decorator.TextStyles
import nyla.solutions.core.patterns.decorator.TextStylist
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */
@Configuration
class TextStylistConfig {

    private val logger: Logger = LoggerFactory.getLogger("TextStylistConfig")
    private val templatePrefix: String = "?{"
    private val templateSuffix: String = "}"

    @Bean
    fun textStylist() : TextStylist
    {
        var textStylist  = BasicTextStyles()
        textStylist.templatePrefix = templatePrefix
        textStylist.templateSuffix = templateSuffix

        logger.info("PREFIX=${textStylist.templatePrefix} SUFFIX=${textStylist.templateSuffix}")

        return textStylist
    }
}