package com.example.weather.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.cityItem
import com.example.weather.data.showCategory
import kotlinx.coroutines.launch

class CityViewModel: ViewModel() {
    private var _cityList =
        mutableStateListOf<cityItem>()

    fun addToCityList(cityItem: cityItem) {
        _cityList.add(cityItem)
    }


    fun removeItem(cityItem: cityItem) {
        _cityList.remove(cityItem)
    }

    fun clearAllItems() {
        _cityList.clear()
    }

    fun getAllitems(): List<cityItem> {
        return _cityList
    }

    fun getFavoriteitems(): List<cityItem>{
        return _cityList.filter { it.isFavorite }
    }

    fun getCurrentlyWatching(): List<cityItem>{
        return _cityList.filter { it.showcategory == showCategory.Currently_Watching }
    }

    fun getWatchlist(): List<cityItem>{
        return _cityList.filter { it.showcategory == showCategory.Watchlist }
    }

    fun editItem(originalCity: cityItem, editedCity: cityItem) {
        val index = _cityList.indexOf (originalCity) // Find the index of the original city item in the list
        _cityList[index] = editedCity // Replace the old city item with the edited one in the list
        }

    fun getCity(cityItem: cityItem): String{
        return cityItem.city
        }
}