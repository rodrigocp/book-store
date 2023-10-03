package br.com.rcp.network.request

import br.com.rcp.core.models.RemoteWrapper
import br.com.rcp.networkabstract.error.ErrorHandler
import br.com.rcp.networkabstract.request.RequestHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

internal class RequestHandlerImpl @Inject constructor(
    private val handler: ErrorHandler
) : RequestHandler {
    override suspend fun <T> invoke(request: suspend () -> T): RemoteWrapper<T> {
        return withContext(IO) {
            try {
                RemoteWrapper.Success(request.invoke())
            } catch (exception: HttpException) {
                RemoteWrapper.Failure(handler.handle(exception))
            }
        }
    }

}
