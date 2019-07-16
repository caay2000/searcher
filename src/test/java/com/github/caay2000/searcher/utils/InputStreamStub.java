package com.github.caay2000.searcher.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

public class InputStreamStub extends InputStream {

    private final Queue<Byte> byteQueue;

    public InputStreamStub(String value) {

        byteQueue = new ArrayDeque<>();
        for (byte aByte : value.getBytes()) {
            byteQueue.offer(aByte);
        }
    }

    @Override
    public int read() throws IOException {

        Byte aByte = byteQueue.poll();
        if (aByte == null) {
            return -1;
        }
        return aByte.intValue();
    }
}
