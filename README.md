# Jet2Travel Test

Technology and Tool used

1. MVVM
2. Kotlin
3. Kotlin Coroutine
4. Retrofit
5. Paging Jetpack
6. Android studio


build.gradle

 def lifecycle_version = "2.2.0"
    // Androidx LiveData and ViewModel
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    implementation 'com.google.android.material:material:1.1.0'

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'

    //add paging library
    implementation 'android.arch.paging:runtime:1.0.1'

    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation "com.squareup.okhttp3:logging-interceptor:4.7.2"
    implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.11.0'
