package xyz.jayanta.examsystem.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.jayanta.examsystem.R
import xyz.jayanta.examsystem.api.RetrofitClient
import xyz.jayanta.examsystem.models.DefaultResponse

/**
 * Sign Up Activity
 */
class Signup : AppCompatActivity() {

    private lateinit var name_in: EditText
    private lateinit var phone_in: EditText
    private lateinit var email_in: EditText
    private lateinit var password_in: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        name_in = findViewById(R.id.name_in)
        phone_in = findViewById(R.id.phone_in)
        email_in = findViewById(R.id.email_in)
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
        val username = "bjayanta"
        val password = password_in.text.toString().trim()
        val cpassword = password_in.text.toString().trim()

        RetrofitClient.instance.createUser(name, phone, email, username, password, cpassword)
            .enqueue(object: Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()

                    println(t.message)
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    Toast.makeText(applicationContext, response.body()?.success.toString(), Toast.LENGTH_LONG).show()

                    println("Response: ")
                    println("Error: " + response.body()?.error.toString())
                    println("Success: " + response.body()?.success)
                }

            })
    }
}
