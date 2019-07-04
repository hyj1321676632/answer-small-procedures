package com.study.system.common;

import java.math.BigDecimal;

public class Constant {
    public static final class State {
        public static final BigDecimal INVALID = new BigDecimal(0);
        public static final BigDecimal VALID = new BigDecimal(1);
    }
    public static final class Password {
        public static final String JASYPT_ENCRYPTOR_PASSWORD = "hyj";
    }
}
