package common.tool.os.util.os.impl;

import common.tool.os.util.os.Os;
import common.tool.os.util.os.OsFactory;

/**
 * @author xuejian.sun
 * @date 2019/1/25 13:19
 */
public class WindowsFactory implements OsFactory {
    @Override
    public Os create() {
        return new Windows();
    }
}
