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
                    println(t.message)
                    println(t.toString())
                }

                override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                    if(response.isSuccessful()) {
                        Toast.makeText(applicationContext, "Response: " + response.body()?.msg, Toast.LENGTH_LONG).show()

                        // call dashboard
                        // val dashboard = Intent(applicationContext, Dashboard::class.java)
                        // startActivity(dashboard)
                    } else {
                        // parse error message
                        Log.d("response: ", "code = " + response.code())
                        Log.d("response: ", "body= " + response.body())
                        Log.d("response: ", response.message().toString())
                    }

                    println("Response: " + response.code())
                    println("Message: " + response.body()?.msg)
                    println("Success: " + response.body()?.success)
                    println("Error: " + response.body()?.error)
                    println("Token: " + response.body()?.token)
                    println("Name: " + response.body()?.name)

                    println("Response body: " + response.body())
                }

            })
    }
}
