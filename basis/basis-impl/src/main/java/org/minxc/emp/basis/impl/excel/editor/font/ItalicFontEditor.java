package org.minxc.emp.basis.impl.excel.editor.font;

import org.minxc.emp.basis.impl.excel.editor.IFontEditor;
import org.minxc.emp.basis.impl.excel.style.font.Font;

/**
 * 实现一些常用的字体<br/>
 * 该类用于设置斜体
 */
public class ItalicFontEditor implements IFontEditor {

    public void updateFont(Font font) {
        font.italic(true);
    }

}
