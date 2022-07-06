//
//  ViewController.swift
//  TemplateFour

import UIKit

class ViewController: UIViewController, UIWebViewDelegate {

    @IBOutlet weak var webView: UIWebView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        webView.scrollView.bounces = false;
        webView.delegate = self;
        activityIndicator.backgroundColor = ConstantsTApp.COLOR_MENU_ITEM_BACKGROUND;
        loadPage(ConstantsTApp.PAGE_0);
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
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
}

