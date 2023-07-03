package com.teko.tekotechv1.utils

import org.mockito.ArgumentCaptor

inline fun <reified T> argumentCaptor(): ArgumentCaptor<T> = ArgumentCaptor.forClass(T::class.java)
