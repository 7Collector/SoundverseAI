package seven.collector.soundverseai.data

data class Message(
    val text: String,
    val isFromUser: Boolean,
    val hasButton: Boolean = false
)