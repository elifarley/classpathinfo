package com.orgecc.classpathinfo;

import com.orgecc.utils.ClassInfoKit;

public final class ClasspathinfoKit {

    private ClasspathinfoKit() {
        // Utility class
    }

    public static String getSourceLocation( final String className ) throws ClassNotFoundException {
        return ClassInfoKit.getSourceLocation( className );
    }

}
