package com.example.atv01_studioandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.atv01_studioandroid.ui.theme.Atv01_StudioAndroidTheme
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import java.util.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Atv01_StudioAndroidTheme {
                Atv01_StudioAndroid()
            }
        }
    }
}

@Composable
fun Atv01_StudioAndroid() {
    var title by remember { mutableStateOf(TextFieldValue()) }
    var status by remember { mutableStateOf("Not Done") }
    var priority by remember { mutableStateOf("Low") }
    var date by remember { mutableStateOf("Choose Date") }
    var time by remember { mutableStateOf("Choose Time") }
    val calendar = Calendar.getInstance()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(text = "Title", color = Color.White)
        TextField(
            value = title,
            onValueChange = { title = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.Black,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Status", color = Color.White)
        Row {
            RadioButton(selected = status == "Done", onClick = { status = "Done" })
            Text("Done", color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(selected = status == "Not Done", onClick = { status = "Not Done" })
            Text("Not Done", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Priority", color = Color.White)
        Row {
            listOf("Low", "Medium", "High").forEach { option ->
                Row {
                    RadioButton(selected = priority == option, onClick = { priority = option })
                    Text(option, color = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Time and Date", color = Color.White)
        Row {
            Button(onClick = {
                DatePickerDialog(context, { _, year, month, day ->
                    date = "$year-${month + 1}-$day"
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
            }) {
                Text(date)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                TimePickerDialog(context, { _, hour, minute ->
                    time = "$hour:$minute"
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
            }) {
                Text(time)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {  }) { Text("Cancel") }
            Button(onClick = {
                title = TextFieldValue()
                status = "Not Done"
                priority = "Low"
                date = "Choose Date"
                time = "Choose Time"
            }) { Text("Reset") }
            Button(onClick = {  }) { Text("Submit") }
        }
    }
}