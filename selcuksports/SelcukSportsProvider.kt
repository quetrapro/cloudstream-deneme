package com.quetrapro.selcuksports

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class SelcukSportsProvider : MainAPI() {
    override var mainUrl = "https://www.selcuksportshd1800.xyz"
    override var name = "SelcukSportsHD"
    override val hasMainPage = true
    override val supportedTypes = setOf(TvType.Live)

    val domains = (1791..1820).map { "https://www.selcuksportshd$it.xyz" }

    override suspend fun load(url: String): LoadResponse {
        val doc = app.get(url).document
        val iframeUrl = doc.selectFirst("iframe")?.attr("src") ?: return ErrorLoadingException("Iframe not found").let { throw it }

        return LiveStreamLoadResponse(
            name = "SelcukSportsHD",
            url = url,
            streamUrl = iframeUrl,
            referer = url,
            quality = Qualities.Unknown,
            headers = mapOf("User-Agent" to USER_AGENT),
        )
    }

    override suspend fun search(query: String): List<SearchResponse> {
        return listOf(
            LiveSearchResponse(
                name = "SelçukSports Canlı",
                url = mainUrl,
                apiName = this.name,
                type = TvType.Live,
            )
        )
    }
}