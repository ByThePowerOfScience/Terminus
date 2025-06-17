import net.fabricmc.loom.api.LoomGradleExtensionAPI
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	id("dev.architectury.loom") version "1.10-SNAPSHOT" apply false
	id("architectury-plugin") version "3.4-SNAPSHOT"
	id("com.github.johnrengelman.shadow") version "8.1.1" apply false
	id("org.jetbrains.kotlin.jvm") version libs.versions.kotlin
}

architectury {
	minecraft = project.properties["minecraft_version"] as String
}

allprojects {
	group = rootProject.properties["maven_group"] as String
	version = rootProject.properties["mod_version"] as String
	
	repositories {
		maven {
			name = "ParchmentMC"
			url = uri("https://maven.parchmentmc.org")
		}
	}
}

subprojects {
	apply(plugin = "architectury-plugin")
//	apply(plugin="maven-publish")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "dev.architectury.loom")
	
	base {
		// Set up a suffixed format for the mod jar names, e.g. `example-fabric`.
		archivesName = "${rootProject.properties["archives_name"]}-${project.properties["name"]}"
	}
	
	repositories {
		// Add repositories to retrieve artifacts from in here.
		// You should only use this when depending on other mods because
		// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
		// See https://docs.gradle.org/current/userguide/declaring_repositories.html
		// for more information about repositories.
	}
	// 2023.09.03
	val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
	
	configure(listOf(loom)) {// idk why it only recognizes collections
		silentMojangMappingsLicense()
		this.runs {
			this["client"].run {
				property("fabric.log.level", "debug")
				vmArg("-XX:+AllowEnhancedClassRedefinition")
			}
			this["server"].run {
				property("fabric.log.level", "debug")
				vmArg("-XX:+AllowEnhancedClassRedefinition")
			}
		}
	}
	dependencies {
		"minecraft"("net.minecraft:minecraft:${rootProject.properties["minecraft_version"]}")
		"mappings"(loom.layered() {
			officialMojangMappings()
			parchment("org.parchmentmc.data:parchment-1.20.1:2023.09.03@zip")
		})
		implementation("org.kodein.di:kodein-di:7.25.0")
	}
	
	java {
		// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
		// if it is present.
		// If you remove this line, sources will not be generated.
		withSourcesJar()
		
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	
	tasks.withType<JavaCompile>().configureEach {
		options.release = 17
	}
	
	kotlin {
		compilerOptions {
			jvmTarget.set(JvmTarget.JVM_17)
			freeCompilerArgs.add("-Xcontext-parameters")
		}
	}
	
	
	// Configure Maven publishing.
//	publishing {
//		publications {
//			mavenJava(MavenPublication::class.java) {
//				artifactId = base.archivesName.get()
//				from(components.java)
//			}
//		}
//
//		// See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
//		repositories {
//			// Add repositories to publish to here.
//			// Notice: This block does NOT have the same function as the block in the top level.
//			// The repositories here will be used for publishing your artifact, not for
//			// retrieving dependencies.
//		}
//	}
}
