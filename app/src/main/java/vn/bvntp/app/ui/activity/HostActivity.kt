package vn.bvntp.app.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionManager
import vn.bvntp.app.R
import vn.bvntp.app.databinding.ActivityHostBinding
import vn.bvntp.app.helper.ClickHandler

class HostActivity : AppCompatActivity() {

    private var _binding: ActivityHostBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)


//         config to display up button and display which screen you are in
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
//         config to display side navigation bar
        val drawer = binding.drawerLayout
        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(drawer)
        appBarConfiguration = builder.build()
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        // config to display bottom navigation bar
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNavigationView.setupWithNavController(navController)
        val navView = binding.navView
        NavigationUI.setupWithNavController(navView, navController)
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)




        binding.fab.setOnClickListener {
            ClickHandler.AnimateButtonOnClick(binding.fab, {})
        }

        binding.fab.setOnClickListener{
            binding.maBenhNhanInput.visibility = View.VISIBLE

            TransitionManager.beginDelayedTransition(binding.constraintLayout)

            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.constraintLayout)

            // Move button to bottom-start
            constraintSet.clear(R.id.fab, ConstraintSet.END)
            constraintSet.connect(R.id.fab, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16)
            constraintSet.connect(R.id.fab, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16)

            // Position EditText next to the button
            constraintSet.connect(R.id.editText, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16)
            constraintSet.connect(R.id.editText, ConstraintSet.START, R.id.fab, ConstraintSet.END, 16)

            constraintSet.applyTo(binding.constraintLayout)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
//    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}