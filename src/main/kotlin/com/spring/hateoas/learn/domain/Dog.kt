package com.spring.hateoas.learn.domain

class Dog(
        val id: Long,
        val name: String,
        val owner: PersonOwner
) {
}