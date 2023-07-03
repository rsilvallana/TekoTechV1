package com.teko.local

import com.teko.domain.User
import com.teko.local.features.user.UserLocalSource
import com.teko.local.features.user.UserLocalSourceImpl
import com.teko.local.features.user.dao.UserDao
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class UserLocalSourceTest {

    private lateinit var sut: UserLocalSource
    private val mockDao = mockk<UserDao>()

    @Before
    fun setup() {
        sut = UserLocalSourceImpl(mockDao)
    }

    @Test
    fun saveUser_ShouldCallFromDao() {
        every {
            mockDao.deleteUsers()
        } returns Completable.complete()

        every {
            mockDao.saveUser(any())
        } returns Single.just(0L)

        val mockUser = mockk<User>(relaxed = true)
        sut.saveUser(mockUser)

        verify {
            mockDao.saveUser(any())
        }
    }
}
