package com.spring.hateoas.learn.repository

import com.spring.hateoas.learn.domain.Dog
import org.springframework.stereotype.Component

@Component
class DogRepository() {

    var listOfDogs = mutableListOf<Dog>()

    fun addDog(dog: Dog) {
        listOfDogs.add(dog)
    }

    fun getAllDogs(): List<Dog> {
        return listOfDogs
    }

}