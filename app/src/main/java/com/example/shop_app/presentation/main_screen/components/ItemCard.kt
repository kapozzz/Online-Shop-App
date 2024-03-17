package com.example.shop_app.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shop_app.R
import com.example.shop_app.domain.model.Feedback
import com.example.shop_app.domain.model.Item
import com.example.shop_app.domain.model.Price
import com.example.shop_app.presentation.theme.ShopAppTheme

@Composable
fun ItemCard(
    item: Item,
    addItem: (itemId: String) -> Unit,
    onLikeClick: (itemId: String) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .height(286.dp)
            .width(168.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                AsyncImage(
                    modifier = Modifier.height(144.dp),
                    model = item.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 2.dp,
                            start = 6.dp
                        )
                ) {
                    Text(
                        text = item.price.price,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${item.price.priceWithDiscount} ${item.price.unit}",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Box(
                            modifier = Modifier
                                .padding(start = 2.dp)
                                .width(34.dp)
                                .height(16.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "-${item.price.discount}%",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(start = 6.dp),
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Row(
                    modifier = Modifier
                        .padding(
                            start = 6.dp,
                            top = 45.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.rating_icon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = "${item.feedback.rating}",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.tertiary
                    )

                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = "(${item.feedback.count})",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { addItem(item.id) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { onLikeClick(item.id) }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (item.isLiked) R.drawable.liked_icon
                        else R.drawable.like_icon
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Composable
@Preview
private fun ItemCardPreview() {
    ShopAppTheme {
        ItemCard(item = mock_item, {}, {})
    }
}

private val mock_item = Item(
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
    image = "https://yandex.ru/images/search?from=tabbar&img_url=https%3A%2F%2Fnaturel.ua%2Fpreset%2Fshop_product_type_schema%2F3ac13eec-b7e2-11e8-bd59-001e676e7890-e23a817d-cac6-11e8-82ae-001e676e7890.jpeg%3F1657807158&lr=213&pos=0&rpt=simage&text=ESFOLIO"
)








