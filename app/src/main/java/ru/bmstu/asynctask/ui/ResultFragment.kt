package ru.bmstu.asynctask.ui

import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.result_gragment_fragment.view.*

import ru.bmstu.asynctask.R

class ResultFragment : Fragment() {

    companion object {
        fun newInstance() = ResultFragment()
    }

    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.result_gragment_fragment, container, false)

        viewModel = ViewModelProviders.of(activity as FragmentActivity).get(ViewModel::class.java)

        root.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MainFragment.newInstance())
                ?.commitNow()
        }

        val statusObserver = Observer<String> { newStatus ->
            root.txtStatusOutput.text = newStatus
        }
        viewModel.lblStatus.observe(this, statusObserver)


        return root
    }

}
