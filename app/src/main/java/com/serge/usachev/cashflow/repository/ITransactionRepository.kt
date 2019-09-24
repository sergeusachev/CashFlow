package com.serge.usachev.cashflow.repository

import com.serge.usachev.cashflow.model.Coordinate
import com.serge.usachev.cashflow.model.TransactionModel

interface ITransactionRepository {
    fun insertTransactionModel(transactionModel: TransactionModel)
    fun insertTransactionCoords(transactionCoordinate: Coordinate)
}