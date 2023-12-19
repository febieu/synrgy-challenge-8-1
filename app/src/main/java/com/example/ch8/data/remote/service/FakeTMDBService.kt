package com.example.ch8.data.remote.service

//
// class FakeTMDBService : SpoonacularService {
//    override suspend fun fetchMovies(apiKey: String, withOriginalLanguage: String): Response {
//        return Response(page = 1, results = emptyList(), totalPages = 1, totalResults = 0)
//    }
//
//    override suspend fun editProfile(
//        token: String,
//        editProfileBody: EditProfileBody
//    ): EditProfileBody {
//        delay(1000)
//        if (token.isEmpty()) {
//            throw HttpException(
//                retrofit2.Response.error<EditProfileBody>(
//                    403,
//                    ResponseBody.Companion.create("application/json".toMediaTypeOrNull(), "{\"error\": \"unauthentication\""),
//                )
//            )
//        } else {
//            return editProfileBody
//        }
//    }
// }
