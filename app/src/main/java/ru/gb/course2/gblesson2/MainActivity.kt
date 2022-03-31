package ru.gb.course2.gblesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.course2.gblesson2.databinding.ActivityMainBinding
import ru.gb.course2.gblesson2.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.container
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }
}