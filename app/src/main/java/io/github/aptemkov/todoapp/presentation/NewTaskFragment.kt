package io.github.aptemkov.todoapp.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import io.github.aptemkov.todoapp.R
import io.github.aptemkov.todoapp.databinding.FragmentNewTaskBinding
import io.github.aptemkov.todoapp.databinding.FragmentTasksListBinding

class NewTaskFragment : Fragment() {
    private val viewModel: NewTaskViewModel by viewModels()

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.setOnClickListener {
            binding.title.text = "Test"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}