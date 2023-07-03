package com.teko.local.features.user

import androidx.room.EmptyResultSetException
import com.teko.domain.User
import com.teko.local.features.user.dao.UserDao
import com.teko.local.features.user.model.UserDB
import com.teko.local.utils.OnErrorResumeNext
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
