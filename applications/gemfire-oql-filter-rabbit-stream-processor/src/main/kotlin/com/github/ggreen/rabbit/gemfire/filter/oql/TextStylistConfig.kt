package com.github.ggreen.rabbit.gemfire.filter.oql

import nyla.solutions.core.patterns.decorator.BasicTextStyles
import nyla.solutions.core.patterns.decorator.TextStyles
import nyla.solutions.core.patterns.decorator.TextStylist
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Gregory Green
 */
@Configuration
class TextStylistConfig {

    @Value("\${template.prefix:?{")
    private lateinit var templatePrefix: String

    @Value("\${template.suffix:}")
    private lateinit var templateSuffix: String

    @Bean
    fun textStylist() : TextStylist
    {
        var textStylist  = BasicTextStyles()
        textStylist.templatePrefix = templatePrefix
        textStylist.templateSuffix = templateSuffix

        return textStylist
    }
}