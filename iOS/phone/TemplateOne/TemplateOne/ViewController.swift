//
//  ViewController.swift
//  TemplateOne
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UIWebViewDelegate {

    @IBOutlet weak var webView: UIWebView!
    @IBOutlet weak var viewMenu: UIView!
    @IBOutlet weak var viewMenuConstraintLeadingSpace: NSLayoutConstraint!
    @IBOutlet weak var tableViewMenu: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    let kMenuShowX :Int = -20;
    let kMenuHideX :Int = -274;
    let kMenuAnimDuration :Double = 0.3;
    var mCurrentMenuX :Int = -274;
    var mArrayData :Array<MenuItemTApp> = Array<MenuItemTApp>();
    var mMenuItemSelectedIdx = 0;
    
    override func viewDidLoad() {
        super.viewDidLoad()

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
        
        viewMenu.layer.shadowOffset = CGSizeMake(1, 0);
        viewMenu.layer.shadowOpacity = 0.8;
        viewMenu.layer.shadowRadius = 3;
        viewMenu.layer.shadowPath = UIBezierPath.init(rect: viewMenu.bounds).CGPath;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    @IBAction func onButtonMenu(sender: UIButton) {
        //print("\(#line), \(#function), origin.x=%f", self.viewMenu.frame.origin.x);
        if(Int(viewMenuConstraintLeadingSpace.constant) == kMenuShowX) {
            menuClose();
        } else {
            menuOpen();
        }
    }
    
    @IBAction func onPanGestureRecognizer(sender: UIPanGestureRecognizer) {
        //print("\(#line), \(#function)");
        if(sender.state == UIGestureRecognizerState.Changed) {
            let point :CGPoint = sender.translationInView(self.viewMenu);
            var leadingSpace :CGFloat = CGFloat(mCurrentMenuX) + point.x;
            if(Int(leadingSpace) <  kMenuHideX) {
                leadingSpace = CGFloat(kMenuHideX);
            } else if(Int(leadingSpace) > kMenuShowX) {
                leadingSpace = CGFloat(kMenuShowX);
            }
            viewMenuConstraintLeadingSpace.constant = leadingSpace;
            viewMenu.setNeedsUpdateConstraints();
            self.view.layoutIfNeeded();
        }
        
        if(sender.state == UIGestureRecognizerState.Ended) {
            if(Int(viewMenuConstraintLeadingSpace.constant) <= kMenuHideX / 2) {
                menuClose();
            } else {
                menuOpen();
            }
        }
    }
    
    func animateMenuToPoint(aX :Int, aDuration: Double) {
        viewMenuConstraintLeadingSpace.constant = CGFloat(aX);
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
        animateMenuToPoint(kMenuHideX, aDuration: kMenuAnimDuration);
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
    }
    
    func loadPage(aPageName :String) {
        let urlHome :NSURL = NSURL.fileURLWithPath(NSBundle.mainBundle()
                        .pathForResource(aPageName, ofType: ConstantsTApp.STR_html, inDirectory: ConstantsTApp.STR_htmlroot)!);
        //let request :NSURLRequest = NSURLRequest(URL: urlHome);
        let request :NSURLRequest = NSURLRequest.init(URL: urlHome);
        webView.loadRequest(request);
        menuClose();
    }
}

