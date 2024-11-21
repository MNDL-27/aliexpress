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