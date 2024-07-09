package com.coroutines.coroutinesTutorial.service

import com.coroutines.coroutinesTutorial.model.PersonalModel
import com.coroutines.coroutinesTutorial.model.ProductHistoryModel
import com.coroutines.coroutinesTutorial.model.ProductModel
import com.coroutines.coroutinesTutorial.model.request.ProductFilterRequest
import com.coroutines.coroutinesTutorial.model.response.ProductFilterResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.netty.util.internal.StringUtil
import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.ServerResponse.async

@Service
class ProductService {

    @Autowired
    private val objectMapper = ObjectMapper()

    suspend fun getProductFilter(request: ProductFilterRequest): List<ProductFilterResponse> {
        val scope = CoroutineScope(Dispatchers.Default)

        val response = ArrayList<ProductFilterResponse>()

        val dataHistory = scope.async {
            getHistory()
        }

        val dataProduct = scope.async {
            getProduct()
        }


        dataHistory.await().forEach{ data ->
            println(data.productId)
            if(data.subProductId == null){
                response.add(ProductFilterResponse(
                    data.productId,
                    data.productType,
                    dataProduct.await().filter { product -> data.productId.equals(product.productId) }.get(0).productName,
                    null,
                    null,
                    null,
                    data.productExpr
                ))
            }else{
                response.add(ProductFilterResponse(
                    data.productId,
                    data.productType,
                    dataProduct.await().filter { product -> data.productId.equals(product.productId) }.get(0).productName,
                    data.subProductId,
                    null,
                    dataProduct.await().filter { subproduct -> data.subProductId!!.equals(subproduct.productId) }.get(0).productName,
                    data.productExpr
                ))
            }

        }

        return response;
    }

    suspend fun getHistory(): List<ProductHistoryModel> {
        println("find History")
        val fileDataHistory = ClassPathResource("static/mockProductHistory.json").inputStream
        val dataHistory: List<ProductHistoryModel> = objectMapper.readValue(fileDataHistory)
        delay(6000)
        return dataHistory;
    }

    suspend fun getProduct(): List<ProductModel> {
        println("find Product")
        val fileDataProduct = ClassPathResource("static/mockProductModel.json").inputStream
        val dataProduct: List<ProductModel> = objectMapper.readValue(fileDataProduct)
        delay(5000)
        return dataProduct
    }




}