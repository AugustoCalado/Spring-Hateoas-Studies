package com.spring.hateoas.learn.domain.resource.assembler

import com.spring.hateoas.learn.controller.PersonOwnerController
import com.spring.hateoas.learn.domain.PersonOwner
import com.spring.hateoas.learn.domain.resource.PersonOwnerResource
import org.springframework.hateoas.mvc.ResourceAssemblerSupport

class PersonOwnerResourceAssembler : ResourceAssemblerSupport<PersonOwner, PersonOwnerResource> {

    constructor() : super(PersonOwnerController::class.java, PersonOwnerResource::class.java)


    override fun instantiateResource(entity: PersonOwner): PersonOwnerResource {
        return PersonOwnerResource(entity)
    }

    override fun toResource(p0: PersonOwner): PersonOwnerResource {
        return createResourceWithId(p0.id, p0)
    }


}