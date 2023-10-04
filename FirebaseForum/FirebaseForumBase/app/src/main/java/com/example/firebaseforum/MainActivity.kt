package com.example.firebaseforum

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.firebaseforum.databinding.ActivityMainBinding
import com.example.firebaseforum.firebase.FirebaseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView


@OptIn(NavigationUiSaveStateControl::class)
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    // Declare the ViewBinding variable for the main activity
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the ViewBinding for the main activity
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        // Get the navigation controller for the main activity
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Set up the app bar configuration to include the home and forums destinations
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_forums
            )
        )
        // Set up the action bar with the navigation controller and app bar configuration
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Set up the bottom navigation view with the navigation controller
        navView.apply {
            setupWithNavController(navController)
        }
        // Add a listener to the navigation controller for destination changes
        navController.addOnDestinationChangedListener(this)

        // Get the back button dispatcher and add the override callback to handle the back button press
        val dispatcher: OnBackPressedDispatcher = onBackPressedDispatcher
        dispatcher.addCallback(this, overrideCallback)
    }

    // Inflate the options menu for the action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    // Prepare the options menu for display
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        // Get the current navigation controller and destination
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navDestination =
            navController.currentDestination ?: return super.onPrepareOptionsMenu(menu)
        // Hide the logout button if the current destination is the login screen
        menu?.findItem(R.id.logout)?.isVisible = navDestination.id != R.id.navigation_login

        return super.onPrepareOptionsMenu(menu)
    }

    // Handle selection of an item from the options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                // Log out the user and navigate to the login screen
                FirebaseHandler.Authentication.logout()
                // Go to the login screen
                findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_login)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        //Disable the bottom navigation for the login Fragment
        if (destination.id == R.id.navigation_login) {
            binding.navView.visibility = View.GONE
        } else {
            binding.navView.visibility = View.VISIBLE
        }
        //Each time we change the navigation destination the menu will be invalidated/unieważniony
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        invalidateOptionsMenu()
    }

    // Declare a callback to handle the back button press
    private val overrideCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Get the current navigation controller and destination
            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            val currentDestination = navController.currentDestination
            // Check if the current destination is either the home or login screen,
            // if so finish the activity, else pop the back stack
            currentDestination?.let { dest ->
                if (dest.id == R.id.navigation_home || dest.id == R.id.navigation_login) {
                    finish()
                    return
                } else navController.popBackStack()
            }
        }
    }
}