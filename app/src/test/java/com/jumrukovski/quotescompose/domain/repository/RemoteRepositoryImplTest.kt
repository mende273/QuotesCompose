package com.jumrukovski.quotescompose.domain.repository

import com.google.common.truth.Truth.assertThat
import com.jumrukovski.quotescompose.data.network.ApiService
import com.jumrukovski.quotescompose.domain.model.Quote
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.net.HttpURLConnection
import kotlinx.coroutines.test.StandardTestDispatcher
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

    private lateinit var remoteRepository: RemoteRepositoryImpl
    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val mockApiService = MockApiService(mockWebServer.url("/").toString())

        apiService = mockApiService.get()
        remoteRepository = RemoteRepositoryImpl(apiService)
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
            assertThat(actualResponse.getOrNull()).hasSize(20)
        }

    @Test
    fun getAllTags() =
        runTest(StandardTestDispatcher()) {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MOCK_RESPONSE_GET_ALL_TAGS)
            )

            val actualResponse = remoteRepository.getAllTags()
            assertThat(actualResponse.isSuccess).isTrue()
            assertThat(actualResponse.getOrNull()).hasSize(67)
        }

    @Test
    fun getQuotesForTag() =
        runTest(StandardTestDispatcher()) {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MOCK_RESPONSE_GET_QUOTES_FOR_TAG)
            )

            val actualResponse = remoteRepository.getQuotesForTag("age")
            assertThat(actualResponse.isSuccess).isTrue()
            assertThat(actualResponse.getOrNull()).hasSize(1)
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
                id = "e0H2BdwC-Of",
                content = "Whatever is not yours: let go of it. " +
                    "Your letting go of it will be for your " +
                    "long-term happiness & benefit.",
                author = "The Buddha"
            )

            val actualResponse = remoteRepository.getRandomQuote()
            assertThat(actualResponse.isSuccess).isTrue()
            assertThat(actualResponse.getOrNull()).isEqualTo(expectedResult)
        }
}

private class MockApiService(url: String) {
    fun get(): ApiService = apiService

    private val okHttpClient = OkHttpClient.Builder()

    private val apiService = Retrofit.Builder()
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
        .create(ApiService::class.java)
}

private const val MOCK_RESPONSE_GET_QUOTES = """
    {"count":20,"totalCount":2127,"page":1,"totalPages":107,"lastItemIndex":20,"results":[{"_id":"bfNpGC2NI","author":"Thomas Edison","content":"As a cure for worrying, work is better than whisky.","tags":["Humorous"],"authorSlug":"thomas-edison","length":51,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"ghVnmSpeAo","author":"Thomas Edison","content":"Everything comes to him who hustles while he waits.","tags":["Success","Motivational"],"authorSlug":"thomas-edison","length":51,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"_92j6kAvwd","author":"Thomas Edison","content":"I never did a day's work in my life.  It was all fun.","tags":["Humorous"],"authorSlug":"thomas-edison","length":53,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"RTYhCs5Ka","author":"Charles Dickens","content":"I do not know the American gentleman, god forgive me for putting two such words together.","tags":["Humorous"],"authorSlug":"charles-dickens","length":89,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"RKl9iZrjfP","author":"Charles Dickens","content":"We need never be ashamed of our tears.","tags":["Sadness"],"authorSlug":"charles-dickens","length":38,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"_EC8bhSDQa","author":"Charles Dickens","content":"Minds, like bodies, will often fall into a pimpled, ill-conditioned state from mere excess of comfort.","tags":["Weakness"],"authorSlug":"charles-dickens","length":102,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"j7W6pP1XiG","author":"Charles Dickens","content":"Train up a fig tree in the way it should go, and when you are old sit under the shade of it.","tags":["Age","Wisdom"],"authorSlug":"charles-dickens","length":92,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"qF9iB6wrCQ","author":"Charles Dickens","content":"Subdue your appetites, my dears, and you've conquered human nature.","tags":[],"authorSlug":"charles-dickens","length":67,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"btV2j1uf2t","author":"Thomas Edison","content":"Faith, as well intentioned as it may be, must be built on facts, not fiction--faith in fiction is a damnable false hope.","tags":["Religion"],"authorSlug":"thomas-edison","length":120,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"QfeIDluRi_","author":"Thomas Edison","content":"There is no expedient to which a man will not go to avoid the labor of thinking.","tags":["Work"],"authorSlug":"thomas-edison","length":80,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"as0ElEk5g7","author":"Charles Dickens","content":"No one is useless in this world who lightens the burdens of another.","tags":["Generosity"],"authorSlug":"charles-dickens","length":68,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"jQHjwwHpLN","author":"Thomas Edison","content":"Results! Why, man, I have gotten a lot of results. I know several thousand things that won't work.","tags":["Success","Failure","Perseverance"],"authorSlug":"thomas-edison","length":98,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"R1DmP1kYtW","author":"Charles Dickens","content":"Reflect on your present blessings, of which every man has many; not on your past misfortunes, of which all men have some.","tags":["Gratitude"],"authorSlug":"charles-dickens","length":121,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"niVz2fQWSH","author":"Thomas Edison","content":"Opportunity is missed by most people because it is dressed in overalls and looks like work.","tags":["Opportunity","Work"],"authorSlug":"thomas-edison","length":91,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"ijc0sPlYFd","author":"Thomas Edison","content":"The doctor of the future will give no medicine, but will interest her or his patients in the care of the human frame, in a proper diet, and in the cause and prevention of disease.","tags":["Health","Wellness"],"authorSlug":"thomas-edison","length":179,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"BzfxvRlA2Y","author":"Thomas Edison","content":"We don't know a millionth of one percent about anything.","tags":["Knowledge"],"authorSlug":"thomas-edison","length":56,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"XtZMaD2s28","author":"Thomas Edison","content":"If we all did the things we are capable of doing, we would literally astound ourselves.","tags":["Inspirational","Motivational"],"authorSlug":"thomas-edison","length":87,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"KMTJ0Ya3e9","author":"Thomas Edison","content":"To invent, you need a good imagination and a pile of junk.","tags":["Imagination","Creativity","Science","Technology"],"authorSlug":"thomas-edison","length":58,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"bZIw7gy0sR","author":"Thomas Edison","content":"I never did anything worth doing by accident, nor did any of my inventions come by accident","tags":["Success","Science"],"authorSlug":"thomas-edison","length":91,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"An5NAXPrbN","author":"Thomas Edison","content":"Hell, there are no rules here-- we're trying to accomplish something.","tags":[],"authorSlug":"thomas-edison","length":69,"dateAdded":"2023-04-14","dateModified":"2023-04-14"}]}
"""

private const val MOCK_RESPONSE_GET_ALL_TAGS = """
[{"_id":"PIyOMHYqPd","name":"Age","slug":"age","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"qTFouZDPBZz","name":"Athletics","slug":"athletics","quoteCount":0,"dateAdded":"2022-07-06","dateModified":"2023-04-14"},{"_id":"M83oc3scg","name":"Business","slug":"business","quoteCount":31,"dateAdded":"2019-08-03","dateModified":"2023-04-14"},{"_id":"QmvdN2qkQCC","name":"Change","slug":"change","quoteCount":34,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"vWfmIQt6k8c","name":"Character","slug":"character","quoteCount":17,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"JaQwywHSk59","name":"Competition","slug":"competition","quoteCount":38,"dateAdded":"2022-07-06","dateModified":"2023-04-14"},{"_id":"AjxQFSPEylb","name":"Conservative","slug":"conservative","quoteCount":1,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"Ipw2-EcInZg","name":"Courage","slug":"courage","quoteCount":2,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"aLWQOIYpeMz","name":"Creativity","slug":"creativity","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"kqzFRe-4V4","name":"Education","slug":"education","quoteCount":7,"dateAdded":"2019-03-17","dateModified":"2023-04-14"},{"_id":"jl_H9UVXhGb","name":"Ethics","slug":"ethics","quoteCount":1,"dateAdded":"2023-04-06","dateModified":"2023-04-14"},{"_id":"gElqvJIRz0h","name":"Failure","slug":"failure","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"-7GEUrC5r","name":"Faith","slug":"faith","quoteCount":4,"dateAdded":"2019-09-13","dateModified":"2023-04-14"},{"_id":"r437PEqVZx5","name":"Family","slug":"family","quoteCount":2,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"fvpORe-t","name":"Famous Quotes","slug":"famous-quotes","quoteCount":1090,"dateAdded":"2019-07-23","dateModified":"2023-04-14"},{"_id":"HJ05xaA6gN","name":"Film","slug":"film","quoteCount":13,"dateAdded":"2022-07-04","dateModified":"2023-04-14"},{"_id":"QuBdKRcjNsO","name":"Freedom","slug":"freedom","quoteCount":9,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"krXU-q4FE","name":"Friendship","slug":"friendship","quoteCount":189,"dateAdded":"2019-03-15","dateModified":"2023-04-14"},{"_id":"OMnUd1CUg","name":"Future","slug":"future","quoteCount":21,"dateAdded":"2019-02-17","dateModified":"2023-04-14"},{"_id":"i_OFrgCiQ4","name":"Generosity","slug":"generosity","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"L09SJrfjY8s","name":"Genius","slug":"genius","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"S9OYJZlyu5","name":"Gratitude","slug":"gratitude","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"k97A51Uf5","name":"Happiness","slug":"happiness","quoteCount":18,"dateAdded":"2019-06-27","dateModified":"2023-04-14"},{"_id":"kv9zk8WIqaq","name":"Health","slug":"health","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"KBWnDu4rH","name":"History","slug":"history","quoteCount":18,"dateAdded":"2021-01-30","dateModified":"2023-04-14"},{"_id":"uCLiEwnwh","name":"Honor","slug":"honor","quoteCount":6,"dateAdded":"2022-07-04","dateModified":"2023-04-14"},{"_id":"uki-krBKTB_","name":"Humor","slug":"humor","quoteCount":0,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"HJp_e1usX","name":"Humorous","slug":"humorous","quoteCount":17,"dateAdded":"2022-07-08","dateModified":"2023-04-14"},{"_id":"eghR-r-OzL9","name":"Imagination","slug":"imagination","quoteCount":2,"dateAdded":"2023-04-06","dateModified":"2023-04-14"},{"_id":"JCMoLDds9","name":"Inspirational","slug":"inspirational","quoteCount":89,"dateAdded":"2020-01-27","dateModified":"2023-04-14"},{"_id":"v9QUFHDZPT3","name":"Knowledge","slug":"knowledge","quoteCount":3,"dateAdded":"2023-04-06","dateModified":"2023-04-14"},{"_id":"V60a195td","name":"Leadership","slug":"leadership","quoteCount":1,"dateAdded":"2022-07-04","dateModified":"2023-04-14"},{"_id":"poT-7QEBm","name":"Life","slug":"life","quoteCount":42,"dateAdded":"2020-09-09","dateModified":"2023-04-14"},{"_id":"3n-gucf_OB","name":"Literature","slug":"literature","quoteCount":1,"dateAdded":"2020-03-01","dateModified":"2023-04-14"},{"_id":"rnrd8q9X1","name":"Love","slug":"love","quoteCount":20,"dateAdded":"2020-02-27","dateModified":"2023-04-14"},{"_id":"s19tg5r8EM-","name":"Mathematics","slug":"mathematics","quoteCount":1,"dateAdded":"2023-04-06","dateModified":"2023-04-14"},{"_id":"CaE-vzeOZb","name":"Motivational","slug":"motivational","quoteCount":32,"dateAdded":"2022-07-04","dateModified":"2023-04-14"},{"_id":"vmVZQ72P_","name":"Nature","slug":"nature","quoteCount":2,"dateAdded":"2020-12-10","dateModified":"2023-04-14"},{"_id":"pnLPcXTs_S","name":"Opportunity","slug":"opportunity","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"q8kOLaefsZM","name":"Pain","slug":"pain","quoteCount":1,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"GaQEsvfbYYd","name":"Perseverance","slug":"perseverance","quoteCount":2,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"mh6HEhK_T_a","name":"Philosophy","slug":"philosophy","quoteCount":16,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"96NNdxeI_","name":"Politics","slug":"politics","quoteCount":15,"dateAdded":"2020-04-02","dateModified":"2023-04-14"},{"_id":"MbsuUl67N3","name":"Power Quotes","slug":"power-quotes","quoteCount":2,"dateAdded":"2022-07-04","dateModified":"2023-04-14"},{"_id":"p5xRCWCdU","name":"Proverb","slug":"proverb","quoteCount":0,"dateAdded":"2021-02-12","dateModified":"2023-04-14"},{"_id":"tWLCsyf_K","name":"Religion","slug":"religion","quoteCount":6,"dateAdded":"2020-01-12","dateModified":"2023-04-14"},{"_id":"dm15bka7Qc","name":"Sadness","slug":"sadness","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"qO4zwIUdFW","name":"Science","slug":"science","quoteCount":14,"dateAdded":"2020-01-26","dateModified":"2023-04-14"},{"_id":"5wvH2mbETdq","name":"Self","slug":"self","quoteCount":6,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"IJsMcEupo4","name":"Self Help","slug":"self-help","quoteCount":6,"dateAdded":"2022-07-04","dateModified":"2023-04-14"},{"_id":"-WCNo8uFORU","name":"Social Justice","slug":"social-justice","quoteCount":3,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"eD6qAIcDR8s","name":"Society","slug":"society","quoteCount":1,"dateAdded":"2023-04-06","dateModified":"2023-04-14"},{"_id":"DNZ4IdtHiFG","name":"Spirituality","slug":"spirituality","quoteCount":4,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"EKV8W1TN-wb","name":"Sports","slug":"sports","quoteCount":43,"dateAdded":"2022-07-06","dateModified":"2023-04-14"},{"_id":"kZei477Cojv","name":"Stupidity","slug":"stupidity","quoteCount":1,"dateAdded":"2023-04-06","dateModified":"2023-04-14"},{"_id":"wm1HFcO8vf","name":"Success","slug":"success","quoteCount":82,"dateAdded":"2020-10-14","dateModified":"2023-04-14"},{"_id":"Gq75KBrfb","name":"Technology","slug":"technology","quoteCount":50,"dateAdded":"2020-01-27","dateModified":"2023-04-14"},{"_id":"LzQ9iXOoZw_","name":"Time","slug":"time","quoteCount":1,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"o7BP9_4e2lL","name":"Tolerance","slug":"tolerance","quoteCount":1,"dateAdded":"2023-04-06","dateModified":"2023-04-14"},{"_id":"B1O_IThWjSP","name":"Truth","slug":"truth","quoteCount":7,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"bsT8Bb9sxB","name":"Virtue","slug":"virtue","quoteCount":10,"dateAdded":"2022-07-04","dateModified":"2023-04-14"},{"_id":"olEL606Ju49","name":"War","slug":"war","quoteCount":3,"dateAdded":"2022-03-12","dateModified":"2023-04-14"},{"_id":"AN2qILFNzW","name":"Weakness","slug":"weakness","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"5j5s-YkHAr_","name":"Wellness","slug":"wellness","quoteCount":1,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"6J1qxxuj3","name":"Wisdom","slug":"wisdom","quoteCount":550,"dateAdded":"2019-10-18","dateModified":"2023-04-14"},{"_id":"Y3mg6WH7Qv1","name":"Work","slug":"work","quoteCount":3,"dateAdded":"2023-04-14","dateModified":"2023-04-14"},{"_id":"NLC25zc7-m5","name":"Work","slug":"work","quoteCount":3,"dateAdded":"2023-04-14","dateModified":"2023-04-14"}]
"""

private const val MOCK_RESPONSE_GET_QUOTES_FOR_TAG = """
{"count":1,"totalCount":1,"page":1,"totalPages":1,"lastItemIndex":null,"results":[{"_id":"j7W6pP1XiG","author":"Charles Dickens","content":"Train up a fig tree in the way it should go, and when you are old sit under the shade of it.","tags":["Age","Wisdom"],"authorSlug":"charles-dickens","length":92,"dateAdded":"2023-04-14","dateModified":"2023-04-14"}]}
"""

private const val MOCK_RESPONSE_GET_RANDOM_QUOTE = """
{"_id":"e0H2BdwC-Of","author":"The Buddha","content":"Whatever is not yours: let go of it. Your letting go of it will be for your long-term happiness & benefit.","tags":["Wisdom","Happiness"],"authorSlug":"the-buddha","length":106,"dateAdded":"2023-03-30","dateModified":"2023-04-14"}
"""
