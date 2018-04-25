package com.rohit.residemenu

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.base_home.*
import com.special.ResideMenu.ResideMenu
import com.special.ResideMenu.ResideMenuItem

abstract class BaseHome : FragmentActivity() {

    private var resideMenu:ResideMenu? = null
    private val menuItems:ArrayList<ResideMenuItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.base_home)
        attachResideMenu()
    }

    override fun setContentView(layoutResID: Int) {
        //super.setContentView(layoutResID)
        val layoutInflater:LayoutInflater = LayoutInflater.from(this@BaseHome)
        val v = layoutInflater.inflate(layoutResID, null, false)
        screen_parent.addView(v)
        //setContentView(screen_parent)
    }

    private fun attachResideMenu(){
        // attach to current activity;
        resideMenu = ResideMenu(this)
        resideMenu?.setBackground(R.drawable.tree)
        resideMenu?.setShadowVisible(true)
        //resideMenu?.setBackgroundColor(resources.getColor(R.color.golden_trans))
        resideMenu?.attachToActivity(this)

        // create menu items;
        val titles = arrayOf("Home", "Profile", "Calendar", "Settings")
        val icon = intArrayOf(R.drawable.ic_menu_gallery,
                R.drawable.ic_menu_gallery,
                R.drawable.ic_menu_gallery,
                R.drawable.ic_menu_gallery)

        for (i in titles.indices) {
            val item = ResideMenuItem(this, icon[i], titles[i])
            menuItems.add(item)
            item.setOnClickListener(resideMenuClick)
            resideMenu?.addMenuItem(item, ResideMenu.DIRECTION_LEFT) // or  ResideMenu.DIRECTION_RIGHT
        }

        resideMenu?.menuListener = menuListener

        hamburger_menu.setOnClickListener{
            resideMenu?.openMenu(ResideMenu.DIRECTION_LEFT) // or ResideMenu.DIRECTION_RIGHT
            //resideMenu?.closeMenu()
        }
    }

    private val resideMenuClick = View.OnClickListener {
        if(it == menuItems[0]){
            Log.d("BaseHome::resideMenuClk", "item 0 clicked")
        }
    }

    private val menuListener = object : ResideMenu.OnMenuListener{
        override fun openMenu() {

        }

        override fun closeMenu() {

        }
    }

    override fun setContentView(view: View?) {
        throw NotImplementedError()
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        throw NotImplementedError()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
