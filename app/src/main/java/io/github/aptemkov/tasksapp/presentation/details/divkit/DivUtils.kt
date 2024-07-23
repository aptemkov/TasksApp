package io.github.aptemkov.tasksapp.presentation.details.divkit

import android.content.Context
import com.yandex.div.core.DivConfiguration
import com.yandex.div.data.DivParsingEnvironment
import com.yandex.div.json.ParsingErrorLogger
import com.yandex.div.picasso.PicassoDivImageLoader
import com.yandex.div2.DivData
import org.json.JSONObject

fun JSONObject.asDiv2DataWithTemplates(): DivData {
    val templates = getJSONObject("templates")
    val card = getJSONObject("card")
    val environment = DivParsingEnvironment(ParsingErrorLogger.LOG)
    environment.parseTemplates(templates)
    return DivData(environment, card)
}

fun createDivConfiguration(context: Context): DivConfiguration {
    return DivConfiguration.Builder(PicassoDivImageLoader(context))
        .build()
}
