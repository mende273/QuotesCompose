package com.jumrukovski.quotescompose.domain.repository

import com.google.common.truth.Truth.assertThat
import com.jumrukovski.quotescompose.data.repository.remote.RemoteRepositoryImpl
import com.jumrukovski.quotescompose.data.source.remote.RemoteDataSource
import com.jumrukovski.quotescompose.domain.model.Quote
import com.jumrukovski.quotescompose.domain.repository.remote.RemoteRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.HttpURLConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteRepositoryImplTest {

    private lateinit var remoteRepository: RemoteRepository
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var mockWebServer: MockWebServer

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val mockApiService = MockApiService(mockWebServer.url("/").toString())

        remoteDataSource = mockApiService.get()
        remoteRepository = RemoteRepositoryImpl(remoteDataSource, testDispatcher)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getQuotes() =
        runTest(StandardTestDispatcher()) {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MOCK_RESPONSE_GET_QUOTES)
            )

            val actualResponse = remoteRepository.getQuotes()
            assertThat(actualResponse.isSuccess).isTrue()
            assertThat(actualResponse.getOrNull()).hasSize(10)
        }

    @Test
    fun getRandomQuote() =
        runTest(StandardTestDispatcher()) {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MOCK_RESPONSE_GET_RANDOM_QUOTE)
            )

            val expectedResult = Quote(
                id = 1,
                content = "The joy of life comes from our encounters with new experiences.",
                author = "Christopher McCandless"
            )

            val actualResponse = remoteRepository.getRandomQuote()
            assertThat(actualResponse.isSuccess).isTrue()
            assertThat(actualResponse.getOrNull()?.author).isEqualTo(expectedResult.author)
            assertThat(actualResponse.getOrNull()?.content).isEqualTo(expectedResult.content)
        }
}

private class MockApiService(url: String) {
    fun get(): RemoteDataSource = remoteDataSource

    private val okHttpClient = OkHttpClient.Builder()

    private val remoteDataSource = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .client(okHttpClient.build())
        .build()
        .create(RemoteDataSource::class.java)
}

private const val MOCK_RESPONSE_GET_QUOTES = """
    [{"q":"The sweetest pleasure arises from difficulties overcome.","a":"Publilius Syrus","c":"56","h":"<blockquote>&ldquo;The sweetest pleasure arises from difficulties overcome.&rdquo; &mdash; <footer>Publilius Syrus</footer></blockquote>"}
    ,{"q":"Do not fight with pigs - you will be smeared in mud but the pig will like it.","a":"George Bernard Shaw","c":"77","h":"<blockquote>&ldquo;Do not fight with pigs - you will be smeared in mud but the pig will like it.&rdquo; &mdash; <footer>George Bernard Shaw</footer></blockquote>"}
    ,{"q":"It is during our darkest moments that we must focus to see the light.","a":"Aristotle","c":"69","h":"<blockquote>&ldquo;It is during our darkest moments that we must focus to see the light.&rdquo; &mdash; <footer>Aristotle</footer></blockquote>"}
    ,{"q":"If you've made a mistake, it's better just to laugh at it.","a":"Zen Proverb","c":"58","h":"<blockquote>&ldquo;If you've made a mistake, it's better just to laugh at it.&rdquo; &mdash; <footer>Zen Proverb</footer></blockquote>"}
    ,{"q":"We win by helping each other win.","a":"Jack Butcher","c":"33","h":"<blockquote>&ldquo;We win by helping each other win.&rdquo; &mdash; <footer>Jack Butcher</footer></blockquote>"}
    ,{"q":"Don't change your mind just because people are offended; change your mind if you're wrong.","a":"Criss Jami","c":"90","h":"<blockquote>&ldquo;Don't change your mind just because people are offended; change your mind if you're wrong.&rdquo; &mdash; <footer>Criss Jami</footer></blockquote>"}
    ,{"q":"Why do you stay in prison when the door is so wide open?","a":"Rumi","c":"56","h":"<blockquote>&ldquo;Why do you stay in prison when the door is so wide open?&rdquo; &mdash; <footer>Rumi</footer></blockquote>"}
    ,{"q":"You must live in the present, launch yourself on every wave, find your eternity in each moment. Fools stand on their island of opportunities and look toward another land. There is no other land; there is no other life but this.","a":"Henry David Thoreau","c":"227","h":"<blockquote>&ldquo;You must live in the present, launch yourself on every wave, find your eternity in each moment. Fools stand on their island of opportunities and look toward another land. There is no other land; there is no other life but this.&rdquo; &mdash; <footer>Henry David Thoreau</footer></blockquote>"}
    ,{"q":"How you think when you lose determines how long it will be until you win.","a":"Gilbert Chesterton","c":"73","h":"<blockquote>&ldquo;How you think when you lose determines how long it will be until you win.&rdquo; &mdash; <footer>Gilbert Chesterton</footer></blockquote>"}
    ,{"q":"Expect the best of yourself, and then do what is necessary to make it a reality.","a":"Ralph Marston","c":"80","h":"<blockquote>&ldquo;Expect the best of yourself, and then do what is necessary to make it a reality.&rdquo; &mdash; <footer>Ralph Marston</footer></blockquote>"}]"""

private const val MOCK_RESPONSE_GET_RANDOM_QUOTE = """
[ {"q":"The joy of life comes from our encounters with new experiences.","a":"Christopher McCandless","h":"<blockquote>&ldquo;The joy of life comes from our encounters with new experiences.&rdquo; &mdash; <footer>Christopher McCandless</footer></blockquote>"} ]
"""
