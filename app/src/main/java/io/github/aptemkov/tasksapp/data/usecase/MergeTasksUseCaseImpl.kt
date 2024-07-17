package io.github.aptemkov.tasksapp.data.usecase


import android.util.Log
import io.github.aptemkov.tasksapp.data.remote.toTask
import io.github.aptemkov.tasksapp.domain.models.Task
import io.github.aptemkov.tasksapp.domain.repository.LocalTasksRepository
import io.github.aptemkov.tasksapp.domain.repository.RemoteTasksRepository
import io.github.aptemkov.tasksapp.domain.usecase.MergeTasksUseCase
import javax.inject.Inject

class MergeTasksUseCaseImpl @Inject constructor(
    private val localRepository: LocalTasksRepository,
    private val remoteRepository: RemoteTasksRepository
): MergeTasksUseCase {

    override suspend fun execute(): Result<Boolean> {
        Log.i("MergeUseCase", "Merge execute: ")

        val remoteResult = remoteRepository.getAllTasksFromRemote()
        return if (remoteResult.isSuccess) {

            val remoteTasks = remoteResult
                .getOrDefault(emptyList())
                .map { it.toTask() }
                .associateBy { it.id }
                .toMutableMap()

            val localTasks = localRepository
                .getAllLocalTasks()
                .associateBy { it.id }

            val mergedTasks = mergeStrategySecond(localTasks, remoteTasks)

            Log.i("MergeUseCase", "merged tasks: $mergedTasks")

            localRepository.clearDataBase()
            localRepository.addTasks(mergedTasks)
            remoteRepository.updateTasksRemote(mergedTasks)
            Result.success(true)
        } else {
            Log.i("MergeUseCase", "$remoteResult ")
            Result.failure(Exception("Failed to fetch remote tasks"))
        }
    }
}

/**
 * Создаём @mergedList, в который будем сохранять все решенные конфликты
 * Проходим по всем локальным элементам и проверяем следующие варианты:
 * - Задача удалена локально -> удаляем из @remoteTasks и пропускаем итерацию цикла
 * - Локальная задача содержится в @remoteTasks -> оставляем ту, которая изменена позже и удаляем задачу из @remoteTasks
 * - Локальная задача не содержится в @remoteTasks -> добавляем её
 * - Проходим по оставшимся задачам внутри @remoteTasks, добавляем их
 */
private fun mergeStrategy(
    localTasks: Map<String, Task>,
    remoteTasks: MutableMap<String, Task>
): List<Task> {

    val mergedList = mutableSetOf<Task>()

    for ((localId, localTask) in localTasks) {

        // Удалена локально - удаляем её везде
        if(localTask.isRemoved) {
            remoteTasks.remove(localId)
            continue
        }

        if (remoteTasks.contains(localId)) {
            // Наличие задачи в remoteTasks проверено выше, break добавлен, чтобы не было null
            val remoteTask = remoteTasks[localId] ?: continue

            val localEdited = localTask.editDate
            val remoteEdited = remoteTask.editDate

            val resolvedTask = if (localEdited >= remoteEdited) {
                localTask
            } else {
                remoteTask
            }
            // Удаляем из remoteTasks, чтобы после обхода всех локальных остались только те, которые есть только в remoteTasks
            remoteTasks.remove(localId)

            mergedList.add(resolvedTask)
        } else {
            // Если в remoteTasks отсутствует локальная задача, то она добавляется в смерженный список
            mergedList.add(localTask)
        }
    }

    // Проходим по оставшимся remote задачам, добавляем их в смерженный список
    for (remote in remoteTasks) {
        mergedList.add(remote.value)
    }

    return mergedList.toList()
}


/**
 * Все изменения будут внутри @remoteTasks
 * Проходим по всем локальным элементам и проверяем следующие варианты:
 * - Локальная задача содержится в @remoteTasks ->
 *     -- Задача удалена локально -> удаляем из @remoteTasks
 *     -- Иначе оставляем ту, которая изменена позже и удаляем задачу из @remoteTasks
 * - Локальная задача не содержится в @remoteTasks -> добавляем её
 */
private fun mergeStrategySecond(
    localTasks: Map<String, Task>,
    remoteTasks: MutableMap<String, Task>
): List<Task> {

    for((localId, localTask) in localTasks) {

        if(remoteTasks.contains(localId)) {
            //if(localTask.isRemoved) {
            if(localTask.isRemoved) {
                remoteTasks.remove(localId)
            } else {
                val remoteTask = remoteTasks.get(localId) ?: continue

                val localEdited = localTask.editDate
                val remoteEdited = remoteTask.editDate

                val resolvedTask = if (localEdited >= remoteEdited) {
                    localTask
                } else {
                    remoteTask
                }
                remoteTasks.replace(localId, resolvedTask)
            }
        } else {
            remoteTasks.put(localId, localTask)
        }
    }

    return remoteTasks.values.toList()
}

