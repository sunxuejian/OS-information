package common.tool.os.util.os.impl;

import common.tool.os.util.os.Os;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuejian.sun
 * @date 2019/1/25 11:06
 */
public class Windows implements Os {

    private Logger logger = LoggerFactory.getLogger(Windows.class);

    private Runtime runtime;

    private static final String MAC = "cmd /c wmic nic where netconnectionid!=null get macaddress";
    private static final String CPU_NUMBER = "cmd /c wmic cpu get processorid";
    private static final String DISK_SERIAL_NUMBER = "cmd /c wmic diskdrive get serialnumber";
    private static final String OS_NAME="cmd /c wmic computersystem get name";

    public Windows() {
        this.runtime = Runtime.getRuntime();
    }

    @Override
    public String cpuProcessId() {
        Process process = execCommand(CPU_NUMBER);
        try {
            List<String> result = parseCommandStream(process);
            return result.get(result.size() - 1);
        } catch (IOException | NullPointerException e) {
            logger.error("获取windows cpu 编号失败", e);
        }
        return null;
    }

    @Override
    public String diskSerialNumber() {
        Process process = execCommand(DISK_SERIAL_NUMBER);
        try {
            List<String> result = parseCommandStream(process);
            return result.get(result.size() - 1);
        } catch (IOException | NullPointerException e) {
            logger.error("获取硬盘序列号失败,command -> {}", DISK_SERIAL_NUMBER, e);
        }
        return null;
    }

    @Override
    public String macAddress() {
        Process process = execCommand(MAC);
        try {
            List<String> result = parseCommandStream(process);
            return result.get(result.size() - 1);
        } catch (IOException e) {
            logger.error("获取mac地址失败,command -> {}", MAC, e);
        }
        return null;
    }

    @Override
    public String ipAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            return localHost.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("获取IP地址失败",e);
        }
        return null;
    }

    @Override
    public String os() {
        Process process = execCommand(OS_NAME);
        try {
            List<String> result = parseCommandStream(process);
            return result.get(result.size() - 1);
        } catch (IOException e) {
            logger.error("获取操作系统名称失败,command -> {}", OS_NAME, e);
        }
        return null;
    }

    private List<String> parseCommandStream(Process process) throws IOException {
        if(process != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                return reader.lines()
                        .filter(StringUtils::isNotBlank)
                        .map(String::trim)
                        .collect(Collectors.toList());
            }
        }
        throw new NullPointerException("Process is Null");
    }

    private Process execCommand(String command) {
        try {
            return runtime.exec(command);
        } catch (IOException e) {
            logger.error("exec win command -> {} failure", command, e);
        }
        return null;
    }
}
