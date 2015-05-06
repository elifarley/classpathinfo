package com.orgecc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class Utils {

    public static String md5sum( final File f ) {

        try {
            return md5sum( new FileInputStream( f ) );

        } catch ( final Exception e ) {
            throw new RuntimeException( e );
        }

    }

    public static String md5sum( final InputStream fis ) {

        try {

            final MessageDigest md = MessageDigest.getInstance( "MD5" );

            final byte[] buffer = new byte[ 8192 ];
            int count;
            while ( ( count = fis.read( buffer ) ) > 0 ) {
                md.update( buffer, 0, count );
            }

            return bytesToHexString( md.digest() );

        } catch ( final Exception e ) {
            throw new RuntimeException( e );
        }

    }

    public static String bytesToHexString( final byte[] bytes ) {

        final StringBuilder sb = new StringBuilder();
        for ( final byte b: bytes ) {
            sb.append( String.format( "%02x", b & 0xff ) );
        }

        return sb.toString();

    }

}
