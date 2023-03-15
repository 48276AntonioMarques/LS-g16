package pt.isel.ls.utils

import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.slf4j.LoggerFactory
import pt.isel.ls.controllers.Controller
import java.lang.NullPointerException

private val logger = LoggerFactory.getLogger("pt.isel.ls.utils.RouterGenerator")
annotation class Mapping(val path: String, val method: Method)

fun generateRouter(controllers: List<Controller>): RoutingHttpHandler {
    val routes: List<RoutingHttpHandler> = controllers.flatMap { controller ->
        val methods = controller::class.java.declaredMethods
        methods.map { method ->
            val annotations = method.annotations
            val mapping = annotations.firstOrNull { it is Mapping } as Mapping?
            if (mapping == null) {
                null
            }
            else {
                mapping.path bind mapping.method to { request ->
                    try {
                        if (method.parameterCount > 0) {
                            method.invoke(controller, request)
                        }
                        else {
                            method.invoke(controller)
                        } as Response
                    }
                    catch (e: Exception) {
                        when (e) {
                            is NullPointerException, is ClassCastException -> {
                                logger.error("Method ${method.name} does not return a Response")
                                Response(Status.INTERNAL_SERVER_ERROR)
                            }
                            else -> throw e
                        }
                    }
                }
            }
        }
    }.filterNotNull()
    return routes(*routes.toTypedArray())
}
