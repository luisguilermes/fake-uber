group = "fake.uber.libs.avro_schemas"
version = "0.0.1"

plugins {
    alias(libs.plugins.avro)
}

dependencies {
    implementation(libs.avro.core)
    implementation(libs.cofluent.avro.serializer)
}

avro {
    isCreateSetters = true // Enables the generation of setter methods in generated classes
}
