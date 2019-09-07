package com.spring.hateoas.learn.domain.resource

import com.spring.hateoas.learn.domain.PersonOwner
import com.spring.hateoas.learn.domain.resource.assembler.DogResourceAssembler
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.core.Relation
import javax.annotation.Resource

@Relation(value = "PersonOwner", collectionRelation = "PeopleOwners")
class PersonOwnerResource(
        val name: String
) : ResourceSupport() {

    constructor(p: PersonOwner) : this(
            p.name
    )

}