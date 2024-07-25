package org.example.gzipcompression;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GzipHttpServletResponseWrapper extends HttpServletResponseWrapper {

    // Logger for this class
    private static final Logger logger = LoggerFactory.getLogger(GzipHttpServletResponseWrapper.class);

    private GZIPServletOutputStream gzipOutputStream = null;
    private PrintWriter printWriter = null;

    // Constructor that takes HttpServletResponse
    public GzipHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        // Ensure PrintWriter has not been obtained already
        if (this.printWriter != null) {
            throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
        }
        // Create GZIP output stream if it doesn't exist
        if (this.gzipOutputStream == null) {
            try {
                this.gzipOutputStream = new GZIPServletOutputStream(getResponse().getOutputStream());
            } catch (IOException e) {
                logger.error("Failed to create GZIP output stream", e);
                throw e;
            }
        }
        return this.gzipOutputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        // Ensure OutputStream has not been obtained already
        if (this.gzipOutputStream != null) {
            throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
        }
        // Create PrintWriter if it doesn't exist
        if (this.printWriter == null) {
            try {
                this.gzipOutputStream = new GZIPServletOutputStream(getResponse().getOutputStream());
                this.printWriter = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
            } catch (IOException e) {
                logger.error("Failed to create PrintWriter", e);
                throw e;
            }
        }
        return this.printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        try {
            // Flush PrintWriter if it exists
            if (this.printWriter != null) {
                this.printWriter.flush();
                // Otherwise, flush GZIP output stream if it exists
            } else if (this.gzipOutputStream != null) {
                this.gzipOutputStream.flush();
            }
            // Call the parent class's flushBuffer method
            super.flushBuffer();
        } catch (IOException e) {
            logger.error("Failed to flush buffer", e);
            throw e;
        }
    }

    @Override
    public void setContentLength(int len) {
        // Do not set content length, since content is being compressed
    }

    // Inner class for GZIPServletOutputStream
    private class GZIPServletOutputStream extends ServletOutputStream {
        private final GZIPOutputStream gzipOutputStream;

        // Constructor that takes a ServletOutputStream
        public GZIPServletOutputStream(ServletOutputStream outputStream) throws IOException {
            super();
            this.gzipOutputStream = new GZIPOutputStream(outputStream);
        }

        @Override
        public void write(int b) throws IOException {
            // Write byte to GZIP output stream
            this.gzipOutputStream.write(b);
        }

        @Override
        public void close() throws IOException {
            try {
                // Finish and close the GZIP output stream
                this.gzipOutputStream.finish();
                this.gzipOutputStream.close();
            } catch (IOException e) {
                logger.error("Failed to close GZIP output stream", e);
                throw e;
            }
        }

        @Override
        public void flush() throws IOException {
            try {
                // Flush the GZIP output stream
                this.gzipOutputStream.flush();
            } catch (IOException e) {
                logger.error("Failed to flush GZIP output stream", e);
                throw e;
            }
        }

        @Override
        public boolean isReady() {
            // Always ready to write
            return true;
        }

        @Override
        public void setWriteListener(jakarta.servlet.WriteListener writeListener) {
            // No operation needed
        }
    }
}
