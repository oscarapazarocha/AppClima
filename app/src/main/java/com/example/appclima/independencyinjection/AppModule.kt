package com.example.appclima.independencyinjection

//@Module
//@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideIApiService(retrofit: Retrofit):IApiService{
        return retrofit.create(IApiService::class.java)
    }*/
}