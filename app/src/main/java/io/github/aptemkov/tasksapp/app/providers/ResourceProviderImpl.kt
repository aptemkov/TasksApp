package io.github.aptemkov.tasksapp.app.providers

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
* Данный класс необходим для предоставления ресурсов
*
*/

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {
    override fun getString(resId: Int, vararg params: String): String {
        return context.getString(resId, params)
    }
}