package com.spring.hateoas.learn.repository

import com.spring.hateoas.learn.domain.PersonOwner
import org.springframework.stereotype.Component

@Component
class PersonRepository {
    var listOfPersonOwners = mutableListOf<PersonOwner>()

    fun addPersonOwner(PersonOwner: PersonOwner) {
        listOfPersonOwners.add(PersonOwner)
    }

    fun getAllPersonOwners(): List<PersonOwner> {
        return listOfPersonOwners
    }
}
