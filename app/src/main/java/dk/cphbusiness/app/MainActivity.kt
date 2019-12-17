package dk.cphbusiness.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.net.DatagramPacket
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.net.DatagramSocket
import java.net.InetAddress
import java.nio.charset.StandardCharsets
import java.util.*
import android.R.attr.data
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val hLabel : TextView = findViewById(R.id.textView)
        val receiveData: ByteArray = ByteArray(255)
        val receivePacket : DatagramPacket = DatagramPacket(receiveData, receiveData.size)

        val clientSocket : DatagramSocket = DatagramSocket()
        val ipAddress: InetAddress = InetAddress.getByName("192.168.43.12")
        var magnetIndex : Int = 0


        button.setOnClickListener {
            //textView.text = "FORWARD"
            sendUDPPacket("F", ipAddress, clientSocket)
        }

        button1.setOnClickListener {
            //textView.text = "FORWARD"
            sendUDPPacket("B", ipAddress, clientSocket)
        }

        button2.setOnClickListener {
            //textView.text = "FORWARD"
            sendUDPPacket("E", ipAddress, clientSocket)
            finish()
            System.exit(0)
        }

        button3.setOnClickListener {
            //textView.text = "FORWARD"
            sendUDPPacket("S", ipAddress, clientSocket)
        }

        button4.setOnClickListener {
            //textView.text = "FORWARD"
            sendUDPPacket("V950", ipAddress, clientSocket)
        }

        button5.setOnClickListener {
            sendUDPPacket("V450", ipAddress, clientSocket)
        }

        button6.setOnClickListener {
            sendUDPPacket("V775", ipAddress, clientSocket)
        }

        button7.setOnClickListener {
            if (magnetIndex < 10) {
                sendUDPPacket("M0$magnetIndex", ipAddress, clientSocket)
            } else{
                sendUDPPacket("M$magnetIndex", ipAddress, clientSocket)
            }
        }

        button8.setOnClickListener {
            if (magnetIndex > 0){
                magnetIndex--
            }
            button7.setText("MAGNET COUNTER: $magnetIndex")
        }

        button9.setOnClickListener {
            magnetIndex++
            button7.setText("MAGNET COUNTER: $magnetIndex")
        }

        //TextView hLabel = (TextView)findViewById(R.id.)

        //hLabel.text = "lego tog styre app"


    }

    private fun sendUDPPacket(udpmsg: String, ipAddress : InetAddress, clientSocket : DatagramSocket) {
        val byteInputStream: ByteArray = udpmsg.toByteArray(StandardCharsets.UTF_8)
        val sendPacket = DatagramPacket(byteInputStream, byteInputStream.size, ipAddress, 4210)
        clientSocket.send(sendPacket)
    }
}