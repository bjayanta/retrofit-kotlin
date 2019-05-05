package xyz.jayanta.examsystem.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import android.widget.TextView
import xyz.jayanta.examsystem.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInBtn = findViewById<Button>(R.id.signin_btn)
        signInBtn.setOnClickListener(View.OnClickListener {
            var email = "jayanta@maxsop.com"
            var password = "123456"

        })

        val signup = findViewById<TextView>(R.id.signup)
        signup.setOnClickListener(View.OnClickListener {
            val signupIntent = Intent(this, Signup::class.java)
            startActivity(signupIntent)
        })
    }

}
