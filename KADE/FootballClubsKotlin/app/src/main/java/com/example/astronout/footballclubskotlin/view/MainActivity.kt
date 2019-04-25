package com.example.astronout.footballclubskotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.astronout.footballclubskotlin.R
import com.example.astronout.footballclubskotlin.adapter.ClubsAdapter
import com.example.astronout.footballclubskotlin.model.Item
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        club_list.layoutManager = LinearLayoutManager(this)
        club_list.adapter = ClubsAdapter(this, items) {
            itemClicked(it)
        }
//        club_list.adapter = ClubsAdapter(this, items) {
//            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
//            toast.show()
//
//        }
    }

    private fun itemClicked(items : Item) {
        startActivity<DetailActivity>(DetailActivity.TITLE to items.name, DetailActivity.IMAGE to items.image, DetailActivity.DESCRIPTION to items.description)
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.club_name)
        val image = resources.obtainTypedArray(R.array.club_image)
        val description = resources.getStringArray(R.array.club_description)
        items.clear()
        for (i in name.indices) {
            items.add(Item(name[i],
                    image.getResourceId(i, 0), description[i]))
        }

        image.recycle()
    }
}

