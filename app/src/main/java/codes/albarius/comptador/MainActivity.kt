package codes.albarius.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val INITIAL_TIME = 20

    private val TAG = MainActivity::class.java.simpleName

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = INITIAL_TIME

    internal var appStarted = false
    internal lateinit var countdownTimer : CountDownTimer
    internal val initialCountDownTimer : Long = time.toLong() *1000
    internal val intervalCountDownTimer : Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"Hola mon! onCreate")
        Log.d(TAG,counter.toString())
        Log.d(TAG,time.toString())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCountDown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        //Actualitzar  o definir valor counterview

        tapMeButton.setOnClickListener {
            if(!appStarted){
                startGame()
            }
            incrementCounter()
        }

        timeTextView.text = getString(R.string.timeText,time)
    }

    private fun startGame() {
        countdownTimer.start()
        appStarted = true
    }

    private fun initCountDown(){
        countdownTimer = object  : CountDownTimer(initialCountDownTimer, intervalCountDownTimer){
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeTextView.text = timeLeft.toString()
            }

            override fun onFinish() {
                endGame()
            }

        }
    }

    private fun incrementCounter(){
        counter += 1
        counterTextView.text = counter.toString()
    }

    private fun endGame(){
        Toast.makeText(this,getString(R.string.endgame, counter), Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun resetGame() {
        // RESET PUNTUACIÓ A ZERO
        counter = 0
        counterTextView.text = counter.toString()

        // REINICIALITZAR EL COMPTADOR
        time = INITIAL_TIME
        timeTextView.text = time.toString()
        initCountDown()

        // GAME STARTED A FALSE
        appStarted = false
    }
}