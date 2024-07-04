package com.coroutines.coroutinesTutorial.controller

import com.coroutines.coroutinesTutorial.model.request.PersonalFilterRequest
import com.coroutines.coroutinesTutorial.model.response.PersoanelResonse
import com.coroutines.coroutinesTutorial.service.PersonalService
import lombok.extern.log4j.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/personal")
@Log4j
class PersonalController {

    @Autowired
    private val service = PersonalService()

    @GetMapping("/user")
    suspend fun getCurrentPersonal(@RequestParam(required = false) id: Int): PersoanelResonse {
        return service.getCurrentPersonal(id)
    }

    @PostMapping("/user/filter")
    suspend fun getPersonal(@RequestBody request: PersonalFilterRequest): List<PersoanelResonse> {
        return service.getPersonal(request)
    }
}