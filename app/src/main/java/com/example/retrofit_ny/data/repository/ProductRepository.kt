package com.example.retrofit_ny.data.repository

import coil.network.HttpException
import com.example.retrofit_ny.data.ProductsApi
import com.example.retrofit_ny.data.model.Product
import com.example.retrofit_ny.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

interface ProductRepository {
    suspend fun getProducts(): Flow<Resource<List<Product>>>
}

class ProductRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
) : ProductRepository {
    override suspend fun getProducts(): Flow<Resource<List<Product>>> {
        return flow {
            val productsFromApi = try {
                productsApi.getProducts().products
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Products"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Products"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error Loading Products"))
                return@flow
            }
            emit(Resource.Success(data = productsFromApi))
        }
    }
}

//return productsApi.getProducts().products
//Type mismatch.
//Required:
//Flow<Resource<List<Product>>>
//Found:
//List<Product>