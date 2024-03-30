package com.kapozzz.domain.model

data class UiItem(
    val id: String,
    val available: Int,
    val description: String,
    val feedback: Feedback,
    val info: List<Info>,
    val ingredients: String,
    val price: Price,
    val subtitle: String,
    val tags: List<String>,
    val title: String,
    val image: String,
    val isLiked: Boolean,
    val inBasket: Boolean
) {
    companion object {
        fun getEmptyItem(): UiItem {
            return UiItem(
                id = "empty",
                available = 0,
                description = "empty",
                feedback = Feedback(
                    count = 0,
                    rating = 0.0
                ),
                info = listOf(
                    Info(
                        title = "empty",
                        value = "empty"
                    )
                ),
                ingredients = "empty",
                price = Price(
                    discount = 0,
                    price = "empty",
                    priceWithDiscount = "empty",
                    unit = "empty"
                ),
                subtitle = "empty",
                tags = emptyList(),
                title = "empty",
                image = "empty",
                isLiked = false,
                inBasket = false
            )
        }
    }
}