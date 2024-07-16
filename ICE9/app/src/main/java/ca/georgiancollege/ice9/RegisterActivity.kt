package ca.georgiancollege.ice9

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import ca.georgiancollege.ice9.databinding.ActivityLoginBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root)

    }
}