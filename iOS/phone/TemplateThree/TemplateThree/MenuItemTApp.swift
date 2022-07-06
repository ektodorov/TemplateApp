//
//  MenuItemTApp.swift
//  TemplateOne

import UIKit

public class MenuItemTApp: NSObject {

    private var menuItemLabel :String;
    private var menuItemPage :String;
    
    public override init() {
        menuItemLabel = ConstantsTApp.STR_EMPTY;
        menuItemPage = ConstantsTApp.STR_EMPTY;
        super.init();
    }
    
    public func getMenuItemTitle() -> String {
        return menuItemLabel;
    }
    
    public func setMenuItemTitle(aTitle: String) {
        menuItemLabel = aTitle;
    }
    
    public func getMenuItemPage() -> String {
        return menuItemPage;
    }
    
    public func setMenuItemPage(aPage: String) {
        menuItemPage = aPage;
    }
}
