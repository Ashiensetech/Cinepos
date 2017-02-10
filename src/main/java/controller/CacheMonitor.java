package controller;

/*


import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.text.NumberFormat;
*/

/**
 * Created by mi on 2/10/17.
 */

//@Aspect
public class CacheMonitor {}/*
    private final static Logger LOG = LoggerFactory.getLogger(CacheMonitor.class);
    private final static NumberFormat NF = new DecimalFormat("0.0###");

    @Autowired
    private SessionFactory sessionFactory;

    @Around("execution(public * getByScreenId(..))")
    public Object logAdvice(ProceedingJoinPoint pjp) throws Throwable {
    *//*    if (!LOG.isDebugEnabled()) {
            return pjp.proceed();
        }*//*

        Statistics statistics = sessionFactory.getStatistics();
        statistics.setStatisticsEnabled(true);

        long hit0 = statistics.getQueryCacheHitCount();
        long miss0 = statistics.getSecondLevelCacheMissCount();

        Object result = pjp.proceed();

        long hit1 = statistics.getQueryCacheHitCount();
        long miss1 = statistics.getQueryCacheMissCount();

        double ratio = (double) hit1 / (hit1 + miss1);

        if (hit1 > hit0) {
            System.out.println(String.format("CACHE HIT; Ratio=%s; Signature=%s#%s()", NF.format(ratio), pjp.getTarget().getClass().getName(), pjp.getSignature().toShortString()));
        }
        else if (miss1 > miss0){
            System.out.println(String.format("CACHE MISS; Ratio=%s; Signature=%s#%s()", NF.format(ratio), pjp.getTarget().getClass().getName(), pjp.getSignature().toShortString()));
        }
        else {
            System.out.println("query cache not used");
        }

        return result;
    }
}*/