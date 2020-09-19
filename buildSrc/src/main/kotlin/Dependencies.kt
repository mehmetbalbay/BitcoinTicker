object Dependencies {

    // Kotlin Libs
    val kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    val core_core_ktx = "androidx.core:core-ktx:${Versions.core_core_ktx}"

    // Android Libs
    val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val card_view = "androidx.cardview:cardview:${Versions.card_view}"

    // Dagger
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val dagger_android_compiler = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // Glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Room
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_copiler = "androidx.room:room-compiler:${Versions.room}"

    // Rest Libs
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    // Coroutines
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val coroutineAndroidTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"


    // ViewModel LiveData
    val arch_lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    val arch_lifecycle_livedata_ktx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"

    // Logging
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // Shared Pref
    val hawk = "com.orhanobut:hawk:${Versions.hawk}"

    // RX JAVA ANDROID
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    // MultiDex
    val multidex = "androidx.multidex:multidex:${Versions.multidex}"

    // Material Design Lib
    var android_material = "com.google.android.material:material:${Versions.android_material}"

    // MPChart
    var mpChart = "com.github.PhilJay:MPAndroidChart:v${Versions.mpChart}"

    // Navigation Component
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"

    // Firebase
    val fireBaseAuth = "com.google.firebase:firebase-auth:${Versions.fireBase}"

    // Fast And Effective BaseAdapter
    val baseadapter = "com.github.skydoves:baserecyclerviewadapter:${Versions.baseAdapter}"

    // Test IMPL
    val junit = "junit:junit:${Versions.junit}"
    val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"

}