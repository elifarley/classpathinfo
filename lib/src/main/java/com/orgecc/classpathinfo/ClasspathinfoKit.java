package com.orgecc.classpathinfo;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.orgecc.util.Utils;

public class ClasspathinfoKit {

    public static String getSourceLocation( final String className ) throws ClassNotFoundException {
        return getSourceLocation( getClass( className ) );
    }

    public static String getSourceLocation( final Class c ) {

        String exMsg = null;
        java.security.CodeSource codeSource = null;

        try {
            codeSource = c.getProtectionDomain().getCodeSource();

        } catch ( final Exception e ) {
            exMsg = e.toString();
        }

        final URL classURL = codeSource == null ? getSourceURL( c ) : codeSource.getLocation();

        if ( classURL == null ) {
            return c.getName() + ": (missing codeSource and classLoader)";
        }

        final String path = classURL.getPath();
        final File f = new File( path );
        if ( !f.isFile() ) {
            return c.getName() + ": " + path + " (not a file)"
                    + ( exMsg == null ? "" : "; " + exMsg );
        }

        return String.format( "%s: %s (MD5: %s)%s", c.getName(), path, Utils.md5sum( f ),
                exMsg == null ? "" : "; " + exMsg );
    }

    public static Class<?> getClass( final String className ) throws ClassNotFoundException {

        return Class.forName( className.replace( '/', '.' ).replace( '\\', '.' )
                .replaceFirst( ".class$", "" ).replaceFirst( ".java$", "" ) );

    }

    public static URL getSourceURL( final Class<?> c ) {

        URL result =
                getClassLoader( c ).getResource(
                        c.getCanonicalName().replace( '.', '/' ) + ".class" );

        try {

            final URLConnection conn = result.openConnection();

            if ( conn instanceof JarURLConnection ) {
                result = ( (JarURLConnection) conn ).getJarFileURL();

            }

        } catch ( final IOException e ) {
            // keep the original value of result
        }

        return result;

    }

    public static ClassLoader getClassLoader( final Class<?> c ) {

        ClassLoader result = c.getClassLoader();

        if ( result != null ) {
            return result;
        }

        // Try the bootstrap classloader - obtained from the ultimate parent of the System Class
        // Loader.
        result = ClassLoader.getSystemClassLoader();

        while ( result != null && result.getParent() != null ) {
            result = result.getParent();
        }

        return result;

    }

}
