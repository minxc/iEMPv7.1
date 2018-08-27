package org.minxc.emp.basis.impl.excel.editor;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;

/**
 * 设置打印格式
 *
 * @author zxh
 */
public interface IPrintSetup {

    public void setup(HSSFPrintSetup printSetup);
}
