package com.coroutines.coroutinesTutorial.controller

import com.coroutines.coroutinesTutorial.model.request.ProductFilterRequest
import com.coroutines.coroutinesTutorial.model.response.PersoanelResonse
import com.coroutines.coroutinesTutorial.model.response.ProductFilterResponse
import com.coroutines.coroutinesTutorial.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductController {

    @Autowired
    private val service = ProductService()

    @PostMapping("/filter")
    suspend fun getProductFilter(@RequestBody request: ProductFilterRequest): List<ProductFilterResponse> {
        return service.getProductFilter(request)
    }

}