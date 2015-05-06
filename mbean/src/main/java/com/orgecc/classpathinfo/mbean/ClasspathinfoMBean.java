package com.orgecc.classpathinfo.mbean;

public interface ClasspathinfoMBean extends org.jboss.system.ServiceMBean {

    String getSourceLocation( String className );

}
