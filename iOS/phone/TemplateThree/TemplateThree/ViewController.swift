//
//  ViewController.swift
//  TemplateThree

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UIWebViewDelegate {

    @IBOutlet weak var buttonMenu: UIButton!
    @IBOutlet weak var layoutConstraintMenuTop: NSLayoutConstraint!
    @IBOutlet weak var layoutConstraintMenuHeight: NSLayoutConstraint!
    @IBOutlet weak var viewMenu: UIView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var webView: UIWebView!
    @IBOutlet weak var tableViewMenu: UITableView!
    
    let kMenuShowX :Int = -20;
    var mMenuHideX :Int = 400;
    let kMenuHeaderHeight = 64;
    let kMenuAnimDuration :Double = 0.3;
    var mCurrentMenuX :Int = 400;
    var mMenuItemSelectedIdx = 0;
    var mArrayData :Array<MenuItemTApp> = Array<MenuItemTApp>();

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let viewHeight = self.view.bounds.size.height;
        mMenuHideX = Int(viewHeight) - (kMenuHeaderHeight + 20);
        layoutConstraintMenuHeight.constant = viewHeight;
        animateMenuToPoint(mMenuHideX, aDuration: 0.0);
        
        webView.scrollView.bounces = false;
        webView.delegate = self;
        activityIndicator.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
        loadPage(ConstantsTApp.PAGE_0);
        
        let data = ConstantsTApp.MENU_ITEMS.dataUsingEncoding(NSUTF8StringEncoding);
        var json :Array<Dictionary<String, String>>? = nil;
        do {
            json = try NSJSONSerialization.JSONObjectWithData(data!, options: NSJSONReadingOptions.AllowFragments) as? Array<Dictionary<String, String>>;
        } catch let error as NSError {
            print("\(#line), \(#function), error=%s", error.localizedDescription);
        }
        
        var arrayItems :Array<Dictionary<String, String>> = json! as Array<Dictionary<String, String>>;
        let count = arrayItems.count;
        for x in 0..<count {
            let item = MenuItemTApp.init();
            var dictItem = arrayItems[x];
            let label = dictItem[ConstantsTApp.API_KEY_label]!;
            let page = dictItem[ConstantsTApp.API_KEY_page];
            item.setMenuItemTitle(label);
            item.setMenuItemPage(page!);
            self.mArrayData.append(item);
        }
        
        tableViewMenu.delegate = self;
        tableViewMenu.dataSource = self;
        tableViewMenu.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    @IBAction func onButtonMenuTouchUp(sender: AnyObject) {
        if(Int(layoutConstraintMenuTop.constant) == kMenuShowX) {
            menuClose();
        } else {
            menuOpen();
        }
    }
    
    func animateMenuToPoint(aX :Int, aDuration: Double) {
        layoutConstraintMenuTop.constant = CGFloat(aX);
        viewMenu.setNeedsUpdateConstraints();
        weak var weakSelf = self
        UIView.animateWithDuration(aDuration, animations: {() in
            if let strongSelf = weakSelf {
                strongSelf.view.layoutIfNeeded();
            }
        }, completion: {(completed: Bool) in
            if let strongSelf = weakSelf {
                strongSelf.mCurrentMenuX = aX;
            }
        });
    }
    
    func menuClose() {
        animateMenuToPoint(mMenuHideX, aDuration: kMenuAnimDuration);
    }
    
    func menuOpen() {
        animateMenuToPoint(kMenuShowX, aDuration: kMenuAnimDuration);
    }
    
    /* UITableViewDataSource */
     func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier(ConstantsTApp.CELL_MENU_ID);
        if(cell == nil) {
            let nib :NSArray = NSBundle.mainBundle().loadNibNamed(ConstantsTApp.NIB_NAME_CellMenuItem, owner: self, options: nil);
            cell = nib.objectAtIndex(0) as? UITableViewCell;
        }
        let cellMenuItem :CellMenuItem = cell as! CellMenuItem;
        if(indexPath.row == mMenuItemSelectedIdx) {
            cellMenuItem.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND_SELECTED;
        } else {
            cellMenuItem.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
        }
        
        let menuItem :MenuItemTApp = self.mArrayData[indexPath.row];
        cellMenuItem.labelTitle.text = menuItem.getMenuItemTitle();
        
        return cell!;
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mArrayData.count;
    }
    
    /* UITableViewDelegate */
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        mMenuItemSelectedIdx = indexPath.row;
        let menuItem :MenuItemTApp = self.mArrayData[indexPath.row];
        self.loadPage(menuItem.getMenuItemPage());
        menuClose();
        tableView.reloadData();
    }
    
    /* UIWebViewDelegate */
    func webView(webView: UIWebView, shouldStartLoadWithRequest request: NSURLRequest, navigationType: UIWebViewNavigationType) -> Bool {
        return true;
    }
    
    func webViewDidStartLoad(webView: UIWebView) {
        activityIndicator.startAnimating();
    }
    
    func webViewDidFinishLoad(webView: UIWebView) {
        activityIndicator.stopAnimating();
    }
    
    func webView(webView: UIWebView, didFailLoadWithError error: NSError?) {
        loadPage(ConstantsTApp.PAGE_ERROR);
        menuClose();
    }
    
    func loadPage(aPageName :String) {
        let urlHome :NSURL = NSURL.fileURLWithPath(NSBundle.mainBundle()
                        .pathForResource(aPageName, ofType: ConstantsTApp.STR_html, inDirectory: ConstantsTApp.STR_htmlroot)!);
        //let request :NSURLRequest = NSURLRequest(URL: urlHome);
        let request :NSURLRequest = NSURLRequest.init(URL: urlHome);
        webView.loadRequest(request);
    }
}

