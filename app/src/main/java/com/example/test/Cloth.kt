package com.example.test

import java.io.Serializable

class Cloth (val id: Int, val name: String, val description: String, val image: Int): Serializable {

    override fun toString(): String {
        return name
    }
}