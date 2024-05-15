package com.rodrigonaya.test.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigonaya.test.R
import com.rodrigonaya.test.domain.entity.WordCount
import com.rodrigonaya.test.presentation.theme.TestTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                MainScreen()
            }
        }
    }

}

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        mainViewModel
            .error
            .collect {
                it?.let { message ->
                    Toast.makeText(
                        context,
                        context.getString(R.string.error, message),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    val every10thCharacterState = mainViewModel.every10thCharacter.collectAsState()
    val wordCountState = mainViewModel.wordCount.collectAsState()

    MainContent(
        every10thCharacterState.value,
        wordCountState.value
    ) {
        mainViewModel.getEvery10thCharacterList()
        mainViewModel.getWordCount()
    }

}

@Composable
fun MainContent(
    every10thCharacter: String,
    wordCount: List<WordCount>,
    onGetData: () -> Unit
) {
    val scroll = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Button(onGetData) {
            Text(
                text = stringResource(R.string.get_data)
            )
        }
        Text(text = "Every 10th character:")
        Text(
            text = every10thCharacter,
            modifier = Modifier
                .height(90.dp)
                .verticalScroll(scroll)
        )
        HorizontalDivider()
        Text(text = "Word count:")
        LazyColumn {
            items(wordCount) {
                Text(text = "Word: ${it.word}, count: ${it.count}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TestTheme {
        MainContent(
            "[A, B, C]",
            listOf(
                WordCount("Test word", 1)
            )
        ) { }
    }
}