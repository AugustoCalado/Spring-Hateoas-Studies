package com.spring.hateoas.learn.controller

import com.spring.hateoas.learn.domain.Dog
import com.spring.hateoas.learn.domain.resource.DogResource
import com.spring.hateoas.learn.domain.resource.assembler.DogResourceAssembler
import com.spring.hateoas.learn.repository.DogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/dog"])
class DogController {

    @Autowired
    lateinit var dogRepository: DogRepository

    @PostMapping(path = ["/add-dog"])
    fun addDog(@RequestBody(required = true) dog: Dog): ResponseEntity<Resource<DogResource>> {

        dog.let {
            dogRepository.addDog(it)
            val dogResource = DogResourceAssembler().toResource(it)
            val myReturn = Resource(dogResource)
            myReturn.add(
                    linkTo(methodOn(DogController::class.java).addDog(it)).withRel("add-dog")
            )
            return ResponseEntity(Resource(dogResource), HttpStatus.OK)
        }
    }

    @GetMapping(path = ["/get-all"])
    fun getAllDogs(): ResponseEntity<Resources<DogResource>> {
        var listDogueiras = DogResourceAssembler().toResources(dogRepository.listOfDogs)
        var listOfResourcesDogs = Resources<DogResource>(listDogueiras)

        listOfResourcesDogs.add(
                linkTo(methodOn(DogController::class.java).getAllDogs()).withRel("all-dogs")
        )
        return ResponseEntity(listOfResourcesDogs, HttpStatus.OK)
    }

    @GetMapping(path = ["/find/{id}"])
    fun findPerson(@PathVariable(name = "id", required = true) idDog: Long): ResponseEntity<Resource<DogResource>> {
        val result = dogRepository.listOfDogs.filter { dog -> dog.id.equals(idDog) }

        result.getOrNull(0)?.let {

            var dogResource = DogResourceAssembler().toResource(it)
            val resourceToReturn = Resource(dogResource)
            return ResponseEntity(resourceToReturn, HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)

    }
}