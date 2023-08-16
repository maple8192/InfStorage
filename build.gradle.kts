plugins {
    java
    kotlin("jvm") version "1.9.0"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
}

val pluginName = "InfStorage"
val groupId = "io.github.maple8192"
val pluginVersion = "1.0.0"
val authorName = "Maple8192"
val paperVersion = "1.20.1-R0.1-SNAPSHOT"

group = groupId
version = pluginVersion

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperLibrary(kotlin("stdlib"))
    paperLibrary("com.google.code.gson", "gson", "2.10.1")

    compileOnly("io.papermc.paper:paper-api:$paperVersion")
}

paper {
    main = "$groupId.${pluginName.lowercase()}.$pluginName"

    loader = "$groupId.${pluginName.lowercase()}.loader.LibraryLoader"
    hasOpenClassloader = true

    generateLibrariesJson = true

    foliaSupported = false

    apiVersion = "1.19"

    version = pluginVersion
    author = authorName
}