package com.hao.composedemo01.data

import java.io.Serializable

/**
 * 用户实体类
 */
data class UserInfoData(val id: Int, val name: String, val password: String) : Serializable
