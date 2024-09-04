package senla.com.transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import senla.com.connectJdbc.ConnectionHolder;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class TransactionAspect {

    private final ConnectionHolder connectionHolder;

    @Around("@annotation(Transaction)")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        try {
            connectionHolder.openTransaction();
            result = joinPoint.proceed();
            connectionHolder.commitTransaction();
        } catch (RuntimeException e) {
            connectionHolder.rollbackTransaction();
            throw e;
        } finally {
            connectionHolder.closeConnection();
        }
        return result;
    }
}