package com.mobiquity.infrastructure.platform

import com.mobiquity.infrastructure.exception.Failure
import com.mobiquity.infrastructure.functional.Either
import kotlinx.coroutines.*

//TODO Added Dispatchers.Main for unit testing. Need to replace it with proper handling of main and background dispatcher
abstract class BaseUseCase<out Type, in Params>(private val ioScope: CoroutineScope, val main : CoroutineDispatcher = Dispatchers.Main) where Type : Any? {
    abstract suspend fun run(param: Params): Either<Failure, Type>
    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit) {
        ioScope.launch {
            val result = run(params)
            withContext(main)
            {
                onResult(result)
            }
        }
    }

    class None
}