package ru.grach

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import ru.grach.features.login.configureLoginRouting
import ru.grach.features.register.configureRegisterRouting
import ru.grach.plugins.*

fun main() {
    val config = HikariConfig("hikari.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

//    Database.connect("jdbc:postgresql://localhost:5432/Grach1.0", driver = "org.postgresql.Driver",
//        user ="postgres", password="qwertyu" )
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
        configureSerialization()
    }.start(wait = true)
}
