package com.example.kipmnotes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.kipmnotes.R
import com.example.kipmnotes.databinding.ActivityHomeBinding
import com.example.kipmnotes.fragment.*
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var mAuth:FirebaseAuth
    var previouseMenuItem:MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAuth = FirebaseAuth.getInstance()


//      OpenHomeFragment with onStart
        openHome()

//      calling Toolbar function
        setUpToolbar()


//       make hamburger icon working
        val actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


//        Adding navigation clicks actions
        binding.navigationView.setNavigationItemSelectedListener {

            if (previouseMenuItem != null){
                previouseMenuItem?.isChecked = false
            }
            it.isCheckable= true
            it.isChecked = true
            previouseMenuItem = it

            when(it.itemId){

//                home menu clicks
                R.id.home -> {
                    openHome()
                    binding.drawerLayout.closeDrawers()
                }


//                Adding clicks on Study Menu
                R.id.study -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,StudyFragment())
                        .commit()
                    supportActionBar?.title = "Study"
                    binding.drawerLayout.closeDrawers()
                }

//                Placements Menu clicks
                R.id.placments -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,PlacementFragment())
                        .commit()
                    supportActionBar?.title = "Placements"
                    binding.drawerLayout.closeDrawers()
                }

//              Roadmaps Menu Clicks
                R.id.roadmaps ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,RoadmapFragment())
                        .commit()
                    supportActionBar?.title = "Roadmaps"
                    binding.drawerLayout.closeDrawers()
                }


//                Programming language Clicks
                R.id.language ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, LanguageFragment())
                        .commit()
                    supportActionBar?.title = "Pragramming Languages"
                    binding.drawerLayout.closeDrawers()
                }


//                Adding clicks on ExtraSkills
                R.id.skills ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,SkillsFragment())
                        .commit()
                    supportActionBar?.title = "Extra SKills"
                    binding.drawerLayout.closeDrawers()
                }

//                Adding clicks on interview
                R.id.interview ->{
                    supportActionBar?.title = "Skills"
                    binding.drawerLayout.closeDrawers()
                }


//              Adding clicks on About Menu
                R.id.about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame,AboutFragment())
                        .commit()
                    supportActionBar?.title = "About Us"
                    binding.drawerLayout.closeDrawers()
                }


//              Adding Clicks on LogOut
                R.id.logOut ->{
                    mAuth.signOut()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

//    OpenHomeFunction
    private fun openHome(){
        val fragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
        supportActionBar?.title = "Home"
        binding.navigationView.setCheckedItem(R.id.home)
    }



//  Creating an function for setting up toolbar with hamburger icon
    private fun setUpToolbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Home"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

//  setting up hamburger icon actions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home){
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }


//    Added backPressFunctionality
    override fun onBackPressed() {
        var frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is HomeFragment -> openHome()
            else -> super.onBackPressed()
        }

    }


}
