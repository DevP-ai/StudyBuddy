package com.developer.android.dev.technologia.androidapp.studybuddy.presentation.dashboard

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.studybuddy.R
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.model.Subject
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.components.CountCard

@Composable
fun DashboardScreen() {
    Scaffold(
        topBar = {
            DashboardTopAppBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item{
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = 10,
                    studiedHours = "5",
                    goalHours = "5"
                )
            }

            item{
                SubjectCardSection(
                    modifier =  Modifier.fillMaxWidth(),
                    subjectList = emptyList(),
                    onAddIconClicked = {},
                    onSubjectCardClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Study Buddy",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount:Int,
    studiedHours:String,
    goalHours:String
) {
    Row (modifier = modifier){
        CountCard(
            modifier =Modifier.weight(1f),
            headingText = "Subject Count",
            count ="$subjectCount"
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCard(
            modifier =Modifier.weight(1f),
            headingText ="Studied Hours",
            count =studiedHours
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCard(
            modifier =Modifier.weight(1f),
            headingText ="Goal Study Hour",
            count =goalHours
        )

    }

}

@Composable
private fun SubjectCardSection(
    modifier: Modifier,
    subjectList:List<Subject>,
    emptyListText:String="You don't have any subjects.\nClick + button to add new subhject.",
    onAddIconClicked:()->Unit,
    onSubjectCardClick:(Int?)->Unit
) {
    Column(modifier=modifier){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }
        }

        if(subjectList.isEmpty()){
            Image(
                painter = painterResource(id = R.drawable.img_books),
                contentDescription = emptyListText,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}