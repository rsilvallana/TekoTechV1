object Libs {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val supportDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"
    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayoutVersion}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"

    // Navigation Components
    const val navigationFragments = "androidx.navigation:navigation-fragment:${Versions.navigationVersion}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationFragmentUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    // Dagger Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofitScalar = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitScalarVersion}"

    // OkHttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingVersion}"

    // Chucker
    const val chuckerDebug = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val chuckerRelease = "com.github.chuckerteam.chucker:library-no-op:${Versions.chucker}"

    // Room
    const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomRx = "androidx.room:room-rxjava2:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // SQLCipher
    const val sqlcipher = "net.zetetic:android-database-sqlcipher:${Versions.sqlChipherVersion}"
    const val sqlite = "androidx.sqlite:sqlite:${Versions.sqliteVersion}"

    // Security Crypto
    const val securityCrypto = "androidx.security:security-crypto:${Versions.securityCryptoVersion}"

    // PreferenceManager
    val preference = "androidx.preference:preference-ktx:${Versions.preferenceManagerVersion}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"

    // RxJava
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2Version}"
    const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlinVersion}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
}