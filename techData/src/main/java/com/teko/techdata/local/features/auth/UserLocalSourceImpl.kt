package com.teko.techdata.local.features.auth

import androidx.room.EmptyResultSetException
import com.teko.commondata.local.OnErrorResumeNext
import com.teko.techdata.remote.features.auth.domain.User
import com.teko.techdata.local.features.auth.dao.UserDao
import com.teko.techdata.local.features.auth.domain.UserDB
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class UserLocalSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalSource {

    override fun saveUser(user: User): Single<User> {
        return userDao
            .deleteUsers()
            .andThen(userDao.saveUser(UserDB.fromDomain(user)))
            .map {
                user
            }
    }

    override fun getUser(): Single<User> {
        return userDao
            .getUser()
            .compose(
                OnErrorResumeNext<UserDB, EmptyResultSetException>(
                    UserDB.empty(),
                    EmptyResultSetException::class.java
                )
            )
            .map {
                UserDB.toDomain(it)
            }
    }

    override fun deleteUser(): Completable {
        return userDao.deleteUsers()
    }
}
