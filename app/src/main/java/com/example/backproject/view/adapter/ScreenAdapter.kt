package com.example.backproject.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.backproject.view.fragment.TutorialFragment

class ScreenAdapter constructor(fragmentActivity : FragmentActivity, private val layouts : List<Int>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = layouts.size

    override fun createFragment(position: Int): Fragment {
        return TutorialFragment().newInstance(layouts[position])
    }
}