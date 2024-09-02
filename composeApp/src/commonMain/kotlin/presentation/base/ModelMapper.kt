package presentation.base

import data.base.DataModel

interface ModelMapper<R : DataModel, T : ViewDataModel> {
    fun mapperToViewDataModel(dataModel: R): T

    fun mapperToDataModel(viewDataModel: ViewDataModel): DataModel {
        TODO("maybe not implement")
    }
}
