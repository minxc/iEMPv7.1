package org.minxc.emp.basis.impl.excel.editor.font;

import org.minxc.emp.basis.impl.excel.editor.IFontEditor;
import org.minxc.emp.basis.impl.excel.style.font.BoldWeight;
import org.minxc.emp.basis.impl.excel.style.font.Font;

/**
 * 实现一些常用的字体<br/>
 * 该类用于把字体加粗
 *
 * @author zxh
 */
public class BoldFontEditor implements IFontEditor {

    public void updateFont(Font font) {
        font.boldweight(BoldWeight.BOLD);
    }

}
