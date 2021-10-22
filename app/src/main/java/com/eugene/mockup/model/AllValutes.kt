package com.eugene.mockup.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs", strict = false)
data class AllValutes(
    @field:ElementList(name = "Valute", inline = true, required = false)
    @param:ElementList(name = "Valute", inline = true, required = false)
    var listOfValutesf: MutableList<Valute>? = null
)

