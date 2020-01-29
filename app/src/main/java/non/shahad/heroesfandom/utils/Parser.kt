package non.shahad.heroesfandom.utils

import non.shahad.heroesfandom.data.remote.responses.ComicResponse
import non.shahad.heroesfandom.domain.model.Comic
import okhttp3.ResponseBody
import org.jsoup.Jsoup

object ComicPageParser{
    fun parse(t : ResponseBody) : ComicResponse{
        val document = Jsoup.parse(t.string())
        val articles = document.getElementsByTag("article")
        val totalPage = document.select("a.page-numbers").last().text()
        val categoryName = document.getElementsByClass("category-name").text()
        val list : ArrayList<Comic> = arrayListOf()

        articles.forEach {
            val postTitle = it.getElementsByClass("post-title")[0].text()
            val poster = it.getElementsByClass("post-header-image")[0].getElementsByTag("img").attr("src")
            val category = it.getElementsByClass("post-category").text()
            val postLink = it.getElementsByClass("post-header-image").select("a").attr("href")
            val publishedDate = it.getElementsByTag("time").text()

            list.add(
                Comic(
                    title = postTitle,
                    posterUrl = poster,
                    category = category,
                    postLink = postLink,
                    publishedDate = publishedDate
                )
            )
        }

        return ComicResponse(page = totalPage.toInt(),list = list,categoryName = categoryName)
    }
}