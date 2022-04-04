package com.example.collegementor.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager
import com.example.collegementor.R
import com.example.collegementor.databinding.ActivityHomeBinding
import com.example.collegementor.firebase.Firebase.mAuth
import com.example.collegementor.fragment.*
import com.example.collegementor.fragment.studyfragment.StudyFragment
import com.example.collegementor.fragment.studyfragment.SubjectFragment
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = mAuth

        openHomeFragment()
        setUpToolbar()

        binding.fabAddNotes.setOnClickListener {
            openDialog()
        }

//       make hamburger icon working
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        //Adding navigation clicks actions
        binding.navigationView.setNavigationItemSelectedListener {

            if (previousMenuItem != null) {
                previousMenuItem?.isChecked = false
            }
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when (it.itemId) {
//                home menu clicks
                R.id.home -> {
                    clearBackStack()
                    openHomeFragment()
                    binding.drawerLayout.closeDrawers()
                }
//                Adding clicks on Study Menu
                R.id.study -> {
                    clearBackStack()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, StudyFragment())
                        .commit()
                    supportActionBar?.title = "Select Course"
                    binding.drawerLayout.closeDrawers()
                }
//                Placements Menu clicks
                R.id.placments -> {
                    clearBackStack()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, PlacementFragment())
                        .commit()
                    supportActionBar?.title = "Placements"
                    binding.drawerLayout.closeDrawers()
                }
//              Roadmaps Menu Clicks
                R.id.roadmaps -> {
                    clearBackStack()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, RoadmapFragment())
                        .commit()
                    supportActionBar?.title = "Roadmaps"
                    binding.drawerLayout.closeDrawers()
                }
//                Programming language Clicks
                R.id.language -> {
                    clearBackStack()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, LanguageFragment())
                        .commit()
                    supportActionBar?.title = "Programming Languages"
                    binding.drawerLayout.closeDrawers()
                }
//                Adding clicks on ExtraSkills
                R.id.skills -> {
                    clearBackStack()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, SkillsFragment())
                        .commit()
                    supportActionBar?.title = "Extra SKills"
                    binding.drawerLayout.closeDrawers()
                }
//                Adding clicks on interview
                R.id.interview -> {
                    clearBackStack()
                    supportActionBar?.title = "Skills"
                    binding.drawerLayout.closeDrawers()
                }
//              Adding clicks on About Menu
                R.id.about -> {
                    clearBackStack()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, AboutFragment())
                        .commit()
                    supportActionBar?.title = "About Us"
                    binding.drawerLayout.closeDrawers()
                }
//              Adding Clicks on LogOut
                R.id.logOut -> {
                    firebaseAuth.signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    //  setting up hamburger icon actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.frame)) {
            is StudyFragment-> {
                if (supportActionBar?.title == "Select course"){
                    clearBackStack()
                    openHomeFragment()
                }
                supportFragmentManager.popBackStack()
            }
            is SubjectFragment -> {
                supportFragmentManager.popBackStack()
            }
            !is HomeFragment -> {
                clearBackStack()
                openHomeFragment()
            }
            else -> {
                clearBackStack()
                super.onBackPressed()
            }
        }
    }

    private fun openHomeFragment() {
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
        supportActionBar?.title = "Home"
        binding.navigationView.setCheckedItem(R.id.home)
    }

    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    //setting up toolbar with hamburger icon
    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Home"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun openDialog() {
        val dialogFragment = FragmentCustomDialogAddSubjects()
        dialogFragment.show(supportFragmentManager, "dialog")
    }


}
