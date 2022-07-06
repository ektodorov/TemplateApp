//
//  ConstantsTApp.swift
//  TemplateOne

import UIKit

class ConstantsTApp: NSObject {

    static let STR_EMPTY = "";
    static let STR_html = "html"
    static let STR_htmlroot = "htmlroot";
    
    static let PAGE_ERROR = "error_page";
    static let PAGE_0 = "Page0";
    static let API_KEY_label = "label";
    static let API_KEY_page = "page";
    
    static let CELL_MENU_ID = "cellmenu";
    static let NIB_NAME_CellMenuItem = "CellMenuItem";
    
    static let COLOR_MENU_ITEM_BACKGROUND = UIColor.init(red: 0.0, green: 150/255, blue: 204/255, alpha: 1.0);
    static let COLOR_MENU_ITEM_BACKGROUND_SELECTED = UIColor.init(red: 0.0, green: 150/255, blue: 235/255, alpha: 1.0);
    static let COLOR_MENU_ITEM_TEXT = UIColor.init(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0);
    
    static let MENU_ITEMS :String = "[{\"label\":\"Item Zero\", \"page\":\"Page0\"},"
                                    + "{\"label\":\"Item One\", \"page\":\"Page1\"},"
                                    + "{\"label\":\"Item Two\", \"page\":\"Page2\"},"
                                    + "{\"label\":\"Item Three\", \"page\":\"Page3\"},"
                                    + "{\"label\":\"Item Four\", \"page\":\"Page4\"},"
                                    + "{\"label\":\"Item Five\", \"page\":\"Page5\"},"
                                    + "{\"label\":\"Item Six\", \"page\":\"Page6\"},"
                                    + "{\"label\":\"Item Seven\", \"page\":\"Page7\"},"
                                    + "{\"label\":\"Item Eight\", \"page\":\"Page8\"},"
                                    + "{\"label\":\"Item Nine\", \"page\":\"Page9\"},"
                                    + "{\"label\":\"Item Ten\", \"page\":\"Page10\"}]";

}
