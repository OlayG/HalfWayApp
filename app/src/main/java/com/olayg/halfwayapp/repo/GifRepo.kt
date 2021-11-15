package com.olayg.halfwayapp.repo

import com.olayg.halfwayapp.repo.remote.RetrofitInstanceGif

object GifRepo {

    suspend fun getImages(
        path: String
    ) = RetrofitInstanceGif.gifService.getGifs(path)
}