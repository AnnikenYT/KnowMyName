plugins {
    id("com.possible-triangle.gradle") version ("0.2.5")
}

val yarn_mappings: String by extra
val continuity_version: String by extra

fabric {
    enableMixins()

    mappings {
        dependencies.create("net.fabricmc:yarn:${yarn_mappings}:v2")
    }
}

repositories {
    modrinthMaven()
}

dependencies {
    modRuntimeOnly("maven.modrinth:continuity:${continuity_version}")
}

enablePublishing()

val possibleMinecraftVersions = project.extra["possible_minecraft_versions"].toString().split(",")

uploadToCurseforge {
    minecraftVersions = possibleMinecraftVersions
}

uploadToModrinth {
    minecraftVersions = possibleMinecraftVersions
    syncBodyFromReadme()
}
