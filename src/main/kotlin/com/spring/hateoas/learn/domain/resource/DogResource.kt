package com.spring.hateoas.learn.domain.resource

import com.spring.hateoas.learn.domain.Dog
import com.spring.hateoas.learn.domain.PersonOwner
import com.spring.hateoas.learn.domain.resource.assembler.DogResourceAssembler
import com.spring.hateoas.learn.domain.resource.assembler.PersonOwnerResourceAssembler
import org.springframework.hateoas.ResourceSupport

class DogResource(
        val name: String,
        val owner: PersonOwnerResource
) : ResourceSupport() {

    constructor(entity: Dog) : this(
            entity.name,
            personOwnerAssembler.toResource(entity.owner)
    )


    companion object {
        val personOwnerAssembler = PersonOwnerResourceAssembler()

    }

}