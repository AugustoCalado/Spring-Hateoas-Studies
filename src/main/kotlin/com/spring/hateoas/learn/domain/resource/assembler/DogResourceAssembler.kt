package com.spring.hateoas.learn.domain.resource.assembler

import com.spring.hateoas.learn.controller.DogController
import com.spring.hateoas.learn.domain.Dog
import com.spring.hateoas.learn.domain.resource.DogResource
import org.springframework.hateoas.mvc.ResourceAssemblerSupport

class DogResourceAssembler : ResourceAssemblerSupport<Dog, DogResource> {

    constructor() : super(DogController::class.java, DogResource::class.java)


    override fun instantiateResource(entity: Dog): DogResource {
        return DogResource(entity)
    }

    override fun toResource(p0: Dog): DogResource {
        return createResourceWithId(p0.id, p0)
    }

}