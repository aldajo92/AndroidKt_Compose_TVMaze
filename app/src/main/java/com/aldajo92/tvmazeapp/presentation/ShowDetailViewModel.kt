package com.aldajo92.tvmazeapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aldajo92.tvmazeapp.mappers.toUIModel
import com.aldajo92.tvmazeapp.repository.show_list.ShowRepository
import com.aldajo92.tvmazeapp.ui.models.ShowUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val showRepository: ShowRepository
) : ViewModel() {

    private val _selectedShowLiveData = MutableLiveData<ShowUIModel?>()
    val selectedShowLiveData: LiveData<ShowUIModel?> = _selectedShowLiveData

    fun getSelectedShow(showID: String) {
        viewModelScope.launch {
            if(showID.isNotBlank()){
                _selectedShowLiveData.value = showRepository.getShowDetail(showID)?.toUIModel()
            }
        }
    }

}