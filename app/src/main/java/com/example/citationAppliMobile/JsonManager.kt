package com.example.citationAppliMobile

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.*
import java.nio.charset.Charset

object JsonManager {
    @JvmStatic
    fun loadJSON(c: Context): String? {
        val json: String
        try {
            var f = File(Environment.getExternalStorageDirectory(), "/Download/data_recherche.json")
            val `is` : InputStream
            `is` = if(f.exists()){
                FileInputStream(f)
            }else{
                c.resources.openRawResource(R.raw.data_recherche)
            }
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.forName("UTF-8"))
            Log.i("json", json)

        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    @JvmStatic
    fun writeJSON(c: Context, content: String) {
        var f = File(Environment.getExternalStorageDirectory(), "/Download/data_recherche.json")
        f.createNewFile()
        var fileWriter = FileWriter(f)
        var writer = BufferedWriter(fileWriter)
        writer.write(content)
        writer.close()

    }

}