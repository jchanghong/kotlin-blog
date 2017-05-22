package com.jchanghong.config

import com.jchanghong.mapper.LogMapper
import com.jchanghong.vo.LogInfo
import com.jchanghong.vo.User
import org.apache.logging.log4j.core.config.Order
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*

/**
 * @author Do
 * *
 * @package com.eumji.zblog.config
 * *
 * @name LoggerAspect
 * *
 * @date 2017/4/10
 * *
 * @time 18:08
 */
@Aspect
@Component
@Order(5)
class LoggerAspect {
    @Autowired
    lateinit private var logMapper: LogMapper
    private val logger = LoggerFactory.getLogger(LoggerAspect::class.java)


    @AfterThrowing(pointcut = "execution(* com.jchanghong.controller.*.*(..))", throwing = "e")
    fun afterThrowing(joinPoint: JoinPoint, e: Throwable) {
        logger.info(e.message)
        val log = LogInfo()
        val requestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        if (requestAttributes != null) {
            val request = requestAttributes.request
            val user = request.session.getAttribute("user")
            if (user != null&&user is User) {
                log.userId =user.id.toString()
            }
        }
        val request = requestAttributes.request
        log.ip = request.remoteAddr
        log.method = request.method
        log.url = request.requestURL.toString()
        log.args = Arrays.toString(joinPoint.args)
        log.classMethod = joinPoint.signature.declaringTypeName + "." + joinPoint.signature.name
        log.exception = e.message ?: ""
        log.operateTime = Date()
        logger.info(log.toString())
        logMapper.save(log)
    }
}
