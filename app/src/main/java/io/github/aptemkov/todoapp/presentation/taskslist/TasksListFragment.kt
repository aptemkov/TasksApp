package io.github.aptemkov.todoapp.presentation.taskslist

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import io.github.aptemkov.todoapp.R
import io.github.aptemkov.todoapp.databinding.FragmentTasksListBinding
import io.github.aptemkov.todoapp.domain.models.Importance
import io.github.aptemkov.todoapp.domain.models.TodoItem
import io.github.aptemkov.todoapp.presentation.adapter.TasksAdapter
import io.github.aptemkov.todoapp.presentation.adapter.toDate

class TasksListFragment : Fragment() {

    private val viewModel: TasksListViewModel by viewModels()
    private var _binding: FragmentTasksListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newTaskFab.setOnClickListener {
            findNavController().navigate(R.id.newTaskFragment)
        }

        val adapter = TasksAdapter { task ->
            Toast.makeText(requireContext(), task.id, Toast.LENGTH_SHORT).show()
        }
        binding.tasksRecyclerView.adapter = adapter

        val list = listOf(
            TodoItem("1", "Создать список покупок", Importance.LOW, 0L, false),
            TodoItem("2", "Составить план дня", Importance.HIGH, 0L, false),
            TodoItem("3", "Написать письмо другу", Importance.LOW, 0L, false),
            TodoItem("4", "Создать заметку о встрече", Importance.LOW, 0L, false),
            TodoItem("5", "Создать напоминание о необходимости оплатить счёт", Importance.LOW, 1L, false),
            TodoItem("6", "Создать список дел на неделю", Importance.LOW, 0L, true),
            TodoItem("7", "Создать список дел на месяц", Importance.MEDIUM, 0L, false),
            TodoItem("8", "Создать список дел на год", Importance.LOW, 0L, true),
            TodoItem("9", "Создать список целей", Importance.LOW,  1790554756076L, false),
            TodoItem("10", "Создать список желаний", Importance.LOW, 0L, false),
            TodoItem("11", "Создать список книг для чтения", Importance.LOW, 0L, false),
            TodoItem("12", "Создать список фильмов для просмотра", Importance.LOW, 0L, false),
            TodoItem("13", "Создать список мест для посещения", Importance.HIGH, 0L, false),
            TodoItem("14", "Создать список мероприятий для участия", Importance.HIGH, 1790564656076L, false),
            TodoItem("15", "Создать список подарков для друзей и родственников", Importance.MEDIUM, 0L, true),
            TodoItem("16", "Создать список вещей для упаковки перед переездом", Importance.LOW, 1780554756076L, false),
            TodoItem("16", "Создать список вещей для уборки дома", Importance.LOW, 0L, false),
            TodoItem("16", "Создать список вещей для организации вечеринки", Importance.LOW, 1790653756076L, true),
            TodoItem("16", "Создать список вещей для подготовки к путешествию", Importance.LOW, 0L, true),
            TodoItem("16", "Создать список вещей для подготовки к экзамену", Importance.LOW, 1791664756076L, false),
        )

        adapter.submitList(list)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}