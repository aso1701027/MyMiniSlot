package jp.ac.asojuku.st.myminislot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*


class GameActivity : AppCompatActivity() {

    var coin = 0;
    var kake = 0;

    val imageArray:Array<Int> = arrayOf(
            R.drawable.banana,
            R.drawable.bar,
            R.drawable.bigwin,
            R.drawable.cherry,
            R.drawable.grape,
            R.drawable.lemon,
            R.drawable.orange,
            R.drawable.seven,
            R.drawable.waltermelon

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        btn_stp.setOnClickListener{ onStopButtonTapped() };

    }



    override fun onResume() {
        super.onResume()
        coin = intent.getIntExtra("temoti",1000)
        kake = intent.getIntExtra("kakekin",10)

        txv_coin.text = intent.getIntExtra("temoti",1000).toString()
        txv_bet2.text = intent.getIntExtra("kakekin",10).toString()
        // 戻るボタンのクリック反応（リスナーとコールバックメソッド）を設定する
        btn_back.setOnClickListener { finish() }

    }

    fun onStopButtonTapped(){
        var x = Random().nextInt(6);
        var y = Random().nextInt(6);
        var z = Random().nextInt(6);
        slot_Button1.setImageResource(imageArray[x]);
        slot_Button2.setImageResource(imageArray[y]);
        slot_Button3.setImageResource(imageArray[z]);
        if(x==y && y==z){
            var wincoin = kake * 7;
            coin += wincoin;

            val pref = PreferenceManager.getDefaultSharedPreferences(this);
            val editor = pref.edit()
            editor.putInt("temoti",coin)
                    .apply()

            txv_coin.setText(coin.toString())
        }else{
            var losecoin = kake;
            coin -= losecoin;

            val pref = PreferenceManager.getDefaultSharedPreferences(this);
            val editor = pref.edit()
            editor.putInt("temoti",coin)
                    .apply()

            txv_coin.setText(coin.toString())
        }
    }

}
