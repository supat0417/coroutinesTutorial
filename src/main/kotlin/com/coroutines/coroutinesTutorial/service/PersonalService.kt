package com.coroutines.coroutinesTutorial.service

import com.coroutines.coroutinesTutorial.model.PersonalModel
import com.coroutines.coroutinesTutorial.model.request.PersonalFilterRequest
import com.coroutines.coroutinesTutorial.model.response.Address
import com.coroutines.coroutinesTutorial.model.response.PersoanelResonse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Service
class PersonalService {

    @Autowired
    private val objectMapper = ObjectMapper()

    suspend fun getCurrentPersonal(id: Int): PersoanelResonse {
        val birthDate = LocalDate.of(1998, 2, 20)
        val age = ChronoUnit.YEARS.between(birthDate, LocalDate.now())
        return PersoanelResonse(id, "Supat", "Suanprasert", birthDate, age, Address("BKK", "10220"))
    }

    suspend fun getPersonal(request: PersonalFilterRequest): List<PersoanelResonse> {
        val file = ClassPathResource("static/mockDataPersonal.json").inputStream
        val dataFile: List<PersonalModel> = objectMapper.readValue(file)
        val dataAfterFilter: List<PersonalModel>
        val response = ArrayList<PersoanelResonse>()

        if (request.idType.equals("EvenNumbers")) {
            dataAfterFilter = dataFile.filter { user ->
                user.id >= request.pagingLimit
                user.id <= request.pagingOffset
                user.id % 2 == 0
            }
        } else if (request.idType.equals("OddNumber")) {
            dataAfterFilter = dataFile.filter { user ->
                user.id >= request.pagingLimit
                user.id <= request.pagingOffset
                user.id % 2 != 0
            }
        } else {
            dataAfterFilter = dataFile.filter { user ->
                user.id >= request.pagingLimit
                user.id <= request.pagingOffset
            }
        }

        dataAfterFilter.forEach { userFilter ->
            val birthDate: LocalDate = LocalDate.parse(userFilter.birthDate.toString())
            val age: Long = ChronoUnit.YEARS.between(birthDate, LocalDate.now())
            response.add(
                PersoanelResonse(
                    userFilter.id,
                    userFilter.firstName,
                    userFilter.lastName,
                    birthDate,
                    age,
                    Address(
                        userFilter.province,
                        userFilter.protalCode
                    )
                )
            )
        }
        return response
    }
}