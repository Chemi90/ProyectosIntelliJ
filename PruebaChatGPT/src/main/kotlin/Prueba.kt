import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class GPT3Client(private val apiKey: String) {

    private val client = OkHttpClient()

    fun askQuestion(question: String): String? {
        val url = "https://api.openai.com/v1/engines/davinci/completions"
        val requestBody = JSONObject()
            .put("prompt", "Pregunta: $question\nRespuesta:")
            .put("max_tokens", 50)  // Adjust max_tokens based on your requirements

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody.toString().toRequestBody(mediaType))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                println("Error en la respuesta de la API: ${response.code}")
                println("Mensaje de error: ${response.message}")
                println("Cuerpo de la respuesta: ${response.body?.string()}")
                return null
            }

            // Parsea la respuesta como JSON
            val responseBody = response.body?.string()
            val jsonResponse = JSONObject(responseBody)

            // Obtiene la cuenta de tokens utilizados
            val tokensUsed = jsonResponse.getInt("usage").toInt()
            println("Tokens utilizados en la respuesta: $tokensUsed")

            return responseBody
        }
    }
}

fun main() {
    val apiKey = "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"
    val gpt3Client = GPT3Client(apiKey)

    val pregunta = "¿Cuál es el significado de la vida, el universo y todo lo demás?"
    println("Pregunta: $pregunta")

    val respuesta = gpt3Client.askQuestion(pregunta)
    println("Respuesta: $respuesta")
}
