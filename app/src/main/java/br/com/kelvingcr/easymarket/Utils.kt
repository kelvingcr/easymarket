package br.com.kelvingcr.easymarket

import android.app.AlertDialog
import android.content.Context
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {
        fun dataAtual(data: Date) : String {
            return SimpleDateFormat("dd/MM/yyyy").format(Date())
        }

        fun AlertDialogBuilderSimple(title: String, subtitle: String, context: Context, listener: AlertDialogListener, positivebtn: String = "Sim", neutralbtn: String = "Não") {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(subtitle)
                .setPositiveButton(positivebtn) { dialog, which ->
                    listener.onPressPositiveButton()
                }
                .setNeutralButton(neutralbtn) { dialog, which ->
                    listener.onPressNeutralButton()
                }
                .show()
        }
        fun formatNumber(number: Double) : String {
            if(number >= 1000 && number < 10000) {
                return "$${number.toString()[0]}k"
            } else if(number >= 10000 && number < 100000) {
                return "$${number.toString()[0]}kk"
            }
            return NumberFormat.getCurrencyInstance().format(number)
        }
    }
}