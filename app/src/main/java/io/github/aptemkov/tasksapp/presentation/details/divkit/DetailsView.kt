package io.github.aptemkov.tasksapp.presentation.details.divkit

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.View
import com.yandex.div.DivDataTag
import com.yandex.div.core.Div2Context
import com.yandex.div.core.DivConfiguration
import com.yandex.div.core.view2.Div2View
import com.yandex.div.picasso.PicassoDivImageLoader
import io.github.aptemkov.tasksapp.R
import org.json.JSONObject

class DetailsView(
    context: Context,
    onClose: () -> Unit,
) {
    private val view: View

    init {
        val parsedJson = details
        val contextThemeWrapper = ContextThemeWrapper(context, R.style.Theme_TasksApp)
        val picassoImageLoader = PicassoDivImageLoader(context)
        val configuration = DivConfiguration
            .Builder(picassoImageLoader)
            .actionHandler(DetailsDivActionHandler(onClose))
            .build()
        val divData = JSONObject(parsedJson).asDiv2DataWithTemplates()
        view = Div2View(Div2Context(contextThemeWrapper, configuration, 0, lifecycleOwner = null))

        view.setData(divData, DivDataTag("details"))
    }

    fun getView(): View {
        return view
    }
}
