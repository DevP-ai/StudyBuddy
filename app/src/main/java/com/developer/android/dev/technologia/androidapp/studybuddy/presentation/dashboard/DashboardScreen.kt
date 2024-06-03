package com.developer.android.dev.technologia.androidapp.studybuddy.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.developer.android.dev.technologia.androidapp.studybuddy.R
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.model.Session
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.model.Subject
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.model.Task
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.components.AddSubjectDialog
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.components.CountCard
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.components.DeleteDialog
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.components.SubjectCard
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.components.studySessionList
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.components.taskList
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.destinations.SessionScreenRouteDestination
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.destinations.SubjectScreenRouteDestination
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.destinations.TaskScreenRouteDestination
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.subject.SubjectScreenNavArgs
import com.developer.android.dev.technologia.androidapp.studybuddy.presentation.task.TaskScreenNavArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun DashboardScreenRoute(
    navigator: DestinationsNavigator
) {
    DashboardScreen(
        onSubjectCardClick = { subjectId ->
            subjectId?.let {
                val navArg=SubjectScreenNavArgs(subjectId=subjectId)
                navigator.navigate(SubjectScreenRouteDestination(navArgs=navArg))
            }
        },
        onTaskCardClick ={taskId->
            val navArg=TaskScreenNavArgs(taskId=taskId,subjectId = null)
            navigator.navigate(TaskScreenRouteDestination(navArgs=navArg))
        },
        onStartSessionClick ={
            navigator.navigate(SessionScreenRouteDestination())
        }
    )
}

@Composable
private fun DashboardScreen(
    onSubjectCardClick: (Int?) -> Unit,
    onTaskCardClick: (Int?) -> Unit,
    onStartSessionClick: () -> Unit
) {

    val subjects = listOf(
        Subject(
            subjectId = 0,
            name = "English",
            goalHours = 10f,
            colors = Subject.subjectColors[0]
        ),
        Subject(
            subjectId = 0,
            name = "English",
            goalHours = 10f,
            colors = Subject.subjectColors[1]
        ),
        Subject(
            subjectId = 0,
            name = "English",
            goalHours = 10f,
            colors = Subject.subjectColors[2]
        ),
        Subject(
            subjectId = 0,
            name = "English",
            goalHours = 10f,
            colors = Subject.subjectColors[3]
        ),
        Subject(subjectId = 0, name = "English", goalHours = 10f, colors = Subject.subjectColors[4])
    )

    val tasks = listOf(
        Task(
            taskId = 1,
            taskSubjectId = 0,
            title = "Prepare Note",
            description = "",
            dueDate = 0L,
            priority = 0,
            relatedToSubject = "",
            isComplete = false
        ),
        Task(
            taskId = 1,
            taskSubjectId = 0,
            title = "Prepare Note",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = true
        ),
        Task(
            taskId = 1,
            taskSubjectId = 0,
            title = "Prepare Note",
            description = "",
            dueDate = 0L,
            priority = 2,
            relatedToSubject = "",
            isComplete = true
        ),
        Task(
            taskId = 1,
            taskSubjectId = 0,
            title = "Prepare Note",
            description = "",
            dueDate = 0L,
            priority = 0,
            relatedToSubject = "",
            isComplete = true
        ),
        Task(
            taskId = 1,
            taskSubjectId = 0,
            title = "Prepare Note",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = true
        ),
        Task(
            taskId = 1,
            taskSubjectId = 0,
            title = "Prepare Note",
            description = "",
            dueDate = 0L,
            priority = 2,
            relatedToSubject = "",
            isComplete = true
        ),
        Task(
            taskId = 1,
            taskSubjectId = 0,
            title = "Prepare Note",
            description = "",
            dueDate = 0L,
            priority = 1,
            relatedToSubject = "",
            isComplete = true
        )
    )

    val sessions = listOf(
        Session(0, "Physics", 0L, 0L, 1),
        Session(0, "Physics", 0L, 0L, 1),
        Session(0, "Physics", 0L, 0L, 1),
        Session(0, "Physics", 0L, 0L, 1),
        Session(0, "Physics", 0L, 0L, 1),
        Session(0, "Physics", 0L, 0L, 1),
        Session(0, "Physics", 0L, 0L, 1),
        Session(0, "Physics", 0L, 0L, 1)
    )

    var isAddSubjectDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }

    var subjectName by remember { mutableStateOf("") }

    var goalHours by remember { mutableStateOf("") }

    var selectedColor by remember { mutableStateOf(Subject.subjectColors.random()) }

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        onDismissRequest = { isAddSubjectDialogOpen = false },
        onConfirmClick = {
            isAddSubjectDialogOpen = false
        },
        selectedColor = selectedColor,
        onColorChange = {
            selectedColor = it
        },
        subjectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange = {
            subjectName = it
        },
        onGoalHourChange = {
            goalHours = it
        }
    )

    var isDeleteDialogOpen by rememberSaveable {
        mutableStateOf(false)
    }
    DeleteDialog(
        title = "Delete Session?",
        isOpen = isDeleteDialogOpen,
        bodyText = "Are you sure, you want to delete this session?\nYour studied hours will be reduced by this\nsession time. This action can not be undone.",
        onDismissRequest = {
            isDeleteDialogOpen = false
        },
        onConfirmClick = {
            isDeleteDialogOpen = false
        })

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
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = 10,
                    studiedHours = "5",
                    goalHours = "5"
                )
            }

            item {
                SubjectCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = subjects,
                    onAddIClick = {
                        isAddSubjectDialogOpen = true
                    },
                    onSubjectCardClick = onSubjectCardClick
                )
            }

            item {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp),
                    onClick = onStartSessionClick
                ) {
                    Text(text = "Start Study Session")
                }
            }
            taskList(
                sectionTitle = "UPCOMING TASKS",
                tasks = tasks,
                emptyListText = "You don't have any upcoming tasks.\nClick the + button in subject screen to add new task.",
                onTaskCardClick = onTaskCardClick,
                onCheckBoxClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            studySessionList(
                sectionTitle = "RECENT STUDY SESSION",
                sessions = sessions,
                emptyListText = "You don't have any recent study sessions.\nStart a study session to begin recording your progress.",
                onDeleteClick = {
                    isDeleteDialogOpen = true
                }
            )
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
    subjectCount: Int,
    studiedHours: String,
    goalHours: String
) {
    Row(modifier = modifier) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = "$subjectCount"
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = studiedHours
        )

        Spacer(modifier = Modifier.width(10.dp))

        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hour",
            count = goalHours
        )

    }

}

@Composable
private fun SubjectCardSection(
    modifier: Modifier,
    subjectList: List<Subject>,
    emptyListText: String = "You don't have any subjects.\nClick + button to add new subject.",
    onAddIClick: () -> Unit,
    onSubjectCardClick: (Int?) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
            )
            IconButton(onClick = { onAddIClick() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }
        }

        if (subjectList.isEmpty()) {
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
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            items(subjectList) { subject ->
                SubjectCard(
                    onClick = { onSubjectCardClick(subject.subjectId) },
                    gradientColors = subject.colors,
                    subjectName = subject.name
                )
            }
        }
    }
}