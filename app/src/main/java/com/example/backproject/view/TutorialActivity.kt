package com.example.backproject.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.backproject.R
import com.example.backproject.view.adapter.ScreenAdapter
import com.example.backproject.view.adapter.ZoomOutPageTransformer


class TutorialActivity() : FragmentActivity() {

    private lateinit var tutorialViewPager: ViewPager2
    private lateinit var tutorialPagerAdapter: FragmentStateAdapter
    private lateinit var tutorialLayouts: ArrayList<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        tutorialViewPager = findViewById(R.id.view_pager)
        tutorialViewPager.setPageTransformer(ZoomOutPageTransformer())
        tutorialLayouts = ArrayList()
        tutorialLayouts.add(R.layout.tutorial1)
        tutorialLayouts.add(R.layout.tutorial2)
        tutorialLayouts.add(R.layout.tutorial3)
        tutorialLayouts.add(R.layout.tutorial4)
        tutorialLayouts.add(R.layout.tutorial5)
        tutorialLayouts.add(R.layout.tutorial6)

        tutorialPagerAdapter = ScreenAdapter(this, tutorialLayouts)
        tutorialViewPager.adapter = tutorialPagerAdapter
    }
}