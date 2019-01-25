package common.tool.os.controller;


import common.tool.os.service.OsInformation;
import common.tool.os.util.AesEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author like
 * @description
 * @data 2019/1/24
 */
@RequestMapping("/computer")
@RestController
public class ComputerApi {

    @Value("${encryption.enable:true}")
    private Boolean encryptionEnable;

    @Autowired
    private AesEncryptUtil aesEncryptor;

    @Autowired
    private OsInformation osInformation;

    @GetMapping("/all")
    public String allOsInformation(){
        return osInformation.allInformation();
    }
}
