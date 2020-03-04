package sr3u.streamz.common;

import sr3u.streamz.exceptions.StreamexProcessingRuntimeException;

public class ExceptionCreator {
    public static RuntimeException createException(Exception e) {
        return new StreamexProcessingRuntimeException(e);
    }
}
