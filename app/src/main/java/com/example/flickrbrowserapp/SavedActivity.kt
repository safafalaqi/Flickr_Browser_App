package com.example.flickrbrowserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ToggleButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrbrowserapp.databinding.ActivityMainBinding
import com.example.flickrbrowserapp.databinding.ItemRowBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedActivity : AppCompatActivity() {
    private lateinit var myRv: RecyclerView
    private lateinit var rvAdapter: RVAdapter
   lateinit var flickr: List<Photo>
    private val photoDao by lazy{ PhotoDatabase.getInstance(this).photoDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liked)
        //get from shared preference
        //flickr= PreferenceHelper.getItemList(PreferenceHelper.PHOTOS_LIST)
        //get liked from database

        CoroutineScope(Dispatchers.IO).launch {
            flickr = photoDao.getPhotos()

            withContext(Dispatchers.Main) {
                //if not empty set RV
                if (flickr != null) {
                    myRv = findViewById(R.id.rvPhotosPager)
                    rvAdapter = RVAdapter(flickr.toArrayList(), this@SavedActivity)
                    myRv.adapter = rvAdapter
                    myRv.layoutManager = GridLayoutManager(applicationContext, 3)
                }
            }

        }
        setBottomNivigation()
    }

    private fun setBottomNivigation() {
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val menu: Menu = bottomNavigationView.menu
        val menuItem: MenuItem = menu.getItem(2)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainActivity-> startActivity(Intent(this,MainActivity::class.java))
                R.id.listView-> { val intent =Intent(this, ListActivity::class.java)
                    intent.putExtra("key","cats") //send the keyword
                    startActivity(intent)
                }
                R.id.likedActivity-> startActivity(Intent(this,SavedActivity::class.java))
            }
            true
        }

    }

    fun <T> List<T>.toArrayList(): ArrayList<T>{
        return ArrayList(this)
    }
}