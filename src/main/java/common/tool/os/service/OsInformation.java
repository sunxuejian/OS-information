package common.tool.os.service;


import common.tool.os.enums.OsType;
import common.tool.os.util.os.Os;
import common.tool.os.util.os.OsFactory;
import common.tool.os.util.os.impl.WindowsFactory;

/**
 * @author xuejian.sun
 * @date 2019/1/25 10:43
 */
public class OsInformation {

    private Os os;

    public OsInformation() {
        OsType osType = OsType.readSystemType();
        OsFactory factory = null;
        if(osType == OsType.WINDOWS){
            factory = new WindowsFactory();
        }else if(osType == OsType.LINUX){
            //todo 暂未实现
        }else {
            //
        }
        this.os = factory.create();
    }


    public String allInformation(){
        return os.printInfo();
    }

    public String getMacAddress(){
        return os.macAddress();
    }

    public String getIp(){
        return os.ipAddress();
    }

    public String getDiskId(){
        return os.diskSerialNumber();
    }

    public static void main(String[] args) {
        OsInformation osInformation = new OsInformation();
        System.out.println(osInformation.os.printInfo());
    }
}
