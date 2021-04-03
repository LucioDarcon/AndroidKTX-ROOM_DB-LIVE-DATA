package com.example.androidktx.ui.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.androidktx.R
import com.example.androidktx.ui.dialogs.UserDialog
import com.example.androidktx.ui.fragments.MainFragment
import com.example.core.provider.Providers.provideDataBase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_main_toolbar)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        provideDataBase(applicationContext)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount;

        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val manager                 = getSystemService(Context.SEARCH_SERVICE) as? SearchManager
        val searchItem: MenuItem?   = menu?.findItem(R.id.main_menu_search_icon)
        val searchView: SearchView? = searchItem?.actionView as SearchView

        searchView?.setSearchableInfo(manager?.getSearchableInfo(componentName))
        searchView?.queryHint = resources.getString(R.string.search)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance(newText))
                    .commitNow()
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}