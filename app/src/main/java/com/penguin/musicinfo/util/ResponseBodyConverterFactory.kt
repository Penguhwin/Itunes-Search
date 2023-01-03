package com.penguin.musicinfo.util

import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ResponseBodyConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        // Check if the type is okhttp3.ResponseBody
        if (type == ResponseBody::class.java) {
            return ResponseBodyConverter()
        }

        // Otherwise, return null to indicate that no converter is available
        return null
    }
}

class ResponseBodyConverter : Converter<ResponseBody, ResponseBody> {
    override fun convert(value: ResponseBody): ResponseBody {
        // Convert the Retrofit ResponseBody to an OkHttp ResponseBody
        return value.source().buffer.readByteString().toResponseBody()
    }
}