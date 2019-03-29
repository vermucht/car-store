package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utilities for common methods.
 *
 * @author Aleksei Sapozhnikov (vermucht@gmail.com)
 * @version 0.1
 * @since 0.1
 */
public class Utils {
    /**
     * Logger.
     */
    @SuppressWarnings("unused")
    private static final Logger LOG = LogManager.getLogger(Utils.class);

    /**
     * Reads fully given input stream to output stream.
     *
     * @param in  Input stream object.
     * @param out Output stream object.
     * @throws IOException In case of problems.
     */
    public static void readFullInput(InputStream in, OutputStream out) throws IOException {
        var buf = new byte[1024];
        var read = in.read(buf);
        while (read > -1) {
            out.write(buf, 0, read);
            read = in.read(buf);
        }
    }

    /**
     * Parses id from string if it's possible.
     *
     * @param string   Given string.
     * @param notFound Value to return if could not parse.
     * @return Parsed id or <tt>notFound</tt> value.
     */
    public static long parseLong(String string, long notFound) {
        long result = notFound;
        if (string != null && string.matches("\\d+")) {
            result = Long.parseLong(string);
        }
        return result;
    }

    /**
     * Reads and returns resource as byte array,
     *
     * @param callerClass Object which is calling the resource (.class object).
     * @param path        Resource path.
     * @return Resource as byte array.
     */
    public static byte[] readResource(Class<?> callerClass, String path) {
        byte[] result = new byte[0];
        try (var in = callerClass.getClassLoader().getResourceAsStream(path);
             var out = new ByteArrayOutputStream()) {
            if (in == null) {
                throw new RuntimeException("Input resource is null");
            }
            Utils.readFullInput(in, out);
            result = out.toByteArray();
        } catch (Exception e) {
            LOG.fatal(e.getMessage(), e);
        }
        return result;
    }
}
