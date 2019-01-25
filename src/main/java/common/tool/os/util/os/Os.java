package common.tool.os.util.os;

/**
 * @author xuejian.sun
 * @date 2019/1/25 10:50
 */
public interface Os {
    /**
     * cpu 编号
     *
     * @return cpu process id
     */
    String cpuProcessId();

    /**
     * 硬盘序列号
     *
     * @return disk serial number
     */
    String diskSerialNumber();

    /**
     * Mac 地址
     *
     * @return mac
     */
    String macAddress();

    /**
     * ipv4 地址
     *
     * @return ip
     */
    String ipAddress();

    /**
     * 操作系统名
     *
     * @return os name
     */
    String os();

    /**
     * use json print os information
     * @return os information
     */
    default String printInfo() {
        return "{\n" +
                "\"cpu\":\""+ cpuProcessId()+"\",\n" +
                "\"disk-id\":\""+ diskSerialNumber()+"\",\n" +
                "\"mac\":\""+ macAddress()+"\",\n" +
                "\"ip\":\""+ipAddress()+"\",\n" +
                "\"os\":\""+os()+"\"\n" +
                "}";
    }
}
