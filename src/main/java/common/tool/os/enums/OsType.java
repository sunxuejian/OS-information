package common.tool.os.enums;

import java.util.Arrays;

/**
 * @author xuejian.sun
 * @date 2019/1/25 13:20
 */
public enum OsType {
    /**
     * linux
     */
    LINUX("linux"),
    /**
     * windows
     */
    WINDOWS("windos");

    private String type;

    public String getType() {
        return type;
    }

    OsType(String type) {
        this.type = type;
    }

    public static OsType readSystemType() {
        String osName = System.getProperty("os.name");
        return Arrays.stream(OsType.values())
                .filter(osType -> osName.toUpperCase().startsWith(osType.name()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
