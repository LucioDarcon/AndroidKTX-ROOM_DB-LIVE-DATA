package com.example.androidktx.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidktx.R
import com.example.androidktx.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.main_menu_add_icon -> {
////                TODO ADD ICON
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

}