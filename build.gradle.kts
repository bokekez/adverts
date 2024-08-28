plugins {
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	java
}

group = "codeVibe"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.mysql:mysql-connector-j")
	implementation("org.junit.jupiter:junit-jupiter:5.10.0")
	implementation("io.stargate.auth.jwt:auth-jwt-service:1.0.38")
	implementation("org.postgresql:postgresql:42.5.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("org.springframework.security:spring-security-core")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("javax.servlet:javax.servlet-api:4.0.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// plugins {
// 	java
// 	id("org.springframework.boot") version "3.3.3"
// 	id("io.spring.dependency-management") version "1.1.6"
// }

// group = "codeVibe"
// version = "0.0.1-SNAPSHOT"

// java {
// 	toolchain {
// 		languageVersion = JavaLanguageVersion.of(17)
// 	}
// }

// repositories {
// 	mavenCentral()
// }

// dependencies {
// 	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
// 	implementation("org.springframework.boot:spring-boot-starter-security")
// 	implementation("org.springframework.boot:spring-boot-starter-web")
// 	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
// 	implementation("org.springframework.boot:spring-boot-starter-mail")
// 	developmentOnly("org.springframework.boot:spring-boot-devtools")
// 	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
// 	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
// 	testImplementation("org.springframework.boot:spring-boot-starter-test")
// 	testImplementation("org.springframework.security:spring-security-test")
// 	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
// 	implementation("com.mysql:mysql-connector-j")
// 	implementation("org.junit.jupiter:junit-jupiter:5.10.0")
// 	implementation("io.stargate.auth.jwt:auth-jwt-service:1.0.38")
// 	implementation("org.postgresql:postgresql:42.5.0")
// 	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
// 	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
// 	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
// }

// tasks.withType<Test> {
// 	useJUnitPlatform()
// }
