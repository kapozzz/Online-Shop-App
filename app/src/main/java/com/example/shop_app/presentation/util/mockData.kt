package com.example.shop_app.presentation.util

import com.example.shop_app.domain.model.Feedback
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.Price

val fakeItem = Item(
    id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
    available = 100,
    description = "Лосьон для тела `ESFOLIO` COENZYME Q10 Увлажняющий содержит минеральную воду и соду, способствует глубокому очищению пор от различных загрязнений, контроллирует работу сальных желез, сужает поры. Обладает мягким антиептическим действием, не пересушивает кожу. Минеральная вода тонизирует и смягчает кожу.",
    feedback = Feedback(4, 4.3),
    info = emptyList(),
    ingredients = "Water, Glycerin Palmitic Acid, Stearic Acid, Myristic Acid, Potassium Hydroxide, Lauric Acid, Cocamidopropyl Betaine, Tea-Lauryl Sulfate, Phenoxyethanol, Sodium Chloride, Acrylates/C10-30 Alkyl Acrylate Crosspolymer, Arachidic Acid, Fragrance, Cellulose Gum, Disodium Edta, Capric Acid, Sodium Benzoate",
    price = Price(39, "899", "549", "₽"),
    subtitle = "Пенка для умывания A`PIEU` `DEEP CLEAN` 200  мл ",
    tags = emptyList(),
    title = "ESFOLIO",
    isLiked = false,
    inBasket = false,
    image = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Fnaturel.ua%2Fpreset%2Fshop_product_type_schema%2F3ac13eec-b7e2-11e8-bd59-001e676e7890-e23a817d-cac6-11e8-82ae-001e676e7890.jpeg%3F1657807158&lr=213&pos=0&rpt=simage&text=ESFOLIO"
)