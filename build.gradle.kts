import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.arenagod.gradle.MybatisGenerator") version "1.4"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
}

group = "com.kiyotakeshi"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.session:spring-session-data-redis")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("org.flywaydb:flyway-core")

	runtimeOnly("mysql:mysql-connector-java:8.0.23")
	implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.2.1")
	mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0")
	implementation("redis.clients:jedis")

	// testImplementation は src/test/kotin 配下のコードでのみ使用される依存関係
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
	testImplementation("org.assertj:assertj-core:3.19.0")
	testImplementation("org.mockito:mockito-core:3.8.0")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

mybatisGenerator {
	verbose = true
	configFile = "${projectDir}/src/main/resources/generatorConfig.xml"
}