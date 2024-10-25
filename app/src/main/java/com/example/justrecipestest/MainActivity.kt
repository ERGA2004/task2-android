package com.example.justrecipestest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.justrecipestest.ui.theme.JustRecipesTestTheme

// TODO: Заглушка для компонента карточки рецепта

data class Recipe(
    val image: Int,
    val title: String,
    val servings: Int,
    val prepTime: Int,
    val description: String,
    val ingredients: List<Ingredient>,
    val instructions: List<Instruction>
)

// Пример рецепта
val recipe = Recipe(
    image = R.drawable.header_02, // Изображение рецепта
    title = "Шоколадный торт",  // Название рецепта
    servings = 8,  // Количество порций
    prepTime = 60, // Время приготовления в минутах
    description = "Вкусный шоколадный торт на любой случай",  // Описание
    ingredients = listOf(
        Ingredient("Мука", false),  // Ингредиенты
        Ingredient("Сахар", false),
        Ingredient("Какао-порошок", false),
        Ingredient("Разрыхлитель", false),
        Ingredient("Сода", false),
        Ingredient("Соль", false),
        Ingredient("Яйца", false),
        Ingredient("Молоко", false),
        Ingredient("Растительное масло", false),
        Ingredient("Ванильный экстракт", false),
        Ingredient("Кипяток", false)
    ),
    instructions = listOf( // Инструкции приготовления
        Instruction("Разогрейте духовку до 180°C. Смажьте маслом и посыпьте мукой две круглые формы диаметром 9 дюймов.", false),
        Instruction("Смешайте сахар, муку, какао, разрыхлитель, соду и соль в большой миске.", false),
        Instruction("Добавьте яйца, молоко, масло и ваниль; взбивайте на средней скорости 2 минуты.", false),
        Instruction("Вмешайте кипяток (тесто будет жидким). Вылейте тесто в подготовленные формы.", false),
        Instruction("Выпекайте 30-35 минут или до тех пор, пока деревянная палочка, вставленная в центр, не будет выходить чистой.", false),
        Instruction("Остудите 10 минут; вытащите из форм на решетку. Полностью остудите.", false)
    )
)

// Основная активность приложения
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustRecipesTestTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize().padding(32.dp)
                ) { innerPadding ->
                    RecipeCard(innerPadding, recipe)
                }
            }
        }
    }

    // Функция добавления нового рецепта в список (пока заглушка)
    private fun addItemToList(names: SnapshotStateList<String>) {
        val newItem = "Рецепт ${names.size + 1}"
        names.add(newItem)
    }
}

// Основная функция отображения (заглушка для экрана приветствия)
@Composable
fun Main(innerPadding: PaddingValues, names: List<String>) {
    var shouldShowBoarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowBoarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowBoarding = false })
    } else {
        Greetings(names)
    }
}

// Экран приветствия
@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Surface {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Just Recipez", style = MaterialTheme.typography.headlineLarge)
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Начать готовить")
            }
        }
    }
}

// Отображение списка рецептов
@Composable
fun Greetings(names: List<String>) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(top = 48.dp, start = 24.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {
            item { Text("Десерты", fontSize = 24.sp) }
            items(names) { name ->
                Greeting(name)
            }
        }
    }
}

// Отображение одного рецепта с анимацией
@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 148.dp else 0.dp,
        animationSpec = tween(durationMillis = 1000)
    )
    Column {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 40.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier.weight(1f).padding(bottom = extraPadding)
                ) {
                    Text(text = "Рецепт, ")
                    Text(text = name)
                }
                OutlinedButton(
                    onClick = { expanded = !expanded }
                ) {
                    Text(if (expanded) "Показать меньше" else "Показать больше",
                        color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

// Превью для отладки
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustRecipesTestTheme {
        Greeting("Android")
    }
}
