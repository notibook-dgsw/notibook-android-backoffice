package com.example.backproject.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.backproject.R
import com.example.backproject.view.MainActivity


class TutorialFragment() : Fragment() {

    fun newInstance(page : Int): TutorialFragment {
        val tutorialFragment = TutorialFragment()
        val bundle = Bundle()
        bundle.putInt("tutorial_page",page)
        tutorialFragment.arguments = bundle
        return tutorialFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val page = this.arguments?.getInt("tutorial_page")
        val view = inflater.inflate(page!!, container, false)

        if (page == R.layout.tutorial6) {
            val bt = view.findViewById<Button>(R.id.end_tutorial)
            bt.setOnClickListener {
                requireActivity().startActivity(
                    Intent(
                        activity,
                        MainActivity::class.java
                    )
                )
                requireActivity().finish()
            }
        }

        return view
    }
}