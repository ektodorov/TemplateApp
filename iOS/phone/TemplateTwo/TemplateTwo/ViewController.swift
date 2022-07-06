//
//  ViewController.swift
//  TemplateTwo

import UIKit

class ViewController: UIViewController, UIWebViewDelegate {

    @IBOutlet weak var webView: UIWebView!
    @IBOutlet weak var viewHeader: UIView!
    @IBOutlet weak var viewMenu: UIView!
    @IBOutlet weak var buttonOne: UIButton!
    @IBOutlet weak var buttonTwo: UIButton!
    @IBOutlet weak var buttonThree: UIButton!
    @IBOutlet weak var buttonFour: UIButton!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    var mArrayData :Array<MenuItemTApp> = Array<MenuItemTApp>();
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        webView.scrollView.bounces = false;
        webView.delegate = self;
        loadPage(ConstantsTApp.PAGE_0);
        setSelectedButton(buttonOne);
        viewHeader.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
        activityIndicator.color = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }

    @IBAction func onButtonOne(sender: AnyObject) {
        loadPage(ConstantsTApp.PAGE_0);
        setSelectedButton(buttonOne);
    }

    @IBAction func onButtonTwo(sender: AnyObject) {
        loadPage(ConstantsTApp.PAGE_1);
        setSelectedButton(buttonTwo);
    }
    
    @IBAction func onButtonThree(sender: AnyObject) {
        loadPage(ConstantsTApp.PAGE_2);
        setSelectedButton(buttonThree);
    }
    
    @IBAction func onButtonFour(sender: AnyObject) {
        loadPage(ConstantsTApp.PAGE_3);
        setSelectedButton(buttonFour);
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
    }
    
    func setSelectedButton(aButton :UIButton) {
        if(aButton == buttonOne) {
            buttonOne.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND_SELECTED;
            buttonTwo.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonThree.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonFour.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
        } else if(aButton == buttonTwo) {
            buttonOne.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonTwo.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND_SELECTED;
            buttonThree.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonFour.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
        } else if(aButton == buttonThree) {
            buttonOne.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonTwo.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonThree.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND_SELECTED;
            buttonFour.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
        } else if(aButton == buttonFour) {
            buttonOne.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonTwo.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonThree.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
            buttonFour.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND_SELECTED;
        }
    }
}

