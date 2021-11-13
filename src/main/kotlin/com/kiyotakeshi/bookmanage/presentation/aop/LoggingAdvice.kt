package com.kiyotakeshi.bookmanage.presentation.aop

import com.kiyotakeshi.bookmanage.application.service.security.BookManagerUserDetails
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

private val logger = LoggerFactory.getLogger(LoggingAdvice::class.java)

// Advice は横断的関心事の処理を定義するもの(LoggingAdvice はログという横断的関心事)
@Aspect
@Component
class LoggingAdvice {

    // 戻り値 パッケージ名.クラス名.関数名(引数の型)
    @Before("execution(* com.kiyotakeshi.bookmanage.presentation.controller..*.*(..))")
    fun beforeLog(joinPoint: JoinPoint) {
        val user =
            SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        // joinPoint には対象である Controller クラスの処理の情報が含まれる
        logger.info("Start: ${joinPoint.signature} userId=${user.id}")
        logger.info("Class: ${joinPoint.target.javaClass}")
        logger.info("Session: ${(RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.session.id}")
    }

    @After("execution(* com.kiyotakeshi.bookmanage.presentation.controller..*.*(..))")
    fun afterLog(joinPoint: JoinPoint) {
        val user =
            SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        logger.info("End: ${joinPoint.signature} userId=${user.id}")
    }

//    @Around("execution(* com.kiyotakeshi.bookmanage.presentation.controller..*.*(..))")
//    fun aroundLog(joinPoint: ProceedingJoinPoint): Any? {
//        val user =
//            SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
//        logger.info("Start Proceed: ${joinPoint.signature} userId=${user.id}")
//
//        // proceed で AOP の対象の処理を実行(前後にログを仕込む)
//        val result = joinPoint.proceed()
//
//        logger.info("End Proceed: ${joinPoint.signature} userId=${user.id}")
//
//        return result
//    }

    // returning で指定した名前で対象処理の戻り値を扱うことができる
    @AfterReturning(
        "execution(* com.kiyotakeshi.bookmanage.presentation.controller..*.*(..))",
        returning = "returnValue"
    )
    fun afterReturningLog(joinPoint: JoinPoint, returnValue: Any?) {
        logger.info("End: ${joinPoint.signature} returnValue=${returnValue}")
    }

    // 例外の種類に応じて処理を定義
    // Throwable 型を引数として定義したためすべての例外を拾う
//    @AfterThrowing("execution(* com.kiyotakeshi.bookmanage.presentation.controller..*.*(..))", throwing = "e")
//    fun afterThrowingLog(joinPoint: JoinPoint, e: Throwable) {
//        logger.error("Exception: ${e.javaClass} signature=${joinPoint.signature} message=${e.message}")
//    }

    // IllegalArgumentException のみを拾う
    @AfterThrowing("execution(* com.kiyotakeshi.bookmanage.presentation.controller..*.*(..))", throwing = "e")
    fun afterThrowingLog(joinPoint: JoinPoint, e: IllegalArgumentException) {
        logger.error("Exception: ${e.javaClass} signature=${joinPoint.signature} message=${e.message}")
    }
}