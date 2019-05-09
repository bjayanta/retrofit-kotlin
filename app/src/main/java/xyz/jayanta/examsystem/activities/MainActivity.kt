package xyz.jayanta.examsystem.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jayanta.examsystem.R
import xyz.jayanta.examsystem.api.RetrofitClient
import xyz.jayanta.examsystem.models.SigninResponse


class MainActivity : AppCompatActivity() {
    private lateinit var email_in: EditText
    private lateinit var password_in: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init input field
        email_in = findViewById(R.id.email_in)
        password_in = findViewById(R.id.password_in)

        val signInBtn = findViewById<Button>(R.id.signin_btn)
        signInBtn.setOnClickListener(View.OnClickListener {
            var email = email_in.text.toString().trim()
            var password = password_in.text.toString().trim()

            RetrofitClient.instance.signin(email, password)
                .enqueue(object: Callback<SigninResponse>{
                    override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<SigninResponse>, response: Response<SigninResponse>) {
                        if(!response.body()?.error!!) {

                        } else {
                            Toast.makeText(applicationContext, response.body()?.success.toString(), Toast.LENGTH_LONG).show()
                        }
                    }

                })
        })

        val signup = findViewById<TextView>(R.id.signup)
        signup.setOnClickListener(View.OnClickListener {
            val signupIntent = Intent(this, Signup::class.java)
            startActivity(signupIntent)
        })
    }

}
