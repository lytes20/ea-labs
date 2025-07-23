package customers.aop;

import customers.EmailSender;
import customers.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class AopConfig {

    @Autowired
    private ILogger logger;



    @After("execution(* customers.*.sendEmail(..)) && args(email, message)")
    public void afterEmailSent(JoinPoint joinPoint, String email, String message) {
        EmailSender emailSender  = (EmailSender) joinPoint.getTarget();
        String outgoingMailServer = emailSender.getOutgoingMailServer();
        logger.log("method=sentEmail, email=" + email + ", message=" + message+
                ", outgoingMailServer=" + outgoingMailServer);
    }

    @Around("execution(* customers.*DAO.*(..))")
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
