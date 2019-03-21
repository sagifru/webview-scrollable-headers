package com.frucht.webview_scrollable_headers;

public class JavascriptConsts {
    public final static String SET_TOP_PADDING =
            "(function(){ document.body.style.paddingTop = '%spx'})();";

    public final static String LISTEN_DOM_LOADED =
            "window.addEventListener('DOMContentLoaded', (event) => {\n" +
                SET_TOP_PADDING + "\n" +
            "});";
}
