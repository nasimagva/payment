package com.example.razorpay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.razorpay.databinding.ActivityMainBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), PaymentResultListener{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPay.setOnClickListener {
            val amt = binding.etAmount.text.toString().trim()
            val name = binding.etName.text.toString().trim()
            val contact = binding.etContact.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val amount = Math.round(amt.toFloat()*100).toInt()
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_QPvuMBUFuHf0MT")
            checkout.setImage(R.drawable.baseline_payment_24)
            val obj = JSONObject()
            try {
                obj.put("name", name)
                obj.put("description", "Healthcare")
                obj.put("theme.color", "")
                obj.put("currency", "INR")
                obj.put("amount", amount)
                obj.put("prefill.contact", contact)
                obj.put("prefill.email", email)

                checkout.open(this, obj)
            } catch (e: JSONException){
                e.printStackTrace()
            }
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Successful: $p0", Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Failed due to error : $p1", Toast.LENGTH_LONG).show()
    }
}