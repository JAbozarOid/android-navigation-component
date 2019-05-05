package com.zamanak.navapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import com.zamanak.navapp.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListFragment.OnListFragmentInteractionListener{

    /**
     * navigation action is en element in navigation graph that point to the particular destination
     * navigation action has own unique id that you can reference to your code instead the destination id
     */

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {

        Log.i("navigation","Selected $item")

        /*val args = Bundle();
        args.putString("param1" , "Selected")
        args.putString("param2" , item.toString())*/
        // ********* as traditional way for passing data between fragments always key must be same for example "param1" in two fragments is the same
        // ********* but Android offer another way for passing data which called "Safe Args Component"
        // ********* Safe Args generate classes that you can use send and receive values with methods that created for each parameters
        // ********* for use Safe Args add classpath to build.gradle in project level

        val action = ListFragmentDirections.ActionToParams()
        action.setParam1("Selected (with safeargs)")
        action.setParam2(item.toString())

        //findNavController(R.id.nav_host).navigate(R.id.params_dest,args) // navigation to fragment by destination
        //findNavController(R.id.nav_host).navigate(R.id.action_to_params,args) // navigation to destination by ""navigation action""

        // ***** instead of id and args we pass action when using Safe Args Component
        findNavController(R.id.nav_host).navigate(action)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                findNavController(R.id.nav_host).navigate(R.id.main_dest)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                findNavController(R.id.nav_host).navigate(R.id.list_dest)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val args = Bundle();
                args.putString("param1" , "Abozar")
                args.putString("param2" , "Raghibdoust")
                //findNavController(R.id.nav_host).navigate(R.id.params_dest,args) // params_dest has default value that is Alexander Hamilton so we can put another value in Bundle and pass it to params_dest,navigate to fragment by destination
                findNavController(R.id.nav_host).navigate(R.id.action_global_params,args) // navigate to fragment by navigation action as a global which it name is "action_global_params"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
