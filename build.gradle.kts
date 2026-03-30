plugins {
    id("net.somethingcatchy.gradle") version ("0.0.7")
}

val yarn_mappings: String by extra

fabric {
    enableMixins()

    mappings {
        dependencies.create("net.fabricmc:yarn:${yarn_mappings}:v2")
    }
}

val possibleMinecraftVersions = project.extra["possible_minecraft_versions"].toString().split(",")

uploadToCurseforge {
    minecraftVersions = possibleMinecraftVersions
}

uploadToModrinth {
    minecraftVersions = possibleMinecraftVersions
    syncBodyFromReadme()
}

enablePublishing()
