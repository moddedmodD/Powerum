plugins {
	id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
	`maven-publish`
}

version = providers.gradleProperty("mod_version").get()
group = providers.gradleProperty("maven_group").get()

repositories {
	mavenCentral()
	maven {
		name = "Terraformers"
		url = uri("https://maven.terraformersmc.com/releases/")
		content {
			includeGroupAndSubgroups("com.terraformersmc")
			includeGroup("dev.emi")
		}
	}
}

dependencies {
	minecraft("com.mojang:minecraft:${providers.gradleProperty("minecraft_version").get()}")
	implementation("net.fabricmc:fabric-loader:${providers.gradleProperty("loader_version").get()}")
	implementation("net.fabricmc.fabric-api:fabric-api:${providers.gradleProperty("fabric_api_version").get()}")
	implementation("com.terraformersmc:modmenu:${providers.gradleProperty("modmenu_version").get()}")

	localRuntime("com.terraformersmc:modmenu:${providers.gradleProperty("modmenu_version").get()}")
}

tasks.processResources {
	inputs.property("version", version)
	inputs.property("fabric_api_version", providers.gradleProperty("fabric_api_version").get())

	filesMatching("fabric.mod.json") {
		expand(
			"version" to version,
			"fabric_api_version" to providers.gradleProperty("fabric_api_version").get()
		)
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.release = 25
}

tasks.jar {
	val mcVersion = providers.gradleProperty("minecraft_version").get()

	archiveFileName.set("${project.name}-${version}+mc${mcVersion}.jar")
}

tasks.jar {
	from("LICENSE") {
		rename { "${it}_${rootProject.name}" }
	}
}