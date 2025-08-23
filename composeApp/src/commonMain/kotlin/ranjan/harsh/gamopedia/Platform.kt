package ranjan.harsh.gamopedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform