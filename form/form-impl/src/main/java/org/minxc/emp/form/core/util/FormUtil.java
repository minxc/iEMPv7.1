package org.minxc.emp.form.core.util;


import java.io.File;

import com.minxc.emp.core.util.FileUtil;

/**
 * 表单实用函数。
 *
 * @author ray。
 */
public class FormUtil {
    /**
     * 返回模版物理的路径。
     *
     * @return
     * @throws Exception
     */
    public static String getDesignTemplatePath() throws Exception {
        return FileUtil.getClassesPath() + "template" + File.separator + "design" + File.separator;
    }

}
