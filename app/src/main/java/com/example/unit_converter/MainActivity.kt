package com.example.unit_converter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unit_converter.ui.theme.Unit_ConverterTheme
import kotlin.math.roundToInt
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Unit_ConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
   var inputvalue by remember {mutableStateOf("")}
    var outputvalue by remember { mutableStateOf("") }
    var inputUnit by remember {mutableStateOf("Meters")}
    var outputunit by remember { mutableStateOf("Meters")}
    var iexpand by remember {mutableStateOf(false)}
    var oexpand by remember {mutableStateOf(false)}
    val conversionFactor = remember {mutableStateOf(1.00)}
    val oconversionFactor = remember {mutableStateOf(1.00)}
fun convertUnits(){
    var inputvaluetodouble= inputvalue.toDoubleOrNull() ?:0.0
    var result = (inputvaluetodouble * conversionFactor.value * 100.0 / oconversionFactor.value).roundToInt()/100.0
    outputvalue=result.toString()
}


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Unit Converter",
            style= MaterialTheme.typography.headlineLarge,
            color= Color.White,
            textAlign = TextAlign.Center,
            fontSize = 50.sp,

            modifier= Modifier.background(Purple40).fillMaxWidth()
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //In this the elements are stacked below each other
                   
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue,
            onValueChange = {
                inputvalue=it
            convertUnits()
                            },
            label={Text("Enter the value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //In this the elements are stacked next to each other
            //Input Box
            Box {
                //Input Button
                Button(onClick = { iexpand=true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iexpand, onDismissRequest = { iexpand=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            inputUnit="Centimeters"
                            iexpand=false
                            conversionFactor.value=0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            inputUnit="Meters"
                            iexpand=false
                            conversionFactor.value=1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            inputUnit="Feet"
                            iexpand=false
                            conversionFactor.value=0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            inputUnit="Millimeters"
                            iexpand=false
                            conversionFactor.value=0.001
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Output Button
                Button(onClick = { oexpand=true }) {
                    Text(outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oexpand, onDismissRequest = { oexpand=false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            outputunit="Centimeters"
                            oexpand=false
                            oconversionFactor.value=0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            outputunit="Meters"
                            oexpand=false
                            oconversionFactor.value=1.00
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            outputunit="Feet"
                            oexpand=false
                            oconversionFactor.value=0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            outputunit="Millimeters"
                            oexpand=false
                            oconversionFactor.value=0.001
                            convertUnits()
                        })
                }
            }
        }
            Spacer(modifier = Modifier.height(16.dp))

            Text("Result = $outputvalue $outputunit" ,
                style= MaterialTheme.typography.headlineMedium
                )


    }

}


@Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()
    }

