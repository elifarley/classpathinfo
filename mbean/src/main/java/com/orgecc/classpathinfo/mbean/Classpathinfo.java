package com.orgecc.classpathinfo.mbean;

import org.jboss.system.ServiceMBeanSupport;

import com.orgecc.classpathinfo.ClasspathinfoKit;

public class Classpathinfo extends ServiceMBeanSupport implements ClasspathinfoMBean {

    public String getSourceLocation( final String className ) {

        try {
            return ClasspathinfoKit.getSourceLocation( className );

        } catch ( final Exception e ) {
            return e.toString();
        }

    }

}
