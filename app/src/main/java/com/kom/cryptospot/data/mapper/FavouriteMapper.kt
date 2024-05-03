// package com.kom.cryptospot.data.mapper
//
// import com.kom.cryptospot.data.model.Favourite
// import com.kom.cryptospot.data.source.local.database.entity.FavouriteEntity
//
// fun Favourite?.toFavouriteEntity() =
//    FavouriteEntity(
//        id = this?.id,
//        coinId = this?.coinId.orEmpty(),
//        coinName = this?.coinName.orEmpty(),
//        coinImage = this?.coinImage.orEmpty(),
//    )
//
// fun FavouriteEntity?.toFavourite() =
//    Favourite(
//        id = this?.id,
//        coinId = this?.coinId,
//        coinName = this?.coinName,
//        coinImage = this?.coinImage,
//    )
//
// fun List<FavouriteEntity?>.toCartList() = this.map { it.toFavourite() }
