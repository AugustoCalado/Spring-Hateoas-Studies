package com.spring.hateoas.learn.domain.resource

import com.spring.hateoas.learn.domain.PersonOwner
import com.spring.hateoas.learn.domain.resource.assembler.DogResourceAssembler
import org.springframework.hateoas.ResourceSupport

class PersonOwnerResource(
        val name: String
) : ResourceSupport() {

    constructor(p: PersonOwner) : this(
            p.name
    )

}