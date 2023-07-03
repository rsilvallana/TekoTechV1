package com.teko.local

import android.content.SharedPreferences
import com.teko.domain.AccessToken
import com.teko.local.features.token.AccessTokenLocalSource
import com.teko.local.features.token.AccessTokenLocalSourceImpl
import com.teko.local.features.token.AccessTokenLocalSourceImpl.Companion.PREF_USER_TOKEN
import com.teko.local.features.token.dao.TokenDao
import com.teko.local.features.token.models.AccessTokenDB
import io.mockk.*
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class AccessTokenLocalSourceTest {

    private lateinit var sut: AccessTokenLocalSource
    private val mockSharedPrefs = mockk<SharedPreferences>(relaxed = true, relaxUnitFun = true)
    private val mockTokenDao = mockk<TokenDao>()

    @Before
    fun setup() {
        sut = AccessTokenLocalSourceImpl(mockSharedPrefs, mockTokenDao)
    }

    @Test
    fun getAccessToken_ShouldCallFromDao() {
        val response = mockk<AccessTokenDB>()
        every {
            mockTokenDao.getToken()
        } returns Single.just(response)

        sut.getAccessToken()

        verify {
            mockTokenDao.getToken()
        }
    }

    @Test
    fun getAccessToken_ShouldCallDomainMapper() {
        val response = mockk<AccessTokenDB>()
        every {
            mockTokenDao.getToken()
        } returns Single.just(response)

        val accessToken = mockk<AccessToken>()
        mockkObject(AccessTokenDB.Companion)
        every {
            AccessTokenDB.Companion.toDomain(any())
        } answers { accessToken }

        sut.getAccessToken()
            .test()
            .assertComplete()

        verify {
            AccessTokenDB.toDomain(any())
        }
    }

    @Test
    fun getAccessTokenFromPrefs_ShouldCallFromPrefs() {
        every {
            mockSharedPrefs.getString(PREF_USER_TOKEN, "")
        } answers { "test" }

        sut.getAccessTokenFromPrefs()

        verify {
            mockSharedPrefs.getString(PREF_USER_TOKEN, "")
        }
    }

    @Test
    fun saveAccessToken_ShouldCallLogoutTokenFromDao() {
        every {
            mockTokenDao.logoutToken()
        } returns Completable.complete()

        every {
            mockTokenDao.saveToken(any())
        } returns Single.just(0L)

        sut.saveAccessToken(AccessToken(authCookie = "test"))

        verify {
            mockTokenDao.logoutToken()
        }
    }

    @Test
    fun saveAccessToken_ShouldCallSaveTokenFromDao() {
        every {
            mockTokenDao.logoutToken()
        } returns Completable.complete()

        every {
            mockTokenDao.saveToken(any())
        } returns Single.just(0L)

        sut.saveAccessToken(AccessToken(authCookie = "test"))

        verify {
            mockTokenDao.saveToken(any())
        }
    }

    @Test
    fun saveAccessToken_ShouldSaveToPrefs() {
        every {
            mockTokenDao.logoutToken()
        } returns Completable.complete()

        every {
            mockTokenDao.saveToken(any())
        } returns Single.just(0L)

        sut.saveAccessToken(AccessToken(authCookie = "test"))
            .test()
            .assertComplete()

        verify {
            mockSharedPrefs
                .edit()
                .apply {
                    putString(PREF_USER_TOKEN, any())
                    apply()
                }
        }
    }

    @Test
    fun deleteToken_ShouldCallFromDao() {
        every {
            mockTokenDao.logoutToken()
        } returns Completable.complete()

        sut.deleteToken()

        verify {
            mockTokenDao.logoutToken()
        }
    }

    @Test
    fun deleteToken_ShouldClearPrefs() {
        every {
            mockTokenDao.logoutToken()
        } returns Completable.complete()

        sut.deleteToken()

        verify {
            mockSharedPrefs
                .edit()
                .apply {
                    putString(PREF_USER_TOKEN, any())
                    apply()
                }
        }
    }
}
