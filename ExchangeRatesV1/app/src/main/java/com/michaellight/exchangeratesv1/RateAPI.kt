package com.michaellight.exchangeratesv1

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RateAPI {
	@GET("rates")
	suspend fun getRates(
		@Query("periodicity")
		periodicity: Int = 0
	) : Response<List<Rate>>
}