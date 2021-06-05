package com.dima.financeapp.ui.main.communication

import com.dima.financeapp.model.domain.User

interface ProfileFragmentCommunication {
    fun setUser(user: User)
}