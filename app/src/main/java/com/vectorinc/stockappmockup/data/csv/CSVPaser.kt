package com.vectorinc.stockappmockup.data.csv

import java.io.InputStream

interface CSVPaser<T> {
suspend fun  paser(stream: InputStream) : List<T>
}