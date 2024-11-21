package com.example.aliexpresspricetracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aliexpresspricetracker.data.model.ProductDetails
import com.example.aliexpresspricetracker.data.network.ApiClient
import com.example.aliexpresspricetracker.ui.theme.AliExpressPriceTrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AliExpressPriceTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductDetailsScreen()
                }
            }
        }
    }
}

@Composable
fun ProductDetailsScreen() {
    var productDetails by remember { mutableStateOf<ProductDetails?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column {
        if (productDetails != null) {
            Text("Title: ${productDetails?.title}")
            Text("Price: ${productDetails?.price}")
            // ... display other product details ...
        } else {
            Text("Loading product details...")
        }

        Button(onClick = {
            coroutineScope.launch {
                try {
                    productDetails = ApiClient.aliExpressService.getProductDetails("productId")
                } catch (e: Exception) {
                    // Handle error
                }
            }
        }) {
            Text("Fetch Product Details")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AliExpressPriceTrackerTheme {
        ProductDetailsScreen()
    }
}