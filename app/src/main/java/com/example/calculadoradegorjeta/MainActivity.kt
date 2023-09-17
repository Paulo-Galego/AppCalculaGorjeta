package com.example.calculadoradegorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.processNextEventInCurrentThread

class MainActivity : AppCompatActivity() {

    private lateinit var editValor: EditText
    private lateinit var textPorcentagem: TextView
    private lateinit var textGorjeta: TextView
    private lateinit var textTotal: TextView
    private lateinit var seekBarGorjeta: SeekBar

    private var porcentagem: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editValor = findViewById(R.id.editaValor)
        textGorjeta = findViewById(R.id.textGorjeta)
        textPorcentagem = findViewById(R.id.textPorcentagem)
        textTotal = findViewById(R.id.textTotal)
        seekBarGorjeta = findViewById(R.id.seekBarGorjeta)


        //Adicionar o listener SeekBar
        seekBarGorjeta.max = 100

        seekBarGorjeta.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                porcentagem = p1.toDouble()
                textPorcentagem.setText("${Math.round(porcentagem)} %" )
                calcular()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

    }

    fun calcular(){
        var valorRecuperado = editValor.text.toString()
        if(valorRecuperado == null || valorRecuperado.isEmpty()){
            Toast.makeText(this,"Digite um valor primeiro", Toast.LENGTH_LONG).show()
        }else{
            var valorDigitado: Double = valorRecuperado.toDouble()

            //Calcula a gorjeta total
             var gorjeta: Double = valorDigitado *  (porcentagem/100)
            var total = gorjeta + valorDigitado

            //exibir a gorjeta e total
            textGorjeta.text = "R$ $gorjeta"
            textTotal.text = "R$ $total"

        }
    }
}