package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var betmoney:Int = 10;
    var coin:Int = 1000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_str.setOnClickListener{ onStartButtonTapped(it) };
        btn_rst.setOnClickListener{ onrstButtonTapped() }
        btn_up.setOnClickListener{ onupButtonTapped() }
        btn_down.setOnClickListener{ ondownButtonTapped() }
    }

    fun onStartButtonTapped(view: View?){
        val intent = Intent(this,GameActivity::class.java)
        intent.putExtra("temoti",coin)
        intent.putExtra("kakekin",betmoney)
        startActivity(intent)
    }

    fun onupButtonTapped(){
        if(coin>0) {
            betmoney += 10;
            coin -= 10;
            txv_Bet.setText(betmoney.toString());
            txv_Coin.setText(coin.toString());
        }
    }

    fun ondownButtonTapped(){
        if(betmoney>=10) {
            betmoney -= 10;
            coin += 10;
            txv_Bet.setText(betmoney.toString());
            txv_Coin.setText(coin.toString());
        }
    }

    fun onrstButtonTapped(){
        coin = 1000;
        betmoney = 10;
        txv_Coin.setText(coin.toString());
        txv_Bet.setText(betmoney.toString());
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.clear()
                .apply()
    }

}

