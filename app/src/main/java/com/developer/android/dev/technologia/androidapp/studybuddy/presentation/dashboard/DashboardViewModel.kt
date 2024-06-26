package com.developer.android.dev.technologia.androidapp.studybuddy.presentation.dashboard

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.model.Session
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.model.Subject
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.model.Task
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.repository.SessionRepository
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.repository.SubjectRepository
import com.developer.android.dev.technologia.androidapp.studybuddy.domain.repository.TaskRepository
import com.developer.android.dev.technologia.androidapp.studybuddy.utils.SnackbarEvent
import com.developer.android.dev.technologia.androidapp.studybuddy.utils.toHours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val subjectRepository: SubjectRepository,
    private val sessionRepository: SessionRepository,
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())

    val state = combine(
        _state,
        subjectRepository.getTotalSubjectCount(),
        subjectRepository.getTotalGoalHours(),
        subjectRepository.getAllSubjects(),
        sessionRepository.getTotalSessionsDuration()
    ) { state, subjectCount, goalHours, subjects, totalSessionDuration ->
        state.copy(
            totalSubjectCount = subjectCount,
            totalGoalStudyHours = goalHours,
            subjects = subjects,
            totalStudiedHours = totalSessionDuration.toHours()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = DashboardState()
    )

    val tasks: StateFlow<List<Task>> =
        taskRepository.getAllUpcomingTasks()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = emptyList()
            )

    val recentSessions: StateFlow<List<Session>> =
        sessionRepository.getRecentFiveSessions()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
                initialValue = emptyList()
            )

    private val _snackBarEventFlow = MutableSharedFlow<SnackbarEvent>()
    val snackBarEventFlow = _snackBarEventFlow.asSharedFlow()

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnSubjectNameChange -> {
                _state.update {
                    it.copy(subjectName = event.name)
                }
            }

            is DashboardEvent.OnGoalStudyHourChange -> {
                _state.update {
                    it.copy(goalStudyHours = event.hours)
                }
            }

            is DashboardEvent.OnSubjectCarColorChange -> {
                _state.update {
                    it.copy(subjectCardColor = event.colors)
                }
            }

            is DashboardEvent.OnDeleteSessionButtonClick -> {
                _state.update {
                    it.copy(session = event.session)
                }
            }

            DashboardEvent.SaveSubject -> saveSubject()
            DashboardEvent.DeleteSession -> deleteSession()
            is DashboardEvent.OnTaskIsCompleteChange -> updateTask(event.task)
        }
    }

    private fun deleteSession(){
        viewModelScope.launch {
            try {
                state.value.session?.let { session->
                    sessionRepository.deleteSession(session=session)

                    _snackBarEventFlow.emit(
                        SnackbarEvent.ShowSnackBar(
                            "Session deleted successfully"
                        )
                    )
                }
            }catch (e:Exception){
                _snackBarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar(
                        "Couldn't delete session. ${e.message}",
                        SnackbarDuration.Long
                    )
                )
            }
        }
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                taskRepository.upsertTask(
                    task=task.copy(
                        isComplete = !task.isComplete
                    )
                )
                _snackBarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar(
                        "Saved in completed task"
                    )
                )
            } catch (e: Exception) {
                _snackBarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar(
                        "Couldn't complete task. ${e.message}",
                        SnackbarDuration.Long
                    )
                )
            }

        }
    }

    private fun saveSubject() {
        viewModelScope.launch {
            try {
                subjectRepository.upsertSubject(
                    subject = Subject(
                        name = state.value.subjectName,
                        goalHours = state.value.goalStudyHours.toFloatOrNull() ?: 1f,
                        colors = state.value.subjectCardColor.map {
                            it.toArgb()
                        }
                    )
                )
                _state.update {
                    it.copy(
                        subjectName = "",
                        goalStudyHours = "",
                        subjectCardColor = Subject.subjectColors.random()
                    )
                }
                _snackBarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar(
                        "Subject saved successfully"
                    )
                )
            } catch (e: Exception) {
                _snackBarEventFlow.emit(
                    SnackbarEvent.ShowSnackBar(
                        "Couldn't save subject. ${e.message}",
                        SnackbarDuration.Long
                    )
                )
            }

        }
    }
}