package com.aldajo92.tvmazeapp.exceptions

class TVMazeAppException(val errorCode: Int = -1, val errorMessage: String = "Something wrong") :
    Exception(errorMessage)
