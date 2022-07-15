package com.example.coinflip

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    //Initialize variables and define them later
    private lateinit var coinImage: ImageView
    private lateinit var totalCount: TextView
    private lateinit var headsCount: TextView
    private lateinit var tailsCount: TextView
    private lateinit var headsPercent: TextView
    private lateinit var headsProgressBar: ProgressBar
    private lateinit var tailsPercent: TextView
    private lateinit var tailsProgressBar: ProgressBar
    private lateinit var simNumber: EditText

    //Counter variables to keep track of heads, tails, and total flips
    private var heads = 0
    private var tails = 0
    private var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get a reference to our switch and buttons
        val simSwitch: SwitchCompat = findViewById(R.id.main_sw_simulate)
        val flipButton: Button = findViewById(R.id.main_bt_flip)
        val resetButton: Button = findViewById(R.id.main_bt_reset)
        val simButton: Button = findViewById(R.id.main_bt_simulate)

        //Set listeners for our buttons
        simSwitch.setOnCheckedChangeListener { buttonView, isChecked -> enableSim(isChecked) }
        flipButton.setOnClickListener { flip() }
        resetButton.setOnClickListener { reset() }
        simButton.setOnClickListener { sim() }

        //Set values to other views
        coinImage = findViewById(R.id.main_iv_coin)
        totalCount = findViewById(R.id.main_tv_total_flips)
        headsCount = findViewById(R.id.main_tv_total_heads)
        tailsCount = findViewById(R.id.main_tv_total_tails)
        headsPercent = findViewById(R.id.main_tv_heads_percent)
        tailsPercent = findViewById(R.id.main_tv_tails_percent)
        headsProgressBar = findViewById(R.id.main_pb_heads_percent)
        tailsProgressBar = findViewById(R.id.main_pb_tails_percent)
        simNumber = findViewById(R.id.main_ed_sim_number)
    }

    //Turn on/off simulation mode
    private fun enableSim(onState: Boolean){

    }

    //Simulate a single coin flip
    private fun flip(){
        val randomNumber = (0..1).random()

        //update based on value
        if(randomNumber == 0){
            update("heads")
        }else{
            update("tails")
        }
    }

    //Update the UI based on the previous coin flip
    private fun update(coinState: String){
        //Change image view to reflect the flipped state and increment counts
        if(coinState == "heads"){
            heads++
            coinImage.setImageResource(R.drawable.ic_heads_icon)
        } else{
            tails++
            coinImage.setImageResource(R.drawable.ic_tails_icon)
        }

        //Increment total flips
        total++

        //Update TextViews to show results
        totalCount.text = "Total Flips: $total"
        headsCount.text = "Total Heads: $heads"
        tailsCount.text = "Total Tails: $tails"

        //Update statistics
        updateStatistics()
    }

    //Update the statistics UI based on the previous coin flip
    private fun updateStatistics(){
        val headsPercentResult = round((heads.toDouble()/total.toDouble()) * 100)
        val tailsPercentResult = round((tails.toDouble()/total.toDouble()) * 100)

        //update textviews for percentages
        headsPercent.text = "Heads: $headsPercentResult%"
        tailsPercent.text = "Tails: $tailsPercentResult%"

        //Update progress Bars
        headsProgressBar.progress = headsPercentResult.toInt()
        tailsProgressBar.progress = tailsPercentResult.toInt()
    }

    //Reset all data for the simulation
    private fun reset(){

    }

    //Run the coin simulation for a set number of flips
    private fun sim(){

    }
}