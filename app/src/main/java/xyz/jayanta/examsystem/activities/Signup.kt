package xyz.jayanta.examsystem.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jayanta.examsystem.R
import xyz.jayanta.examsystem.api.RetrofitClient
import xyz.jayanta.examsystem.models.SignupResponse

/**
 * Sign Up Activity
 */
class Signup : AppCompatActivity() {

    private lateinit var name_in: EditText
    private lateinit var phone_in: EditText
    private lateinit var email_in: EditText
    private lateinit var username_in: EditText
    private lateinit var password_in: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        name_in = findViewById(R.id.name_in)
        phone_in = findViewById(R.id.phone_in)
        email_in = findViewById(R.id.email_in)
        username_in = findViewById(R.id.username_in)
        password_in = findViewById(R.id.password_in)

        val registration_btn = findViewById<Button>(R.id.registration_btn)
        registration_btn.setOnClickListener(View.OnClickListener {
            signup()
        })

        val back_btn = findViewById<Button>(R.id.backBtn)
        back_btn.setOnClickListener(View.OnClickListener {
            val signin = Intent(this, MainActivity::class.java)
            startActivity(signin)
            finish()
        })
    }

    fun signup() {
        // get input values
        val name = name_in.text.toString().trim()
        val phone = phone_in.text.toString().trim()
        val email = email_in.text.toString().trim()
        val username = username_in.text.toString().trim()
        val password = password_in.text.toString().trim()
        val cpassword = password_in.text.toString().trim()

        RetrofitClient.instance.createUser(name, phone, email, username, password, cpassword)
            .enqueue(object: Callback<SignupResponse>{
                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                    // check response code and response data
                    Log.d("Response Code: ", response.code().toString())
                    Log.d("Response Body: ", response.body().toString())

                    // check the response code
                    if(response.isSuccessful()) {
                        // show a toast message
                        Toast.makeText(applicationContext, "Response: " + response.body()?.message, Toast.LENGTH_LONG).show()

                        // call dashboard
                        val signin = Intent(applicationContext, MainActivity::class.java)
                        startActivity(signin)
                        finish()
                    } else {
                        var message = "${ response.code().toString() } Unauthorized. Please change the above data and resubmit."

                        // show a toast message for error
                        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                    }
                }

            })
    }
}
