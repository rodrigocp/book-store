package br.com.rcp.network.error

import br.com.rcp.core.models.RemoteError
import br.com.rcp.networkabstract.error.ErrorHandler
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

private const val ERROR = "error"
private const val MESSAGE = "message"

internal class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override fun handle(exception: HttpException): RemoteError {
        return when (exception.code()) {
            400 -> RemoteError.BadRequest(getErrorMessage(exception))
            401 -> RemoteError.Unauthorized(getErrorMessage(exception))
            403 -> RemoteError.AccessDenied(getErrorMessage(exception))
            404 -> RemoteError.NotFound(getErrorMessage(exception))
            500 -> RemoteError.ServiceInternalError(getErrorMessage(exception))
            503 -> RemoteError.ServiceUnavailable(getErrorMessage(exception))
            else -> RemoteError.Unknown(getErrorMessage(exception))
        }
    }

    private fun getErrorMessage(exception: HttpException) : String {
        return with(exception) {
            getErrorFromResponseBody(this).ifBlank {
                getMessageFromResponseBody(exception)
            }
        }
    }

    private fun getErrorFromResponseBody(exception: HttpException) : String {
        return getMessageErrorFromBody(exception, ERROR)
    }

    private fun getMessageFromResponseBody(exception: HttpException) : String {
        return getMessageErrorFromBody(exception, MESSAGE)
    }

    private fun getMessageErrorFromBody(exception: HttpException, parameter: String) : String {
        return try {
            exception.response()?.errorBody()?.string()?.let {
                JSONObject(it).getString(parameter)
            } ?: ""
        } catch (exception: JSONException) {
            ""
        }
    }
}
