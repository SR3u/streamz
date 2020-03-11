package sr3u.streamz.streams.impl;

import sr3u.streamz.streams.Streamex;
import sr3u.streamz.streams.StringStreamex;

import java.util.stream.Stream;

public class WrappedStringStream extends WrappedStream<String> implements Streamex<String>, StringStreamex {
    protected WrappedStringStream(Stream<String> stream) {
        super(stream);
    }
}
