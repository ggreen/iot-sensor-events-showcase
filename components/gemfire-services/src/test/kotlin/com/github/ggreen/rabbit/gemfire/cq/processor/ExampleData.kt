package com.github.ggreen.rabbit.gemfire.cq.processor

data class ExampleData(var id: String = "",
                       var name: String = "",
                       var nestedData: NestedExampleData? = null)
