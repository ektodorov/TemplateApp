//
//  CellMenuItem.swift
//  TemplateOne

import UIKit

class CellMenuItem: UITableViewCell {

    @IBOutlet weak var labelTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        labelTitle.textColor = ConstantsTApp.COLOR_MENU_ITEM_TEXT;
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
