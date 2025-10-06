plugins {
    id("com.possible-triangle.fabric")
}

val yarn_mappings: String by extra
val continuity_version: String by extra

dependencies {
    modRuntimeOnly("maven.modrinth:continuity:${continuity_version}")
}

val possibleMinecraftVersions = project.extra["possible_minecraft_versions"].toString().split(",")


upload {
    maven {
        nexus()
    }

    forEach {
        minecraftVersions = possibleMinecraftVersions
    }

    modrinth {
        syncBodyFromReadme()
    }
}

enableSpotless()