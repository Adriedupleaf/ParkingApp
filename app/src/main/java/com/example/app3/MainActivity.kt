package com.example.app3

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.app3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint

class MainActivity : NavController.OnDestinationChangedListener, AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    protected val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment).navController }

    private val firstLevelDestinations: Set<Int> = setOf(
        R.id.navigation_login
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

//        val navView: BottomNavigationView = binding.navView
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        navController.addOnDestinationChangedListener(this)

        //Configure app permissions
        val permissions =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.POST_NOTIFICATIONS)
            } else {
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            }

        if (permissions.any { !checkPermissionGranted(it) }) {

            if (permissions.any { shouldShowRequestPermissionRationale(it) }) {
                // Display a dialogue explaining the required permissions.
            }

            val permissionsLauncher =
                registerForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions(),
                ) { permissionResults ->
                    if (permissionResults.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false)) {
                        onLocationPermissionGranted()
                    } else {
                        finish()
                    }
                }

            permissionsLauncher.launch(permissions)
        } else {
            android.os.Handler(Looper.getMainLooper()).postDelayed({ onLocationPermissionGranted() }, TimeUnit.SECONDS.toMillis(2))
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }
    private fun checkPermissionGranted(permissionToCheck: String): Boolean =
        ContextCompat.checkSelfPermission(this, permissionToCheck) == PackageManager.PERMISSION_GRANTED

    private fun onLocationPermissionGranted() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        // Hide the toolbar to maximize the navigation UI
        supportActionBar?.hide()


//        initializeNavigationApi()
    }

//    override fun onTrimMemory(level: Int) {
//        super.onTrimMemory(level)
//        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_home_map) as? HomeFragment
//        val navView = fragment?.view?.findViewById<NavigationView>(R.id.navigation_view)
//        navView?.onTrimMemory(level)
//    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
//        if (destination.id in firstLevelDestinations) {
//            binding.navView.isVisible = false
//        } else {
//            binding.navView.isVisible = true
//        }
    }
}