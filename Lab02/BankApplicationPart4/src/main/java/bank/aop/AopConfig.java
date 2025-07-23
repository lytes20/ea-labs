package bank.aop;

import bank.jms.JMSSender;
import bank.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class AopConfig {

    private Logger logger;

    @Autowired
    public AopConfig(Logger logger) {
        this.logger = logger;
    }


    @Before("execution(* bank.dao.*.*(..))")
    public void beforeDAOInvocation(JoinPoint joinPoint) {

        logger.log("A DAO method is about to be invoked" +  joinPoint.getSignature());
    }

    @After("execution(* bank.jms.*.*(..)) && args(message)")
    public void afterJMSSent( String message) {
        logger.log("A JMS message has been sent: " + message);
    }


    @Around("execution(* bank.service.*.*(..))")
    public Object invoke(ProceedingJoinPoint call ) throws Throwable {

        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();


        logger.log("method=" + call.getSignature().getName() + ", time=" + totaltime + "ms");
        return retVal;
    }
}
