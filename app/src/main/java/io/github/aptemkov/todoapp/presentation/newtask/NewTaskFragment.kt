package io.github.aptemkov.todoapp.presentation.newtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.AppBarLayout
import io.github.aptemkov.todoapp.databinding.FragmentNewTaskBinding


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
        binding.appBar.addOnOffsetChangedListener(object: AppBarLayout.OnOffsetChangedListener {

            var isShow: Boolean = false
            var scrollRange: Int = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout?.getTotalScrollRange()!!
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    binding.testIcon.visibility = View.VISIBLE
                } else if (isShow) {
                    isShow = false;
                    binding.testIcon.visibility = View.GONE
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}