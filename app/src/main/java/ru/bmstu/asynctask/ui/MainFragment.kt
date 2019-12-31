package ru.bmstu.asynctask.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.main_fragment.view.*

import ru.bmstu.asynctask.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ViewModel::class.java)

        val root = inflater.inflate(R.layout.main_fragment, container, false)

        root.edtNumber.setText(viewModel.rawInput)

        root.btnCalculate.setOnClickListener {
            onBtnCalculate(root.edtNumber.text.toString())
        }

        return root
    }

    private fun onBtnCalculate(rawText: String) {
        if (rawText.isNotEmpty()) {
            viewModel.rawInput = rawText
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, ResultFragment.newInstance())
                ?.commitNow()
            viewModel.launchCalculation()
        }
    }
}
