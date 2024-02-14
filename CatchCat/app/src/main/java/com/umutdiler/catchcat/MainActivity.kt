package com.umutdiler.catchcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.umutdiler.catchcat.databinding.ActivityMainBinding
import java.util.Random
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var score = 0
    var runnable : Runnable = Runnable{}
    var handler : Handler = Handler()
    var imageArray = ArrayList<ImageView>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        runnable = object : Runnable{
            override fun run() {
                handler.postDelayed(this,500)
                hideImage()

            }
        }
        handler.post(runnable)

        imageArray.add(binding.imageView)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)





        object : CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timerText.text = "Timer : ${millisUntilFinished / 1000}"
                
            }

            override fun onFinish() {
                binding.timerText.text = "Timer : 0"
                handler.removeCallbacks(runnable)
                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setMessage("Do You Want Play Again")
                alert.setPositiveButton("Yes"){ p0 ,p1 ->
                    val intentFromMain = intent
                    finish()
                    startActivity(intentFromMain)
                }
                alert.setNegativeButton("No"){ p0, p1 ->
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG)
                }
                alert.show()
            }

        }.start()



    }
    fun score(view : View){
        score = score + 1
        binding.scoreText.text = "Score : ${score}"

    }
    fun hideImage()
    {
        for(image in imageArray)
        {
            image.visibility = View.INVISIBLE
        }
        var random = Random()
        val randomIndex = random.nextInt(9)
        imageArray[randomIndex].visibility = View.VISIBLE
    }


}